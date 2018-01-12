package com.gtbit.jeevan.dinotask.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gtbit.jeevan.dinotask.Adapters.MyCommentAdapter;
import com.gtbit.jeevan.dinotask.ApiService;
import com.gtbit.jeevan.dinotask.ModelClass.CommentData;
import com.gtbit.jeevan.dinotask.R;
import com.gtbit.jeevan.dinotask.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailActivity extends AppCompatActivity {

    Retrofit retrofit;
    RecyclerView recyclerView;
    private int postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        postId = getIntent().getIntExtra("postId", 0);
        recyclerView = findViewById(R.id.commentRecycleView);

        retrofit = Utility.getRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<CommentData>> listCall = apiService.getComments();

        listCall.enqueue(new Callback<List<CommentData>>() {
            @Override
            public void onResponse(Call<List<CommentData>> call, Response<List<CommentData>> response) {
                List<CommentData> commentDataList = response.body();
                updateView(commentDataList, postId);
            }

            @Override
            public void onFailure(Call<List<CommentData>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void updateView(List<CommentData> commentDataList, int postId) {
        List<CommentData> commentList = new ArrayList<>();//filtered comments with same postId
        for (CommentData commentData : commentDataList) {
            if (commentData.getPostId() == postId)
                commentList.add(commentData);
        }
        MyCommentAdapter adapter = new MyCommentAdapter(commentList,this, postId);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}