package com.example.n1pagination.presentation.features.offer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.n1pagination.R
import com.example.n1pagination.data.model.FlatOffer
import com.example.n1pagination.databinding.ActivityCurrentOfferBinding
import com.example.n1pagination.presentation.features.flatoffers.adapter.OnPhotoChangeListener
import com.example.n1pagination.presentation.features.flatoffers.adapter.PhotoViewPagerAdapter
import java.text.NumberFormat

class FlatOfferActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_OFFER = "EXTRA_OFFER"
    }

    private lateinit var binding: ActivityCurrentOfferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_current_offer)
        val offer = intent.extras?.getSerializable(EXTRA_OFFER)
        if (offer is FlatOffer) {
            binding.tvAddress.text = getString(
                R.string.address_template,
                offer.roomsCount, offer.streetName, offer.houseNumber
            )
            binding.tvPrice.text = getString(R.string.price_template, NumberFormat.getInstance().format(offer.price))
            binding.tvFloor.text = getString(R.string.floors_template, offer.floor, offer.floorsCount)
            binding.tvArea.text = getString(
                R.string.area_template,
                offer.totalAreaInMeters,
                offer.livingAreaInMeters,
                offer.kitchenAreaInMeters
            )
            if (offer.photos.isNotEmpty()) {
                binding.cvPhotoNumberHolder.visibility = View.VISIBLE
                binding.vpPhotos.visibility = View.VISIBLE
                binding.ivNoPhoto.visibility = View.GONE
                binding.vpPhotos.adapter = PhotoViewPagerAdapter(offer.photos)
                binding.vpPhotos.addOnPageChangeListener(OnPhotoChangeListener {
                    binding.tvPhotoNumber.text =
                        getString(R.string.photos_number_template, it + 1, offer.photos.size)
                })
                binding.tvPhotoNumber.text =
                    getString(R.string.photos_number_template, 1, offer.photos.size)
            } else {
                binding.cvPhotoNumberHolder.visibility = View.GONE
                binding.vpPhotos.visibility = View.INVISIBLE
                binding.ivNoPhoto.visibility = View.VISIBLE
            }
        } else {
            finish()
        }
    }
}