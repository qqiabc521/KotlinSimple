package com.ljj.comm.rx


import com.ljj.comm.BaseApplication
import com.ljj.comm.R
import com.ljj.comm.exception.ApiException
import io.reactivex.annotations.NonNull
import io.reactivex.functions.Consumer
import retrofit2.HttpException
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by lijunjie on 2017/12/21.
 */

abstract class ExceptionConsumer : Consumer<Throwable> {

    /**
     * Consume the given value.
     *
     * @param throwable the value
     * @throws Exception on error
     */
    @Throws(Exception::class)
    override fun accept(@NonNull throwable: Throwable) {
        val message = getMessage(throwable)
        accept(message)
    }

    abstract fun accept(message: String)

    companion object {

        fun getMessage(throwable: Throwable): String {
            if (throwable is ApiException) {
                return throwable.message!!
            } else if (throwable is java.net.ConnectException || throwable is UnknownHostException) {
                return BaseApplication.instance!!.getString(R.string.common_network_error)
            } else if (throwable is SocketTimeoutException) {
                return BaseApplication.instance!!.getString(R.string.common_network_timeout)
            } else if (throwable is MalformedURLException || throwable is ProtocolException) {
                return BaseApplication.instance!!.getString(R.string.common_network_malformed)
            } else if (throwable is HttpException) {
                val code = throwable.code()
                return if (code >= 500 && code < 600) {
                    BaseApplication.instance.getString(R.string.common_network_server)
                } else if (code >= 400 && code < 500) {
                    BaseApplication.instance.getString(R.string.common_network_client)
                } else {
                    BaseApplication.instance!!.getString(R.string.common_network_request)
                }
            } else {
                return BaseApplication.instance!!.getString(R.string.common_network_retry)
            }
        }
    }
}
