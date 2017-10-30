package com.hooooong.firebasebasic.model;

/**
 * Created by Android Hong on 2017-10-30.
 */

public class Bbs {

    private String id;
    private String title;
    private String content;
    private String date;
    private String user_id;

    public Bbs() {
        // Firebase parsing
    }

    public Bbs(String title, String content, String date, String user_id) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
