package com.grv.glammtest.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.grv.glammtest.R
import com.grv.glammtest.TouchActionDelegates
import com.grv.glammtest.database.RestaurantEntity
import kotlinx.android.synthetic.main.item_restaurant.view.*
import java.net.URI
import java.net.URL

class RestaurantAdapter(
    var restaurantList: MutableList<RestaurantEntity>,
    var touchActionDelegate: TouchActionDelegates
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TAG = "RestaurantAdapter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        RestaurantViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_restaurant,
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RestaurantViewHolder).apply {
            setData(position)
        }
    }

    fun updateList(list: List<RestaurantEntity>) {
        restaurantList.clear()
        restaurantList.addAll(list)
        notifyDataSetChanged()
    }

    inner class RestaurantViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

        fun setData(position: Int) {
            var restaurant = restaurantList.get(position)
            view.img_thumb
            view.tv_hotel_name.text = restaurant.name
            view.tv_address.text = restaurant.address

            Log.e(TAG, "url ${restaurant.thumb}")
            Glide.with(view).load(URL(restaurant.thumb)).apply(requestOptions).into(view.img_thumb)
        }
    }

}