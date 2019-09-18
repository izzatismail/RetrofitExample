package com.izzatismail.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//The name "JsonPlaceHolder" will change according to where you get JSON file from.
public interface JsonPlaceHolderApi {

    @GET("posts") //Get the relative URL, the one after the base URL (/)
    Call<List<Post>> getPosts();
}
