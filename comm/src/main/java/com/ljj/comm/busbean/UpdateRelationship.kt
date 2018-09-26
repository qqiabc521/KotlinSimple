package com.ljj.comm.busbean


import com.ljj.comm.entity.Relationship

/**
 * Created by lijunjie on 2018/1/15.
 */

class UpdateRelationship {
    var userId: Long
    var relationship: Relationship

    constructor(userId: Long, relationship: Relationship) {
        this.userId = userId
        this.relationship = relationship
    }
}
