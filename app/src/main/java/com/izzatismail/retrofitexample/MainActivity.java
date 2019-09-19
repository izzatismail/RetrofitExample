package com.izzatismail.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.text_view_result);

        Gson gson = new GsonBuilder().serializeNulls().create(); //If you want Gson to drop null value (insert null value into the server)

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class); //Retrofit will take care of the implementation

        //getPosts();
        //getComments();
        //createPost();
        //updatePost();
        deletePost();
    }

    private void getComments() {
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments("posts/1/comments"); //Can use URL to get the JSON

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()){
                    //If not successful response (ERROR 404 for example)
                    textResult.setText("Code : " + response.code());
                    return;
                }

                List<Comment> comments = response.body();

                for(Comment comment : comments){
                    String content = "";
                    content += "ID : " + comment.getId() + "\n";
                    content += "Post ID : " + comment.getPostId() + "\n";
                    content += "Name : " + comment.getName() + "\n";
                    content += "Email : " + comment.getEmail() + "\n";
                    content += "Body : " + comment.getText() + "\n\n";

                    textResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textResult.setText(t.getMessage());
            }
        });
    }

    private void getPosts(){
        //For any combination
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "1");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(parameters);
        //Call<List<Post>> call = jsonPlaceHolderApi.getPosts(new Integer[]{2,3,6}, "id", "desc"); //use null if you don't want to be specific

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    //If not successful response (ERROR 404 for example)
                    textResult.setText("Code : " + response.code());
                    return;
                }

                List<Post> posts = response.body(); //.body() will return the list of Posts

                for(Post post : posts){
                    String content = "";
                    content += "ID : " + post.getId() + "\n";
                    content += "User ID : " + post.getUserId() + "\n";
                    content += "Title : " + post.getTitle() + "\n";
                    content += "Body : " + post.getText() + "\n\n";

                    textResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textResult.setText(t.getMessage());
            }
        });
    }

    public void createPost(){
        Post post = new Post(23, "Insert Title Here", "Insert Text Here");

        //For any combination, similar to GET
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "25");
        parameters.put("title", "New Title");

        Call<Post> callJson = jsonPlaceHolderApi.createPost(post);
        Call<Post> callUrlEncoded = jsonPlaceHolderApi.createPost(23, "Insert Title Here", "Insert Text Here");
        Call<Post> callUrlMap  = jsonPlaceHolderApi.createPost(parameters);

        callUrlMap.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    //If not successful response (ERROR 404 for example)
                    textResult.setText("Code : " + response.code());
                    return;
                }

                Post postRespones = response.body();

                String content = "";
                content += "Code : " + response.code() + "\n";
                content += "ID : " + postRespones.getId() + "\n";
                content += "User ID : " + postRespones.getUserId() + "\n";
                content += "Title : " + postRespones.getTitle() + "\n";
                content += "Text : " + postRespones.getText() + "\n\n";

                textResult.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textResult.setText(t.getMessage());
            }
        });
    }

    private void updatePost(){
        Post post = new Post(12, null, "New Text");

        Call<Post> callPut = jsonPlaceHolderApi.putPost(5, post);
        Call<Post> callPatch = jsonPlaceHolderApi.patchPost(5, post);

        callPatch.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    //If not successful response (ERROR 404 for example)
                    textResult.setText("Code : " + response.code());
                    return;
                }

                Post postRespones = response.body();

                String content = "";
                content += "Code : " + response.code() + "\n";
                content += "ID : " + postRespones.getId() + "\n";
                content += "User ID : " + postRespones.getUserId() + "\n";
                content += "Title : " + postRespones.getTitle() + "\n";
                content += "Text : " + postRespones.getText() + "\n\n";

                textResult.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textResult.setText(t.getMessage());
            }
        });
    }

    public void deletePost(){
        Call<Void> call = jsonPlaceHolderApi.deletePost(5);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textResult.setText("Code : " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textResult.setText(t.getMessage());
            }
        });
    }
}
