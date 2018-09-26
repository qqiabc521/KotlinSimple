package com.ljj.comm.bean

import android.os.Parcel
import android.os.Parcelable

import com.ljj.comm.entity.Relationship
import com.ljj.comm.entity.UserEntity

open class UserBrief : BaseBean, Parcelable {

    var id: Long = 0

    var name: String? = null

    var relationship = Relationship.DEFAULT

    constructor()

    constructor(userEntity: UserEntity) {
        this.id = userEntity.id!!
        this.name = userEntity.name
        this.relationship = userEntity.relationShip
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readLong()
        name = `in`.readString()
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
        dest.writeLong(id)
        dest.writeString(name)
    }

    companion object CREATOR : Parcelable.Creator<UserBrief> {
        override fun createFromParcel(source: Parcel): UserBrief {
            return UserBrief(source)
        }

        override fun newArray(size: Int): Array<UserBrief?> {
            return arrayOfNulls(size)
        }

    }
}
