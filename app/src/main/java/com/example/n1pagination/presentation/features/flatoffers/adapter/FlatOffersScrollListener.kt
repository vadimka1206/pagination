package com.example.n1pagination.presentation.features.flatoffers.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FlatOffersScrollListener
    (val listener: () -> Unit) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is LinearLayoutManager && layoutManager != null) {
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount - 10 && firstVisibleItemPosition >= 0) {
                listener.invoke()
            }
        }
    }
}