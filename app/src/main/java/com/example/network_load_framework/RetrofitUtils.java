package com.example.network_load_framework;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/8/17.
 */

public class RetrofitUtils {
    private static Retrofit retrofit;

    private RetrofitUtils(){

    }

    public static Retrofit getInstance(String url){
        if(retrofit==null){
            synchronized (Retrofit.class){
                if(retrofit==null){
                    retrofit=new Retrofit.Builder().baseUrl(url)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }

}
