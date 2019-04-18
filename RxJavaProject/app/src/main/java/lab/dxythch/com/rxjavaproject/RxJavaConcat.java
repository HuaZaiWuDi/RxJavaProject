package lab.dxythch.com.rxjavaproject;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * 项目名称：RxJavaProject
 * 类描述：
 * 创建人：oden
 * 创建时间：2018/1/24
 */
public class RxJavaConcat {

    String TAG = this.getClass().getSimpleName();

    /**
     * concat and concatArray  组合多个被观察者一起发送数据，合并后 按发送顺序串行执行
     * <p>
     * 区别 concat 被观察的数量小于等于4个，concatArray大于4个
     * <p>
     * merge and mergeArray   组合多个被观察者一起发送数据，合并后 按时间线并行执行
     * <p>
     * 二者区别：组合被观察者的数量，即merge（）组合被观察者数量≤4个，而mergeArray（）则可＞4个
     * 区别上述concat（）操作符：同样是组合多个被观察者一起发送数据，但concat（）操作符合并后是按发送顺序串行执行
     * <p>
     * concatDelayError（） / mergeDelayError（）
     */

    public RxJavaConcat() {


        //最大为4个被观察着（Observable）
        Observable.concat(Observable.just(1, 2, 3),
                Observable.just(22, 434, 54),
                Observable.just(232, 532, 6534),
                Observable.just(3, 2, 1)
        )
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "accept: " + integer);
                    }
                });

        /**
         * 结果：
         * 01-24 11:56:29.739 26218-26218/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 1
         01-24 11:56:29.739 26218-26218/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 2
         01-24 11:56:29.739 26218-26218/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 3
         01-24 11:56:29.739 26218-26218/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 22
         01-24 11:56:29.739 26218-26218/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 434
         01-24 11:56:29.739 26218-26218/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 54
         01-24 11:56:29.739 26218-26218/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 232
         01-24 11:56:29.739 26218-26218/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 532
         01-24 11:56:29.739 26218-26218/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 6534
         01-24 11:56:29.739 26218-26218/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 3
         01-24 11:56:29.739 26218-26218/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 2
         01-24 11:56:29.739 26218-26218/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 1
         * */


        Observable.merge(Observable.intervalRange(0, 3, 4, 3, TimeUnit.SECONDS),
                Observable.intervalRange(3, 6, 6, 3, TimeUnit.SECONDS))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {

                        Log.d(TAG, "accept: " + aLong);
                    }
                });

        /**
         * 结果：
         * 01-24 14:13:59.799 22771-22831/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 0
         01-24 14:14:01.809 22771-22832/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 3
         01-24 14:14:02.799 22771-22831/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 1
         01-24 14:14:04.809 22771-22832/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 4
         01-24 14:14:05.799 22771-22831/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 2
         01-24 14:14:07.799 22771-22832/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 5
         01-24 14:14:10.799 22771-22832/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 6
         01-24 14:14:13.799 22771-22832/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 7
         01-24 14:14:16.799 22771-22832/lab.dxythch.com.rxjavaproject D/RxJavaConcat: accept: 8
         *
         *
         * */


        //RxJava的内部生命周期
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(2);
                e.onError(new Throwable("我是异常"));
            }
            //1. 当Observable每发送1次数据事件就会调用1次
        }).doOnEach(new Consumer<Notification<Integer>>() {
            @Override
            public void accept(Notification<Integer> integerNotification) throws Exception {
                Log.d(TAG, integerNotification.getValue() + "");
            }
        }).doOnEach(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "doOnEach: onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "doOnEach: onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "doOnEach: onComplete");
            }
        }).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "doOnNext: " + integer);
            }
        }).doAfterNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "doAfterNext: " + integer);
            }
        }).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                Log.d(TAG, "doOnComplete");
            }
        }).doAfterTerminate(new Action() {
            @Override
            public void run() throws Exception {
                Log.d(TAG, "doAfterTerminate");
            }
        }).doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "doOnError");
            }
        }).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                Log.d(TAG, "doFinally");
            }
        }).doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                Log.d(TAG, "doOnDispose");
            }
        }).doOnLifecycle(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                Log.d(TAG, "doOnLifecycle:accept");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.d(TAG, "doOnLifecycle:Action");
            }
        }).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                Log.d(TAG, "doOnSubscribe");
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> apply(Throwable throwable) throws Exception {
                Log.d(TAG, "onErrorResumeNext");
                return Observable.error(new Exception("处理异常又发送一个异常"));
            }
        }).onExceptionResumeNext(new Observable<Integer>() {
            @Override
            protected void subscribeActual(Observer<? super Integer> observer) {
                Log.d(TAG, "onExceptionResumeNext:有一个Exception");
                observer.onError(new Exception("我又来了一个异常"));
            }
            //重试，即当出现错误时，让被观察者（Observable）重新发射数据 ,重新发送次数，以及是否重新发送
        }).retry(2, new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {
                //true重新发送，flase不发送
                return false;
            }
            //拦截一个异常并且返回一个常数
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Exception {
                Log.d(TAG, "onErrorReturn");
                return 0;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "subscribe:" + integer);
            }
        });


        /**
         * 结果：
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: doOnLifecycle:accept
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: doOnSubscribe
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: 1
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: doOnEach: onNext:1
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: doOnNext: 1
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: subscribe:1
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: doAfterNext: 1
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: 2
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: doOnEach: onNext:2
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: doOnNext: 2
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: subscribe:2
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: doAfterNext: 2
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: 2
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: doOnEach: onNext:2
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: doOnNext: 2
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: subscribe:2
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: doAfterNext: 2
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: null
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: doOnError
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: onErrorResumeNext
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: onErrorReturn
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: subscribe:0
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: doFinally
         01-24 20:14:31.969 8546-8546/lab.dxythch.com.rxjavaproject D/RxJavaConcat: doAfterTerminate
         *
         * */


    }
}
