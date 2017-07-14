
package com.hjx.chelailebusview.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metro {

    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("lineId")
    @Expose
    private String lineId;
    @SerializedName("lineNo")
    @Expose
    private String lineNo;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

}
