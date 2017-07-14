
package com.hjx.chelailebusview.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusDetail {

    @SerializedName("targetOrder")
    @Expose
    private int targetOrder;
    @SerializedName("feed")
    @Expose
    private int feed;
    @SerializedName("depDesc")
    @Expose
    private String depDesc;
    @SerializedName("buses")
    @Expose
    private List<Bus> buses = null;
    @SerializedName("line")
    @Expose
    private Line line;
    @SerializedName("roads")
    @Expose
    private List<List<Road>> roads = null;
    @SerializedName("depTable")
    @Expose
    private int depTable;
    @SerializedName("fav")
    @Expose
    private int fav;
    @SerializedName("notify")
    @Expose
    private int notify;

    public int getTargetOrder() {
        return targetOrder;
    }

    public void setTargetOrder(int targetOrder) {
        this.targetOrder = targetOrder;
    }

    public int getFeed() {
        return feed;
    }

    public void setFeed(int feed) {
        this.feed = feed;
    }

    public String getDepDesc() {
        return depDesc;
    }

    public void setDepDesc(String depDesc) {
        this.depDesc = depDesc;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public List<List<Road>> getRoads() {
        return roads;
    }

    public void setRoads(List<List<Road>> roads) {
        this.roads = roads;
    }

    public int getDepTable() {
        return depTable;
    }

    public void setDepTable(int depTable) {
        this.depTable = depTable;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public int getNotify() {
        return notify;
    }

    public void setNotify(int notify) {
        this.notify = notify;
    }

}
