package com.example.network_load_framework;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2017/8/17.
 */

public interface RetrofitApi {

    @GET("guide.json")
    Observable<DataBean> getObservable();
}
