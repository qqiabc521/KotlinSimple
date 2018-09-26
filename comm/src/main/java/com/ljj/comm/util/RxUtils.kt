package com.ljj.comm.util


import com.ljj.comm.mvp.PresenterDelegate
import com.ljj.comm.mvp.RequestCallBack
import com.ljj.comm.rx.ExceptionConsumer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by lijunjie on 2017/12/21.
 */

object RxUtils {

    //为解决单元测试不依赖android环境，默认不指明callbackScheduler 为AndroidSchedulers.mainThread()
    private var callbackScheduler = AndroidSchedulers.mainThread()

    fun <T> defaultSchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream
                    .unsubscribeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io())
                    .observeOn(callbackScheduler)
        }
    }

    //    public static <T> Disposable defaultHttpResultCallback(Observable<HttpResult<T>> observable, final RequestCallBack<T> requestCallBack) {
    //        return defaultCallback(observable.map(new HttpResultFunc<T>()), requestCallBack);
    //    }

    fun <T> defaultCallback(observable: Observable<T>, requestCallBack: RequestCallBack<T>): Disposable {
        return observable
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(callbackScheduler)
                .doOnSubscribe { disposable ->
                    requestCallBack.onRequestStart(disposable)
                }
                .doOnTerminate {
                    requestCallBack.onFinish()
                }
                .subscribe(Consumer { t ->
                    requestCallBack.onResponse(t)
                }, object : ExceptionConsumer() {

                    override fun accept(message: String) {
                        requestCallBack.onRequestError(message)
                    }
                })
    }

    fun <T> defaultCallback(observable: Observable<T>, presenterDelegate: PresenterDelegate, rxResult: RxResult<T>): Disposable {
        return defaultCallback(null, observable, presenterDelegate, rxResult)
    }

    fun <T> defaultCallback(taskId: String?, observable: Observable<T>, presenterDelegate: PresenterDelegate, rxResult: RxResult<T>): Disposable {
        return observable
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(callbackScheduler)
                .doOnSubscribe { disposable ->
                    presenterDelegate.onRequestStart(taskId, disposable)
                }
                .doOnTerminate {
                    presenterDelegate.onFinish(taskId)
                }
                .subscribe({ t ->
                    rxResult.doResult(t)
                }, { t ->
                    presenterDelegate.onRequestError(taskId, t.message!!)
                }, {
                    rxResult.doCompleted()
                })
    }

    interface RxResult<T> {

        fun doResult(t: T)

        fun doCompleted()
    }
}
