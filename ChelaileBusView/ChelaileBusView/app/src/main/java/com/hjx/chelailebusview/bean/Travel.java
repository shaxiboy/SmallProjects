
package com.hjx.chelailebusview.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Travel {

    @SerializedName("arrivalTime")
    @Expose
    private long arrivalTime;
    @SerializedName("order")
    @Expose
    private int order;
    @SerializedName("pRate")
    @Expose
    private int pRate;
    @SerializedName("travelTime")
    @Expose
    private int travelTime;

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getPRate() {
        return pRate;
    }

    public void setPRate(int pRate) {
        this.pRate = pRate;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

}
