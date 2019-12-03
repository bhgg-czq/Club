package com.czq.club;

import java.io.Serializable;

public class BeanMyclub implements Serializable {
    private String myclub_name;     //社团名称
    private int myclub_logo;        //社团logo
    private String describtion;     //社团简介
    private int member_count;       //社团人数
    private String things;          //社团事迹

    public int getMyclub_logo() {
        return myclub_logo;
    }

    public void setMyclub_logo(int myclub_logo) {
        this.myclub_logo = myclub_logo;
    }

    public void setMyclub_name(String myclub_name) {
        this.myclub_name = myclub_name;
    }

    public int getMember_count() {
        return member_count;
    }

    public String getDescribtion() {
        return describtion;
    }

    public String getThings() {
        return things;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public void setMember_count(int member_count) {
        this.member_count = member_count;
    }

    public void setThings(String things) {
        this.things = things;
    }

    public String getMyclub_name() {
        return myclub_name;
    }
    public BeanMyclub(String myclub_name,int myclub_logo,String describtion,int member_count,String things){
        this.myclub_logo=myclub_logo;
        this.myclub_name=myclub_name;
        this.describtion=describtion;
        this.member_count=member_count;
        this.things=things;
    }

}
