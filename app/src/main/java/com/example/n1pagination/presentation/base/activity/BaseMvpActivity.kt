package com.example.n1pagination.presentation.base.activity

import android.os.Bundle
import androidx.annotation.CallSuper
import com.example.n1pagination.presentation.base.presenter.IPresenter
import com.example.n1pagination.presentation.base.view.IView
import javax.inject.Inject

abstract class BaseMvpActivity<V : IView, P : IPresenter<V>> : BaseActivity(), IView {

    @Inject
    lateinit var presenter: P

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        initDependencies()
        super.onCreate(savedInstanceState)

        presenter?.let {
            presenter = it
            presenter.attachLifecycle(lifecycle)
            presenter.bindView(this as V)
        }
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        presenter.detachLifecycle(lifecycle)
        presenter.unBindView()
    }

    protected abstract fun initDependencies()

    override fun showProgress() {
        //For override
    }

    override fun hideProgress() {
        //For override
    }
}