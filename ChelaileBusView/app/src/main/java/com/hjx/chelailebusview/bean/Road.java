
package com.hjx.chelailebusview.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Road {

    @SerializedName("TPC")
    @Expose
    private float tpc;
    @SerializedName("TVL")
    @Expose
    private int tvl;

    public float getTPC() {
        return tpc;
    }

    public void setTPC(float tPC) {
        this.tpc = tPC;
    }

    public int getTVL() {
        return tvl;
    }

    public void setTVL(int tVL) {
        this.tvl = tVL;
    }

    @Override
    public String toString() {
        return "{" + tpc +
                "," + tvl +
                '}';
    }
}
