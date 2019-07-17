package com.example.n1pagination.presentation.features.flatoffers.adapter

import androidx.viewpager.widget.ViewPager

class OnPhotoChangeListener(
    var pageChanger: (page: Int) -> Unit
) : ViewPager.OnPageChangeListener {

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        pageChanger.invoke(position)
    }
}