package com.ljj.kotlinsimple.user.page.view

import android.widget.Button
import android.widget.TextView
import com.ljj.comm.entity.Relationship
import com.ljj.comm.mvp.view.BaseViewDelegate
import com.ljj.kotlinsimple.user.R
import com.ljj.kotlinsimple.user.bean.UserBean
import com.ljj.kotlinsimple.user.page.contract.UserDetailContract

class UserDetailViewDelegate : BaseViewDelegate(),UserDetailContract.View {

    private lateinit var nickNameTV: TextView
    private lateinit var ageTV: TextView
    private lateinit var emailTV: TextView
    private lateinit var phoneTV: TextView
    private lateinit var signTV: TextView
    private lateinit var followBtn: Button

    override val rootLayoutId: Int
        get() = R.layout.activity_user

    override fun initWidget() {
        nickNameTV = get(R.id.user_detail_name)
        ageTV = get(R.id.user_detail_age)
        emailTV = get(R.id.user_detail_email)
        phoneTV = get(R.id.user_detail_phone)
        signTV = get(R.id.user_detail_sign)
        followBtn = get(R.id.user_detail_follow_btn)
    }

    private fun doRelationship(relationship: Relationship) {
        if (Relationship.FOLLOWED == relationship) {
            doFollowedResult()
        } else if (Relationship.DEFAULT == relationship) {
            doUnFollowResult()
        }
    }

    /**
     * 显示user信息
     */
    override fun doUserDetail(userBean: UserBean) {
        setActionBarTitle(userBean.name!!)

        nickNameTV.text = userBean.name
        ageTV.text = userBean.age.toString() + "岁"
        emailTV.text = userBean.email
        phoneTV.text = userBean.phoneNumber
        signTV.text = userBean.description

        doRelationship(userBean.relationship)
    }

    /**
     * 更新关注UI
     */
    override fun doFollowedResult() {
        followBtn.text = "取消关注"
    }

    /**
     * 更新未关注UI
     */
    override fun doUnFollowResult() {
        followBtn.text = "关注"
    }
}
