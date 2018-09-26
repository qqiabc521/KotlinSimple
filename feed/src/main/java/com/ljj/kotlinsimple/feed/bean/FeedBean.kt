package com.ljj.kotlinsimple.feed.bean

import android.os.Parcel
import android.os.Parcelable

import com.ljj.comm.bean.FeedBrief
import com.ljj.comm.entity.FeedEntity


class FeedBean : FeedBrief, Parcelable {

    var content: String? = null

    var likeCount: Int = 0

    constructor() : super() {}

    constructor(feedEntity: FeedEntity) : super(feedEntity) {
        this.content = feedEntity.content
        this.likeCount = feedEntity.likeCount
    }

    protected constructor(`in`: Parcel) : super(`in`) {
        content = `in`.readString()
        likeCount = `in`.readInt()
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
        dest.writeString(content)
        dest.writeInt(likeCount)
    }

    companion object {

        val CREATOR: Parcelable.Creator<FeedBean> = object : Parcelable.Creator<FeedBean> {
            override fun createFromParcel(`in`: Parcel): FeedBean {
                return FeedBean(`in`)
            }

            override fun newArray(size: Int): Array<FeedBean?> {
                return arrayOfNulls(size)
            }
        }

        fun createFeedEntity(feedBean: FeedBean): FeedEntity {
            val feedEntity = FeedEntity()
            feedEntity.id = if (feedBean.id == 0L) null else feedBean.id
            feedEntity.content = feedBean.content
            feedEntity.title = feedBean.title
            if (feedBean.owner != null) {
                feedEntity.ownerId = feedBean.owner!!.id
            }
            feedEntity.likeCount = feedBean.likeCount

            return feedEntity
        }
    }
}
