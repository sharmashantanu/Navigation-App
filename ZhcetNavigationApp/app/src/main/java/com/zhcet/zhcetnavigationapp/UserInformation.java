package com.zhcet.zhcetnavigationapp;

/**
 * Created by prashant on 4/11/17.
 */


public class UserInformation {

    private String name;
    private String email;
    private String latitude;
    private String longitude;
    private boolean status=false;
   // private String phone_num;


    public UserInformation(){

    }

    public UserInformation(String name, String email, String latitude, String longitude,boolean status) {
        this.status=status;
        this.name = name;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}