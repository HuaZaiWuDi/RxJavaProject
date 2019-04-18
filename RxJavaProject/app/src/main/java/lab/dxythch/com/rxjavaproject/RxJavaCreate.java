package lab.dxythch.com.rxjavaproject;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 项目名称：RxJavaProject
 * 类描述：
 * 创建人：oden
 * 创建时间：2018/1/26
 */
public class RxJavaCreate {


    /**
     * Create  创建一个Observable 被观察的对象
     * <p>
     * <p>
     * just 快速创建1个被观察者对象（Observable） 发送特定的事件：直接发送 传入的事件(10个以下)
     * <p>
     * <p>
     * fromArray  发送事件的特点：直接发送 传入的数组数据 会遍历发送数据里面的每个元素？事实上并没有遍历？
     * <p>
     * formIterable  发送事件的特点：直接发送 传入的集合List数据
     * <p>
     * empty 创建后只会调用onComplete,
     * <p>
     * error 会直接调用onError
     * <p>
     * never 不发生任何事件，什么也不会调用
     * <p>
     * defer 延迟创建.只有当绑定subscribe 时Observable才会被创建
     * <p>
     * timer  发送事件的特点：延迟指定时间后，发送1个数值（Long类型）
     * <p>
     * <p>
     * interval 指定间隔内，从0开始不无递增的发送一个long型
     * <p>
     * intervalRange // 参数1 = 事件序列起始点；// 参数2 = 事件数量；// 参数3 = 第1次事件延迟发送时间；// 参数4 = 间隔时间数字单位；// 参数5 = 时间单位
     * <p>
     * range 发送指定范围的数据
     */

    String TAG = this.getClass().getSimpleName();

    public RxJavaCreate() {


        int[] ints = {1, 2, 3, 4, 5, 6};

        Observable.fromArray(ints)
                .subscribe(new Consumer<int[]>() {
                    @Override
                    public void accept(int[] ints) throws Exception {
                        Log.d(TAG, "fromArray:" + Arrays.toString(ints));
                    }
                });

        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);
        lists.add(4);


        Observable.fromIterable(lists)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "fromIterable:" + integer);
                    }
                });

        Observable.empty().subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG, "empty:onNext");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "empty:onComplete");
            }
        });


        Observable<Object> defer = Observable.defer(new Callable<ObservableSource<?>>() {
            @Override
            public ObservableSource<?> call() throws Exception {
                return Observable.just(i);
            }
        });

        i = 20;

        defer.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d(TAG, "defer:" + o);
            }
        });

    }

    int i = 0;

    boolean isConnect = false;

    private void isConnect(Test test) {
        if (isConnect) {
            test.success();
        } else {

        }
    }

    interface Test {
        void success();
    }

    private void aVoid() {
        isConnect(new Test() {
            @Override
            public void success() {

            }
        });
    }

}


