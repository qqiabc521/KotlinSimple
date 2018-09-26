package com.ljj.comm.bean

import android.os.Parcel
import android.os.Parcelable

import com.ljj.comm.entity.FeedEntity
import com.ljj.comm.entity.Relationship

open class FeedBrief : BaseBean, Parcelable {

    var id: Long = 0

    lateinit var title: String

    var owner: UserBrief? = null

    constructor()

    constructor(feedEntity: FeedEntity) {
        this.id = feedEntity.id!!
        this.title = feedEntity.title

        if (feedEntity.owner != null) {
            this.owner = UserBrief(feedEntity.owner!!)
        }
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readLong()
        title = `in`.readString()
        owner = `in`.readParcelable(UserBrief::class.java.classLoader)
    }

    fun updateRelationship(relationship: Relationship) {
        if (owner != null) {
            owner!!.relationship = relationship
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(title)
        dest.writeParcelable(owner, flags)
    }

    companion object {

        val CREATOR: Parcelable.Creator<FeedBrief> = object : Parcelable.Creator<FeedBrief> {
            override fun createFromParcel(`in`: Parcel): FeedBrief {
                return FeedBrief(`in`)
            }

            override fun newArray(size: Int): Array<FeedBrief?> {
                return arrayOfNulls(size)
            }
        }
    }
}
