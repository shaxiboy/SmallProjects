
package com.hjx.chelailebusview.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Otherline {

    @SerializedName("lineId")
    @Expose
    private String lineId;

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

}
