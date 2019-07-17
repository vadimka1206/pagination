package com.example.n1pagination.presentation.base.presenter

import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import com.example.n1pagination.presentation.base.handler.IErrorHandler
import com.example.n1pagination.presentation.base.view.IView
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BasePresenter<V : IView> : LifecycleObserver, IPresenter<V> {

    protected var view: V? = null
    protected var errorHandler: IErrorHandler? = null

    private val disposables = HashMap<String, Disposable>()
    private val compositeDisposable = CompositeDisposable()

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun addDisposable(disposable: Disposable, tag: String) {
        if (disposables.containsKey(tag)) {
            disposables[tag]?.dispose()
        }

        disposables[tag] = disposable
    }

    override fun attachLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    override fun detachLifecycle(lifecycle: Lifecycle) {
        lifecycle.removeObserver(this)
    }

    override fun bindView(view: V) {
        this.view = view
    }

    override fun unBindView() {
        view = null
    }

    @CallSuper
    override fun onPresenterDestroy() {
        compositeDisposable.clear()
        disposables.values.forEach { it.dispose() }
        disposables.clear()
    }

    protected fun <T> runAsyncProgress(
        single: Single<T>, result: (result: T) -> Unit, error: (throwable: Throwable) -> Unit = { showError(it) }
    ) {
        addDisposable(
            schedulingWithProgress(single)
                .subscribe({ result.invoke(it) }, { error.invoke(it) })
        )
    }

    protected fun <T> runAsync(
        single: Single<T>, result: (result: T) -> Unit, error: (throwable: Throwable) -> Unit = { showError(it) }
    ) {
        addDisposable(
            scheduling(single)
                .subscribe({ result.invoke(it) }, { error.invoke(it) })
        )
    }

    protected fun runAsync(
        single: Completable, result: () -> Unit, error: (throwable: Throwable) -> Unit = { showError(it) }
    ) {
        addDisposable(
            scheduling(single)
                .subscribe({ result.invoke() }, { error.invoke(it) })
        )
    }

    protected fun <T> runAsyncProgress(
        flowable: Flowable<T>, result: (result: T) -> Unit, error: (throwable: Throwable) -> Unit = { showError(it) }
    ) {
        addDisposable(
            schedulingWithProgress(flowable)
                .subscribe({ result.invoke(it) }, { error.invoke(it) })
        )
    }

    protected fun <T> runAsync(
        flowable: Flowable<T>, result: (result: T) -> Unit, error: (throwable: Throwable) -> Unit = { showError(it) }
    ) {
        addDisposable(
            scheduling(flowable)
                .subscribe({ result.invoke(it) }, { error.invoke(it) })
        )
    }

    protected fun <T> runAsyncProgress(
        observable: Observable<T>,
        result: (result: T) -> Unit,
        error: (throwable: Throwable) -> Unit = { showError(it) }
    ) {
        addDisposable(
            schedulingWithProgress(observable)
                .subscribe({ result.invoke(it) }, { error.invoke(it) })
        )
    }

    protected fun <T> runAsync(
        observable: Observable<T>,
        result: (result: T) -> Unit,
        error: (throwable: Throwable) -> Unit = { showError(it) }
    ) {
        addDisposable(
            scheduling(observable)
                .subscribe({ result.invoke(it) }, { error.invoke(it) })
        )
    }

    private fun <T> scheduling(single: Single<T>): Single<T> {
        return single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun scheduling(single: Completable): Completable {
        return single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    protected fun <T> schedulingWithProgress(single: Single<T>): Single<T> {
        return scheduling(single).doOnSubscribe { progressOn() }
            .doAfterTerminate { progressOff() }
        /*.doOnError { progressOff() }*/
    }

    private fun <T> schedulingWithProgress(flowable: Flowable<T>): Flowable<T> {
        return scheduling(flowable).doOnSubscribe { progressOn() }
            .doAfterTerminate { progressOff() }
    }

    private fun <T> schedulingWithProgress(observable: Observable<T>): Observable<T> {
        return scheduling(observable).doOnSubscribe { progressOn() }
            .doOnNext { progressOff() }
            .doAfterTerminate { progressOff() }
    }

    private fun <T> scheduling(observable: Observable<T>): Observable<T> {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun <T> scheduling(flowable: Flowable<T>): Flowable<T> {
        return flowable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun progressOn() {
        showProgress()
    }

    private fun progressOff() {
        hideProgress()
    }

    protected open fun showError(throwable: Throwable) {
        try {
            errorHandler?.handleError(throwable, view)
        } catch (ignored: Throwable) {
            //Ignored
        }
    }

    protected fun hideProgress() {
        view?.hideProgress()
    }

    protected fun showProgress() {
        view?.showProgress()
    }

}