package com.github.okhttprequestbodylengthbug;

import android.support.annotation.NonNull;

import com.squareup.okhttp.RequestBody;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.POST;

public class DummyClient {

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://www.google.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public interface IDummyService {

        @POST("/dummy/path")
        Call<Void> dummyRequest(@NonNull @Body RequestBody body);
    }
}
