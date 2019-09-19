package com.izzatismail.retrofitexample;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int userId;
    private Integer id; //When we don't define the id (null), Retrofit will not include id into the JSON file
    private String title;

    //Use SerializedName if you want to use different naming from the JSON file.
    @SerializedName("body")
    private String text;

    public Post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
        //Id will be generated automatically by Retrofit
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
