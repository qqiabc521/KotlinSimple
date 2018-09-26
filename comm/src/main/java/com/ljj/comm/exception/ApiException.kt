package com.ljj.comm.exception

/**
 * Created by lijunjie on 2017/12/21.
 */

class ApiException : RuntimeException {

    constructor() : super() {}

    constructor(message: String) : super(message) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}
