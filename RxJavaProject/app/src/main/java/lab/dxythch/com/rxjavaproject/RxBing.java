package lab.dxythch.com.rxjavaproject;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * 项目名称：RxJavaProject
 * 类描述：
 * 创建人：oden
 * 创建时间：2018/1/26
 */
public class RxBing {

    //防抖
    public static void throttleFirst(View v, Consumer<? super Object> subscriber) {
        throttleFirst(v, subscriber, 2);
    }

    //防抖 自定义时间
    public static void throttleFirst(View v, Consumer<? super Object> subscriber, int time) {
        RxView.clicks(v)
                .throttleFirst(time, TimeUnit.SECONDS)
                .subscribe(subscriber);
    }

    public static void longClick(View v, Consumer<? super Object> subscriber) {
        RxView.longClicks(v)
                .subscribe(subscriber);

    }


}
