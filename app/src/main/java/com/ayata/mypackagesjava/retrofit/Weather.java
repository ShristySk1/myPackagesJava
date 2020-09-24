
package com.ayata.mypackagesjava.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("max")
    @Expose
    private String max;
    @SerializedName("min")
    @Expose
    private String min;
    @SerializedName("rain")
    @Expose
    private String rain;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

}
