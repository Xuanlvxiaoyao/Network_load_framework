package com.example.network_load_framework;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/17.
 */

public class PostActivity extends Activity {
    private Button btn;
    private String url="https://Xuanlvxiaoyao.github.io/";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        btn= (Button) findViewById(R.id.btn);

        //使用Retrofit+rxjava下载数据
        Retrofit retrofit=RetrofitUtils.getInstance(url);
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        final Observable<DataBean> observable = retrofitApi.getObservable();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              observable.subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribe(new Action1<DataBean>() {
                          @Override
                          public void call(DataBean dataBean) {
                              //使用Evetbus传递数据
                              EventBus.getDefault().post(dataBean);
                          }
                      });
            }
        });
    }
}
