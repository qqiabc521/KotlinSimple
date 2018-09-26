package com.ljj.kotlinsimple.user.bean

import android.os.Parcel
import android.os.Parcelable

import com.ljj.comm.bean.UserBrief
import com.ljj.comm.entity.UserEntity

/**
 * Created by lijunjie on 2017/12/28.
 */

class UserBean : UserBrief, Parcelable {

    var age: Int = 0

    var email: String? = null

    var phoneNumber: String? = null

    var description: String? = null

    constructor() : super() {}

    constructor(userEntity: UserEntity) : super(userEntity) {
        this.age = userEntity.age
        this.email = userEntity.email
        this.phoneNumber = userEntity.phoneNumber
        this.description = userEntity.description
    }

    protected constructor(`in`: Parcel) : super(`in`) {
        age = `in`.readInt()
        email = `in`.readString()
        phoneNumber = `in`.readString()
        description = `in`.readString()
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of [.writeToParcel],
     * the return value of this method must include the
     * [.CONTENTS_FILE_DESCRIPTOR] bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    override fun describeContents(): Int {
        return 0
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     * May be 0 or [.PARCELABLE_WRITE_RETURN_VALUE].
     */
    override fun writeToParcel(dest: Parcel, flags: Int) {
        super.writeToParcel(dest, flags)
        dest.writeInt(age)
        dest.writeString(email)
        dest.writeString(phoneNumber)
        dest.writeString(description)
    }

    companion object {

        val CREATOR: Parcelable.Creator<UserBean> = object : Parcelable.Creator<UserBean> {
            override fun createFromParcel(`in`: Parcel): UserBean {
                return UserBean(`in`)
            }

            override fun newArray(size: Int): Array<UserBean?> {
                return arrayOfNulls(size)
            }
        }

        fun createUserEntity(userBean: UserBean): UserEntity {
            val userEntity = UserEntity()
            userEntity.id = if (userBean.id == 0L) null else userBean.id
            userEntity.age = userBean.age
            userEntity.name = userBean.name
            userEntity.email = userBean.email
            userEntity.phoneNumber = userBean.phoneNumber
            userEntity.description = userBean.description
            userEntity.relationShip = userBean.relationship

            return userEntity
        }
    }
}
