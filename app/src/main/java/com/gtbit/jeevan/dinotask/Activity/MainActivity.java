package com.gtbit.jeevan.dinotask.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gtbit.jeevan.dinotask.Adapters.MyPostAdapter;
import com.gtbit.jeevan.dinotask.ApiService;
import com.gtbit.jeevan.dinotask.ModelClass.PostData;
import com.gtbit.jeevan.dinotask.R;
import com.gtbit.jeevan.dinotask.Utility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    List<PostData> postDataList;
    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecyclerView = findViewById(R.id.myRecyclerView);

        retrofit = Utility.getRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<PostData>> call = apiService.getPosts();

        call.enqueue(new Callback<List<PostData>>() {
            @Override
            public void onResponse(Call<List<PostData>> call, Response<List<PostData>> response) {
                postDataList = response.body();
                updateView(postDataList);
            }

            @Override
            public void onFailure(Call<List<PostData>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void updateView(List<PostData> postDataList) {
        MyPostAdapter adapter = new MyPostAdapter(postDataList, MainActivity.this);
        myRecyclerView.setAdapter(adapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}
