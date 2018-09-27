package com.ljj.kotlinsimple.user.page

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ljj.comm.bean.UserBrief
import com.ljj.comm.busbean.UpdateRelationship
import com.ljj.comm.entity.Relationship
import com.ljj.comm.model.UserAssistModel
import com.ljj.comm.mvp.AbstractRequestCallBack
import com.ljj.comm.mvp.presenter.ActivityPresenter
import com.ljj.comm.rxbus.RxBus
import com.ljj.comm.util.RxUtils
import com.ljj.kotlinsimple.user.R
import com.ljj.kotlinsimple.user.bean.UserBean
import com.ljj.kotlinsimple.user.model.UserModel
import com.ljj.kotlinsimple.user.page.contract.UserDetailContract
import com.ljj.kotlinsimple.user.page.view.UserDetailViewDelegate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer

@Route(path = "/user/activity/user_detail")
class UserDetailActivity : ActivityPresenter<UserDetailContract.View>(), UserDetailContract.Presenter, View.OnClickListener {

    private val userAssistModel: UserAssistModel by lazy {
        ARouter.getInstance().navigation(UserAssistModel::class.java)
    }
    private val userModel: UserModel by lazy {
        ARouter.getInstance().navigation(UserModel::class.java)
    }

    private var userBean: UserBean? = null

    override val getDelegateView: UserDetailContract.View
        get() = UserDetailViewDelegate()

    override fun onCreateBefore(savedInstanceState: Bundle?) {
        register(RxBus.getDefault().register(UpdateRelationship::class.java, Consumer<UpdateRelationship> { updateRelationship ->
            if (updateRelationship == null) {
                return@Consumer
            }

            val userId = updateRelationship.userId!!
            if (userBean == null || userBean!!.id != userId) {
                return@Consumer
            }

            val relationship = updateRelationship.relationship
            userBean!!.relationship = relationship!!

            if (Relationship.DEFAULT == relationship) {
                viewDelegate.doUnFollowResult()
            } else if (Relationship.FOLLOWED == relationship) {
                viewDelegate.doFollowedResult()
            }
        }, AndroidSchedulers.mainThread()))
    }

    override fun onCreateAfter() {
        viewDelegate.setOnClickListenter(this, R.id.user_detail_follow_btn)

        val userId: Long
        val userBrief = intent.getParcelableExtra<UserBrief>(USER)
        if (userBrief != null) {
            userId = userBrief.id
        } else {
            userId = intent.getLongExtra(USER_ID, -1)
        }

        requestUserDetail(userId)
    }

    /**
     * 请求用户详情
     */
    override fun requestUserDetail(userId: Long) {
        register(RxUtils.defaultCallback(userModel.getUser(userId).map { userEntity -> UserBean(userEntity) }, object : AbstractRequestCallBack<UserBean>(this) {
            /**
             * 请求结果回调
             *
             * @param data
             */
            override fun onResponse(data: UserBean) {
                userBean = data
                viewDelegate.doUserDetail(data)
            }
        }))
    }

    /**
     * 关注用户
     */
    override fun followUser() {
        updateUserRelationship(userBean!!.id, Relationship.FOLLOWED)
    }

    /**
     * 取消关注用户
     */
    override fun unFollowUser() {
        updateUserRelationship(userBean!!.id, Relationship.DEFAULT)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.user_detail_follow_btn) {
            updateUserRelationship()
        }
    }

    private fun updateUserRelationship() {
        if (userBean == null) {
            return
        }

        if (Relationship.DEFAULT == userBean!!.relationship) {
            followUser()
        } else if (Relationship.FOLLOWED == userBean!!.relationship) {
            unFollowUser()
        }
    }

    private fun updateUserRelationship(userId: Long, relationship: Relationship) {
        userAssistModel.updateUserRelation(userId, relationship, object : AbstractRequestCallBack<Boolean>(this) {
            override fun onResponse(data: Boolean) {
                RxBus.getDefault().post(UpdateRelationship(userId, relationship))
            }
        })
    }

    companion object {
        private const val USER_ID = "user_id"
        private const val USER = "user"
    }
}
