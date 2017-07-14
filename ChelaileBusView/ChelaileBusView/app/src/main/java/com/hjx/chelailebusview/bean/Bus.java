
package com.hjx.chelailebusview.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bus {

    @SerializedName("acBus")
    @Expose
    private int acBus;
    @SerializedName("beforeBaseIndex")
    @Expose
    private int beforeBaseIndex;
    @SerializedName("beforeLat")
    @Expose
    private int beforeLat;
    @SerializedName("beforeLng")
    @Expose
    private int beforeLng;
    @SerializedName("busBaseIndex")
    @Expose
    private int busBaseIndex;
    @SerializedName("busId")
    @Expose
    private String busId;
    @SerializedName("delay")
    @Expose
    private int delay;
    @SerializedName("distanceToSc")
    @Expose
    private int distanceToSc;
    @SerializedName("lat")
    @Expose
    private float lat;
    @SerializedName("licence")
    @Expose
    private String licence;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("lng")
    @Expose
    private float lng;
    @SerializedName("mTicket")
    @Expose
    private int mTicket;
    @SerializedName("order")
    @Expose
    private int order;
    @SerializedName("rType")
    @Expose
    private int rType;
    @SerializedName("shareId")
    @Expose
    private String shareId;
    @SerializedName("state")
    @Expose
    private int state;
    @SerializedName("syncTime")
    @Expose
    private int syncTime;
    @SerializedName("travels")
    @Expose
    private List<Travel> travels = null;

    public int getAcBus() {
        return acBus;
    }

    public void setAcBus(int acBus) {
        this.acBus = acBus;
    }

    public int getBeforeBaseIndex() {
        return beforeBaseIndex;
    }

    public void setBeforeBaseIndex(int beforeBaseIndex) {
        this.beforeBaseIndex = beforeBaseIndex;
    }

    public int getBeforeLat() {
        return beforeLat;
    }

    public void setBeforeLat(int beforeLat) {
        this.beforeLat = beforeLat;
    }

    public int getBeforeLng() {
        return beforeLng;
    }

    public void setBeforeLng(int beforeLng) {
        this.beforeLng = beforeLng;
    }

    public int getBusBaseIndex() {
        return busBaseIndex;
    }

    public void setBusBaseIndex(int busBaseIndex) {
        this.busBaseIndex = busBaseIndex;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDistanceToSc() {
        return distanceToSc;
    }

    public void setDistanceToSc(int distanceToSc) {
        this.distanceToSc = distanceToSc;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public int getMTicket() {
        return mTicket;
    }

    public void setMTicket(int mTicket) {
        this.mTicket = mTicket;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getRType() {
        return rType;
    }

    public void setRType(int rType) {
        this.rType = rType;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(int syncTime) {
        this.syncTime = syncTime;
    }

    public List<Travel> getTravels() {
        return travels;
    }

    public void setTravels(List<Travel> travels) {
        this.travels = travels;
    }

}
