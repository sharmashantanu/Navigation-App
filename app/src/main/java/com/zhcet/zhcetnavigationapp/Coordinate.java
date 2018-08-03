package com.zhcet.zhcetnavigationapp;

import java.io.Serializable;

/**
 * Created by prashant on 13/11/17.
 */

public class Coordinate implements Serializable{

    private String Latitude;
    private String Longitude;
    private String name;

    public Coordinate(){

    }
   public Coordinate(String name){
       this.name=name;
    }

    public Coordinate(String name,String Latitude,String Longitude){
        this.name=name;
        this.Latitude=Latitude;
        this.Longitude=Longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(String latitude) {
      this.  Latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.Longitude = longitude;
    }

    public String getLatitude(){
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

}
