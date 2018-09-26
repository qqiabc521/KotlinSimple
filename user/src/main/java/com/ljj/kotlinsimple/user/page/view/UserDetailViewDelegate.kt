package com.ljj.kotlinsimple.user.page.view

import android.widget.Button
import android.widget.TextView

import com.ljj.comm.entity.Relationship
import com.ljj.comm.mvp.view.BaseViewDelegate
import com.ljj.kotlinsimple.user.R
import com.ljj.kotlinsimple.user.bean.UserBean

class UserDetailViewDelegate : BaseViewDelegate() {

    private var nickNameTV: TextView? = null
    private var ageTV: TextView? = null
    private var emailTV: TextView? = null
    private var phoneTV: TextView? = null
    private var signTV: TextView? = null
    private var followBtn: Button? = null

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

    fun doUserDetail(userBean: UserBean) {
        setActionBarTitle(userBean.name!!)

        nickNameTV!!.text = userBean.name
        ageTV!!.text = userBean.age.toString() + "岁"
        emailTV!!.text = userBean.email
        phoneTV!!.text = userBean.phoneNumber
        signTV!!.text = userBean.description

        doRelationship(userBean.relationship)
    }

    private fun doRelationship(relationship: Relationship) {
        if (Relationship.FOLLOWED == relationship) {
            doFollowedResult()
        } else if (Relationship.DEFAULT == relationship) {
            doUnFollowResult()
        }
    }

    fun doFollowedResult() {
        followBtn!!.text = "取消关注"
    }

    fun doUnFollowResult() {
        followBtn!!.text = "关注"
    }
}
