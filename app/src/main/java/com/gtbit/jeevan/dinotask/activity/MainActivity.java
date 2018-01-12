package com.gtbit.jeevan.dinotask.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.gtbit.jeevan.dinotask.ApiService;
import com.gtbit.jeevan.dinotask.R;
import com.gtbit.jeevan.dinotask.Utility;
import com.gtbit.jeevan.dinotask.adapters.MyPostAdapter;
import com.gtbit.jeevan.dinotask.modelClass.PostData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    List<PostData> postDataList;
    ProgressDialog progressDialog;
    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecyclerView = findViewById(R.id.myRecyclerView);

        retrofit = Utility.getRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<PostData>> call = apiService.getPosts();
        showProgressDialog();
        if (!isOnline()) {
            progressDialog.dismiss();
            Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }

        call.enqueue(new Callback<List<PostData>>() {
            @Override
            public void onResponse(Call<List<PostData>> call, Response<List<PostData>> response) {
                postDataList = response.body();
                updateView(postDataList);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<PostData>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMax(80);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    private void updateView(List<PostData> postDataList) {
        MyPostAdapter adapter = new MyPostAdapter(postDataList, MainActivity.this);
        myRecyclerView.setAdapter(adapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
