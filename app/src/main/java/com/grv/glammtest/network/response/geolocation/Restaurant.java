package com.grv.glammtest.network.response.geolocation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.grv.gauravtest.network.response.geolocation.Location_;

import java.util.List;

public class Restaurant {

    @SerializedName("id")
    @Expose
     public String id;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("location")
    @Expose
    private Location_ location;


    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("offers")
    @Expose
    private List<Object> offers = null;
    @SerializedName("opentable_support")
    @Expose
    private Integer opentableSupport;


    @SerializedName("thumb")
    @Expose
    private String thumb;

    @SerializedName("photos_url")
    @Expose
    private String photosUrl;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Location_ getLocation() {
        return location;
    }

    public void setLocation(Location_ location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Object> getOffers() {
        return offers;
    }

    public void setOffers(List<Object> offers) {
        this.offers = offers;
    }

    public Integer getOpentableSupport() {
        return opentableSupport;
    }

    public void setOpentableSupport(Integer opentableSupport) {
        this.opentableSupport = opentableSupport;
    }


    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }


    public String getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(String photosUrl) {
        this.photosUrl = photosUrl;
    }


}