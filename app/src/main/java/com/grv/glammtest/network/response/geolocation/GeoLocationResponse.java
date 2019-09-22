package com.grv.glammtest.network.response.geolocation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeoLocationResponse {




@SerializedName("nearby_restaurants")
@Expose
private List<NearbyRestaurant> nearbyRestaurants = null;



public List<NearbyRestaurant> getNearbyRestaurants() {
return nearbyRestaurants;
}

public void setNearbyRestaurants(List<NearbyRestaurant> nearbyRestaurants) {
this.nearbyRestaurants = nearbyRestaurants;
}

}