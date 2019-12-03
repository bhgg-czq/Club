package com.czq.club;

public class BeanMyclub_passage {
    private int myclub_logo;
    private String myclub_name;
    private String time;
    private int passage_photo;
    private String passage_sumary;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPassage_sumary() {
        return passage_sumary;
    }

    public void setPassage_sumary(String passage_sumary) {
        this.passage_sumary = passage_sumary;
    }

    public int getPassage_photo() {
        return passage_photo;
    }

    public void setPassage_photo(int passage_photo) {
        this.passage_photo = passage_photo;
    }

    public String getMyclub_name() {
        return myclub_name;
    }

    public void setMyclub_name(String myclub_name) {
        this.myclub_name = myclub_name;
    }

    public int getMyclub_logo() {
        return myclub_logo;
    }

    public void setMyclub_logo(int myclub_logo) {
        this.myclub_logo = myclub_logo;
    }
    public BeanMyclub_passage(int myclub_logo,String myclub_name,String time,int passage_photo,String passage_sumary){
        this.myclub_logo=myclub_logo;
        this.myclub_name=myclub_name;
        this.passage_photo=passage_photo;
        this.passage_sumary=passage_sumary;
        this.time=time;
    }


}
