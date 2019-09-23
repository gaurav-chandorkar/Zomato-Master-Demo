package com.grv.gauravtest.network.response.geolocation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal class Location_ {

    @SerializedName("address")
    @Expose
     val address: String? = null
    @SerializedName("locality")
    @Expose
     val locality: String? = null
    @SerializedName("city")
    @Expose
    private val city: String? = null



}
