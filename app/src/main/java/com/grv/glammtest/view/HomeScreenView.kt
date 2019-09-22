package com.grv.glammtest.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.grv.glammtest.TouchActionDelegates
import com.grv.glammtest.adapter.RestaurantAdapter
import com.grv.glammtest.database.RestaurantEntity
import kotlinx.android.synthetic.main.activity_home_screen.view.*

class HomeScreenView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var touchActionDelegate: TouchActionDelegates
    lateinit var adapter: RestaurantAdapter
    fun initView(touchActionDelegate: TouchActionDelegates) {
        this.touchActionDelegate = touchActionDelegate

        setUpView()
    }

    private fun setUpView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RestaurantAdapter(
            restaurantList = mutableListOf(),
            touchActionDelegate = touchActionDelegate
        )
        recyclerView.adapter = adapter
    }

    fun updateList(list: MutableList<RestaurantEntity>) {
        adapter.updateList(list)

    }

    fun closeProgress() {
        progress_bar.visibility= View.GONE
        tv_data_loading_text.visibility= View.GONE

    }
}