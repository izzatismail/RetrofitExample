package com.izzatismail.retrofitexample;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

//The name "JsonPlaceHolder" will change according to where you get JSON file from.
public interface JsonPlaceHolderApi {

    /*
    @GET("posts") //Get the relative URL, the one after the base URL (/)
    Call<List<Post>> getPosts(); */

    /* Without Sorting
    // for /posts?userId=1
    @GET("posts") //Get the relative URL, the one after the base URL (/)
    Call<List<Post>> getPosts(@Query("userId") int userId); //the annotation name "userId" must be same with the one JSON */

    // With Sorting
    @GET("posts") //Get the relative URL, the one after the base URL (/)
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] userId, //If you want to query more than 1 userId
            @Query("_sort") String sort,
            @Query("_order") String order
    ); //the annotation name "userId" must be same with the one JSON

    //For any combination of query
    @GET("posts")
    Call<List<Post>> getPosts(
            @QueryMap Map<String, String> parameters
            );

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId); //the annotation name "id" must be same with the one in the @GET {}

    @GET
    Call<List<Comment>> getComments(@Url String url); //If you want to GET by url
}
