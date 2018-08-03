package com.zhcet.zhcetnavigationapp;

/**
 * Created by Shantanu on 21-11-2017.
 */

public class TeacherStatus {


    private String status="Not Active";
    public TeacherStatus() {
    }

    public TeacherStatus(String str){
        status=str;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
