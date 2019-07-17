package com.example.n1pagination.presentation.features.flatoffers.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.n1pagination.R
import com.example.n1pagination.data.model.FlatOffer
import com.example.n1pagination.di.DaggerCore
import com.example.n1pagination.presentation.base.activity.BaseMvpActivity
import com.example.n1pagination.presentation.features.flatoffers.adapter.FlatOffersAdapter
import com.example.n1pagination.presentation.features.flatoffers.adapter.FlatOffersScrollListener
import com.example.n1pagination.presentation.features.flatoffers.di.DaggerFlatOffersComponent
import com.example.n1pagination.presentation.features.flatoffers.di.FlatOffersModule
import com.example.n1pagination.presentation.features.flatoffers.presenter.IFlatOffersPresenter

class FlatOffersActivity : BaseMvpActivity<IFlatOffersView, IFlatOffersPresenter>(), IFlatOffersView {

    private val adapter = FlatOffersAdapter()
    lateinit var progress: ProgressBar
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flat_offers)
        progress = findViewById(R.id.progress)
        presenter.onCreate()
        initRecyclerView()
        initSwipeRefreshLayout()
    }

    override fun initDependencies() {
        DaggerFlatOffersComponent.builder().appComponent(DaggerCore.appComponent)
            .flatOffersModule(FlatOffersModule())
            .build().inject(this)
    }

    override fun addItems(list: List<FlatOffer>) {
        adapter.addItems(list)
    }

    override fun clearList() {
        adapter.clear()
    }

    override fun stopRefresh() {
        swipeRefreshLayout.isRefreshing = false
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvFlatOffers)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(FlatOffersScrollListener({ presenter.loadMore() }))
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout = findViewById(R.id.swipeRefresh)
        swipeRefreshLayout.setOnRefreshListener {
            presenter.refresh()
        }
    }

    override fun showProgress() {
        super.showProgress()
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        super.hideProgress()
        progress.visibility = View.GONE
    }
}