package com.example.n1pagination.presentation.features.flatoffers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.n1pagination.R


class PhotoViewPagerAdapter(
    val photoList: List<String>
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.photo_item, null)
        val imageView = view.findViewById<ImageView>(R.id.ivPhoto)
        Glide.with(container.context).load(photoList.get(position)).into(imageView)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` === view
    }

    override fun getCount(): Int {
        return photoList.size
    }
}