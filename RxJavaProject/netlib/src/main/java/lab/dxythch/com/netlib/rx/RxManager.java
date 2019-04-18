package lab.dxythch.com.netlib.rx;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目名称：BleCar
 * 类描述：
 * 创建人：oden
 * 创建时间：2016/8/30 11:06
 */
public class RxManager {
    private static RxManager rxManager = null;

    String TAG = "[RxManager]";

    private RxManager() {

    }

    public synchronized static RxManager getInstance() {
        if (rxManager == null) {
            rxManager = new RxManager();
        }
        return rxManager;
    }

    public void doUnifySubscribe(Observable<HttpResult> observable, RxSubscriber<HttpResult> subscriber) {
        observable
                .map(new Function<HttpResult, HttpResult>() {
                    @Override
                    public HttpResult apply(HttpResult httpResult) throws Exception {
                        if (!httpResult.isStatus()) {
//                            L.d("运行异常");
                        }
                        return httpResult;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public <T> void doSubscribeT(Observable<HttpResultT<T>> observable, RxSubscriber<T> subscriber) {
        Observable<T> map = observable
                .map(new Function<HttpResultT<T>, T>() {
                    @Override
                    public T apply(HttpResultT<T> t) throws Exception {
                        if (!t.isStatus()) {
//                            L.d("运行异常");
                        }
                        return t.getData();
                    }
                });
        dofunSubscribe(map, subscriber);
    }


    public <T> void dofunSubscribe(Observable<T> observable, RxSubscriber<T> subscriber) {
        observable
                .throttleFirst(1, TimeUnit.SECONDS)  //两秒只发生第一时间
                .timeout(2, TimeUnit.SECONDS)   //两秒超时重新发送
                .retry(2)  //重试两次
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public <T> void doSubscribe(Observable<T> observable, RxSubscriber<T> subscriber) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public <T> void doSubscribe(Observable<T> observable, Consumer<T> subscriber) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public <T> void doIoSubscribe(Observable<T> observable, RxSubscriber<T> subscriber) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(subscriber);
    }

    public <T> void doIoSubscribe(Observable<T> observable, Consumer<T> subscriber) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(subscriber);
    }

    public <T> Observable<T> doSubscribe(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
