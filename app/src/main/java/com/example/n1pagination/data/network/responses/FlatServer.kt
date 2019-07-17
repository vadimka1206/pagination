package com.example.n1pagination.data.network.responses

import com.google.gson.annotations.SerializedName

open class FlatServer {

    @SerializedName("_id")
    val id: Long = 0
    @SerializedName("photos")
    val photos: List<PhotoServer>? = null
    @SerializedName("params")
    val params: ParamsServer? = null

    open class ParamsServer {
        @SerializedName("price")
        val price: String = ""
        @SerializedName("rooms_count")
        val roomsCount: Int = 0
        @SerializedName("house_addresses")
        val houseAddresses: List<HouseAddressServer>? = null
        @SerializedName("total_area")
        val totalArea: Int = 0
        @SerializedName("living_area")
        val livingArea: Int = 0
        @SerializedName("kitchen_area")
        val kitchenArea: Int = 0
        @SerializedName("floors_count")
        val floorsCount: Int = 0
        @SerializedName("floor")
        val floor: Int = 0
    }

    open class PhotoServer{
        @SerializedName("360x270cp")
        var url : String = ""
    }

    open class HouseAddressServer{
        @SerializedName("street")
        var street: StreetServer? = null
        @SerializedName("house_number")
        var houseNumber: String? = ""
    }

    open class StreetServer{
        @SerializedName("name_raw_ru")
        var name: String = ""
        @SerializedName("abbr_raw_ru")
        var abbr: String = ""

    }
}