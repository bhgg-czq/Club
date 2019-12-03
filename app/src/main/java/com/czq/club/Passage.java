package com.czq.club;

public class Passage {
    private Club club;
    private String time;

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public Passage(Club club,String time,String title){
        this.club = club;
        this.time = time;
        this.title = title;
    }
}
