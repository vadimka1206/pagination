package com.example.n1pagination.presentation.features.flatoffers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.n1pagination.R
import com.example.n1pagination.data.model.FlatOffer
import com.example.n1pagination.presentation.Router
import java.text.NumberFormat
import java.util.*


class FlatOffersAdapter : RecyclerView.Adapter<FlatOffersAdapter.FlatOfferViewHolder>() {

    private var list: ArrayList<FlatOffer> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlatOfferViewHolder {
        val view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.flat_offer_item, parent, false);
        return FlatOfferViewHolder(
            view
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: FlatOfferViewHolder, position: Int) {
        holder.bind(list.get(position));
    }

    fun addItems(items: List<FlatOffer>) {
        list.addAll(items)
        notifyItemInserted(items.count())
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    class FlatOfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvAddress: TextView
        var tvPrice: TextView
        var tvFloor: TextView
        var tvArea: TextView
        var tvPhotoNumber: TextView
        var cvPhotoNumberHolder: CardView
        var vpPhotos: ViewPager
        var ivNoPhoto: ImageView

        init {
            tvAddress = itemView.findViewById(R.id.tvAddress)
            tvPrice = itemView.findViewById(R.id.tvPrice)
            tvFloor = itemView.findViewById(R.id.tvFloor)
            tvArea = itemView.findViewById(R.id.tvArea)
            tvPhotoNumber = itemView.findViewById(R.id.tvPhotoNumber)
            cvPhotoNumberHolder = itemView.findViewById(R.id.cvPhotoNumberHolder)
            vpPhotos = itemView.findViewById(R.id.vpPhotos)
            ivNoPhoto = itemView.findViewById(R.id.ivNoPhoto)
        }

        fun bind(offer: FlatOffer) {
            itemView.setOnClickListener { Router.navigateToOfferCard(itemView.context, offer) }
            tvAddress.text = itemView.context.getString(
                R.string.address_template,
                offer.roomsCount, offer.streetName, offer.houseNumber
            )
            tvPrice.text =
                itemView.context.getString(R.string.price_template, NumberFormat.getInstance().format(offer.price))
            tvFloor.text = itemView.context.getString(R.string.floors_template, offer.floor, offer.floorsCount)
            tvArea.text = itemView.context.getString(
                R.string.area_template,
                offer.totalAreaInMeters,
                offer.livingAreaInMeters,
                offer.kitchenAreaInMeters
            )
            if (offer.photos.isNotEmpty()) {
                cvPhotoNumberHolder.visibility = View.VISIBLE
                vpPhotos.visibility = View.VISIBLE
                ivNoPhoto.visibility = View.GONE
                vpPhotos.adapter = PhotoViewPagerAdapter(offer.photos)
                vpPhotos.addOnPageChangeListener(OnPhotoChangeListener {
                    tvPhotoNumber.text =
                        itemView.context.getString(R.string.photos_number_template, it + 1, offer.photos.size)
                })
                tvPhotoNumber.text = itemView.context.getString(R.string.photos_number_template, 1, offer.photos.size)
            } else {
                cvPhotoNumberHolder.visibility = View.GONE
                vpPhotos.visibility = View.INVISIBLE
                ivNoPhoto.visibility = View.VISIBLE
            }
        }
    }
}