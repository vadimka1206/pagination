package com.example.n1pagination.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.n1pagination.data.model.FlatOffer
import com.example.n1pagination.presentation.features.offer.FlatOfferActivity

class Router {

    companion object {

        fun navigateToOfferCard(context: Context, offer: FlatOffer) {
            val intent = Intent(context, FlatOfferActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable(FlatOfferActivity.EXTRA_OFFER, offer)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
}