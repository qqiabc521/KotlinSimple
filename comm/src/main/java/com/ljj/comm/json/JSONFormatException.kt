package com.ljj.comm.json


/**
 * Created by lijunjie on 2017/12/21.
 */
class JSONFormatException : Exception {

    constructor() : super() {}

    constructor(message: String) : super(message) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}

    companion object {

        private const val serialVersionUID = 6411745024910244643L
    }

}
