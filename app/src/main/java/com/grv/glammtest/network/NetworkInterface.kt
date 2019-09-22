package com.grv.gauravtest.network

import com.grv.glammtest.network.response.geolocation.GeoLocationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NetworkInterface {

    @Headers("user_key: d1dbbb2e0cd77f6c7ef7479043fc6cdd")
    @GET("geocode")
    fun getRestorantByGEO(
        @Query("lat") queryParameters1: String,
        @Query("lon") queryParams2: String
    ): Call<GeoLocationResponse>


}