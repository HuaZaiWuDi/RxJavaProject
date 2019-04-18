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
 * 创建时间：2018/1/24
 */
public class RxJavaFilter {

    String TAG = this.getClass().getSimpleName();

    /**
     * 过滤操作符
     * <p>
     * throttleFirst（）/ throttleLast（）在某段时间内，只发送该段时间内第1次事件 / 最后1次事件
     * <p>
     * Sample（）在某段时间内，只发送该段时间内最新（最后）1次事件
     * <p>
     * throttleWithTimeout （） / debounce（）发送数据事件时，若2次发送事件的间隔＜指定时间，就会丢弃前一次的数据，直到指定时间内都没有新数据发射时才会发送后一次的数据
     * <p>
     * firstElement（） / lastElement（）仅选取第1个元素 / 最后一个元素
     * <p>
     * elementAt（）指定接收某个元素（通过 索引值 确定）
     * <p>
     * elementAtOrError（）在elementAt（）的基础上，当出现越界情况（即获取的位置索引 ＞ 发送事件序列长度）时，即抛出异常
     */


    public RxJavaFilter() {


        Observable.just(1, 2, "33", 4, 2, 1)
                .ofType(Integer.class)//过滤类型
                .skip(1)//跳过第几项
                .skipLast(1)//倒数第几项
                .distinct()//过滤事件序列中重复的事件 / 连续重复的事件过滤重复的事件
                .distinctUntilChanged()//连续重复事件
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer < 3;
                    }
                }).take(2)//接收者的数量
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "accept: " + integer);
                    }
                });


        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(222);
                e.onNext(333);
                e.onNext(444);
                e.onNext(555);
            }
        }).delay(2, TimeUnit.SECONDS)
                .debounce(2, TimeUnit.SECONDS)//防抖，防止连续发送事件
                .sample(2, TimeUnit.SECONDS)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "int : " + integer);
                    }
                });


    }


}
