package com.hooooong.firebasebasic.model;

import com.google.firebase.database.Exclude;

import java.util.List;

/**
 * Created by Android Hong on 2017-10-30.
 */

public class User {

    private String user_id;
    private String username;
    private int age;
    private String email;

    @Exclude // database field 에서 제외하고 싶을 때 사용
    private boolean check = false;

    private List<Bbs> bbsList;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)

        // getValue 를 호출할 때 기본 생성자를 호출하기 떄문에
        // 무조건 하나 생성해야 한다.
    }

    public User(String username,int age, String email) {
        this.username = username;
        this.age = age;
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Bbs> getBbsList() {
        return bbsList;
    }

    public void setBbsList(List<Bbs> bbsList) {
        this.bbsList = bbsList;
    }
}
