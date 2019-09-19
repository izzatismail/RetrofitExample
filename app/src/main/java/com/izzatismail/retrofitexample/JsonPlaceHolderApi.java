package com.izzatismail.retrofitexample;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    //This is to write data into the JSON file. Body refers to the data type that we want to write
    @POST("posts")
    Call<Post> createPost(@Body Post post);

    //Send as URL Encoded rather than JSON
    //Eg. userId=23&title=New%20Title&body=New%20Text
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    //If want to use Map, same as GET
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> fields);

    //PUT and PATCH updates the JSON file to One(usually) path (ie. posts/1)
    //PUT completely replace existing resource. Need to send full object.
    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id,
                       @Body Post post);

    //PATCH only changes the field we sent.
    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id,
                       @Body Post post);

    //To delete JSON resource
    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
