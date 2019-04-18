package lab.dxythch.com.rxjavaproject;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * 项目名称：RxJavaProject
 * 类描述：
 * 创建人：oden
 * 创建时间：2018/1/26
 */
public class RxJavaBoolean {


    /**
     * all()  有所数据是否都满足某一条件
     * <p>
     * exists()  是否存在
     * <p>
     * contains()  是否包含
     * <p>
     * isEmpty()  是否为空
     * <p>
     * amb() 当需要发送多个 Observable时，只发送 先发送数据的Observable的数据，而其余 Observable则被丢弃。
     * <p>
     * takeWhile  若发送的数据满足该条件，则发送该项数据；否则不发送
     * <p>
     * takeUntil  执行到某个条件时，停止发送事件  为true就停止发送事件
     * <p>
     * sipWhile  直到该判断条件 = false时，才开始发送Observable的数据
     * <p>
     * skipUntil  执行到某个条件时，开始发送事件  为true就开始发送事件
     * <p>
     * defaultEmpty  在不发送任何有效事件（ Next事件）、仅发送了 Complete 事件的前提下，发送一个默认值
     * <p>
     * sequenceEqual 判定两个Observables需要发送的数据是否相同
     */

    String TAG = this.getClass().getSimpleName();

    public RxJavaBoolean() {

        Observable.just(1, 2, 4, 5, 6, 78)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer < 100;
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                Log.d(TAG, "accept: all:" + aBoolean);
            }
        });


        Observable.interval(1, TimeUnit.SECONDS)
                .skipUntil(Observable.timer(3, TimeUnit.SECONDS))
                .take(3)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "accept:skipUntil: " + aLong);
                    }
                });

        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                e.onComplete();
            }
        }).defaultIfEmpty(22)
                .isEmpty()
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d(TAG, "accept:skipUntil: " + o);
                    }
                });

    }
}
