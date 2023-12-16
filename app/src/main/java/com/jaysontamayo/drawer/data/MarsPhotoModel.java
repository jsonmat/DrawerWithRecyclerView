package com.jaysontamayo.drawer.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarsPhotoModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("img_src")
    @Expose
    private String imgSrc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

}