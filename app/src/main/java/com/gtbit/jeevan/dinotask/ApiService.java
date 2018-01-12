package com.gtbit.jeevan.dinotask;

import com.gtbit.jeevan.dinotask.ModelClass.CommentData;
import com.gtbit.jeevan.dinotask.ModelClass.PostData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jeevan on 12/1/18.
 */

public interface ApiService {
    @GET("/posts")
    Call<List<PostData>> getPosts();

    @GET("/comments")
    Call<List<CommentData>> getComments();
}
