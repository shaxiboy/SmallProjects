
package com.hjx.chelailebusview.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Station {

    @SerializedName("distanceToSp")
    @Expose
    private int distanceToSp;
    @SerializedName("metros")
    @Expose
    private List<Metro> metros = null;
    @SerializedName("order")
    @Expose
    private int order;
    @SerializedName("sId")
    @Expose
    private String sId;
    @SerializedName("sn")
    @Expose
    private String sn;

    public int getDistanceToSp() {
        return distanceToSp;
    }

    public void setDistanceToSp(int distanceToSp) {
        this.distanceToSp = distanceToSp;
    }

    public List<Metro> getMetros() {
        return metros;
    }

    public void setMetros(List<Metro> metros) {
        this.metros = metros;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getSId() {
        return sId;
    }

    public void setSId(String sId) {
        this.sId = sId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

}
