
package com.hjx.chelailebusview.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Line {

    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("direction")
    @Expose
    private int direction;
    @SerializedName("endSn")
    @Expose
    private String endSn;
    @SerializedName("firstTime")
    @Expose
    private String firstTime;
    @SerializedName("lastTime")
    @Expose
    private String lastTime;
    @SerializedName("lineId")
    @Expose
    private String lineId;
    @SerializedName("lineNo")
    @Expose
    private String lineNo;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("shortDesc")
    @Expose
    private String shortDesc;
    @SerializedName("sortPolicy")
    @Expose
    private String sortPolicy;
    @SerializedName("startSn")
    @Expose
    private String startSn;
    @SerializedName("state")
    @Expose
    private int state;
    @SerializedName("stationsNum")
    @Expose
    private int stationsNum;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("version")
    @Expose
    private String version;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getEndSn() {
        return endSn;
    }

    public void setEndSn(String endSn) {
        this.endSn = endSn;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getSortPolicy() {
        return sortPolicy;
    }

    public void setSortPolicy(String sortPolicy) {
        this.sortPolicy = sortPolicy;
    }

    public String getStartSn() {
        return startSn;
    }

    public void setStartSn(String startSn) {
        this.startSn = startSn;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStationsNum() {
        return stationsNum;
    }

    public void setStationsNum(int stationsNum) {
        this.stationsNum = stationsNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
