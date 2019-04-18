package lab.dxythch.com.rxjavaproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import lab.dxythch.com.netlib.net.RetrofitService;
import lab.dxythch.com.netlib.rx.NetManager;
import lab.dxythch.com.netlib.rx.RxManager;
import lab.dxythch.com.netlib.rx.RxSubscriber;

public class RxJavaTransformActivity extends AppCompatActivity {


    String TAG = "[" + this.getClass().getSimpleName() + "]";

    /**
     * RxJava2 ： https://www.jianshu.com/p/cd984dd5aae8--
     * https://www.jianshu.com/users/383970bef0a0/latest_articles
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView login = findViewById(R.id.textView);
//
//        RxView.clicks(login)
//                .throttleFirst(2, TimeUnit.SECONDS)
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//                        Log.d(TAG, "点击了按钮");
//                    }
//                });
//
//        RxBing.throttleFirst(login, new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Exception {
//
//            }
//        });


    }


    public void WecharLogin(View v) {
//        mIWXAPI = WXAPIFactory.createWXAPI(this, ShareManager.CONFIG.getWxId());


    }


    SsoHandler mSsoHandler;

    public void weiboLogin(View v) {

//        AuthInfo authInfo = new AuthInfo(this, WEIBO_LOGIN_KEY,
//                WEIBO_LOGIN_URL, WEIBO_LOGIN_SECRET);
//        mSsoHandler = new SsoHandler(this, authInfo);
//        mSsoHandler.authorize(new WeiboAuthListener() {
//            @Override
//            public void onComplete(Bundle bundle) {
//                Oauth2AccessToken accessToken = Oauth2AccessToken.parseAccessToken(bundle);
//                Log.d(TAG, "登录成功" + accessToken.toString());
//            }
//
//            @Override
//            public void onWeiboException(WeiboException e) {
//                Log.d(TAG, "登录失败" + e.toString());
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//        });

    }


    public void QQLogin(View v) {
//        mTencent.login(this, "all", loghinListener);
//        weiboLogin(v);
    }


    IUiListener loghinListener = new IUiListener() {
        @Override
        public void onComplete(Object o) {
            Log.d(TAG, "登录成功" + o.toString());
        }

        @Override
        public void onError(UiError uiError) {
            Log.d(TAG, "登录失败" + uiError.toString());
        }

        @Override
        public void onCancel() {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.handleResultData(data, loghinListener);
        if (mSsoHandler != null)
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Teansform();
//        new RxJavaConcat();
//        new RxJavaFilter();
//        new RxJavaBoolean();
//        new RxJavaCreate();

//               doBle(new byte[]{0x01, 0x02, 0x03}, new RxSubscriber<byte[]>() {
//            @Override
//            protected void _onNext(byte[] bytes) {
//                Log.w(TAG, "_onNext" + Arrays.toString(bytes));
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                Log.w(TAG, "onError" + e.getMessage());
//            }
//
//        });


//        doBleList(new byte[][]{new byte[]{0x01, 0x02, 0x03}, new byte[]{0x04, 0x05, 0x06},
//                new byte[]{0x07, 0x08, 0x09}, new byte[]{0x0a, 0x0b, 0x0c}}, new RxSubscriber<byte[]>() {
//            @Override
//            protected void _onNext(byte[] bytes) {
//                Log.w(TAG, "_onNext" + Arrays.toString(bytes));
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                Log.w(TAG, "onError" + e.getMessage());
//            }
//
//        });


//        Observable<Integer> retry = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                //模拟请求，返回是否成功
////                boolean writeSuccess = false;
////                if (writeSuccess)
//                emitter.onNext(1);
////                else
////                    emitter.onError(new Throwable(""));
//            }
//        });
//                .timeout(2, TimeUnit.SECONDS)  //两秒超时重新发送
//                .retry(2);//重试两次


        String date = formatData(new Date(), "yyyyMMddHHmmss");
//
//        【定位改变】:经度114.028365
//        04-14 11:38:04.007 31480-31480/com.embednet.wdluo.bleplatformsdkdemo D/syd: 【定位改变】:海拔0.0
//        04-14 11:38:04.007 31480-31480/com.embednet.wdluo.bleplatformsdkdemo D/syd: 【定位改变】:维度22.623243

        RetrofitService serviceAPI = NetManager.getInstance().createString(RetrofitService.class);
        RxManager.getInstance().dofunSubscribe(serviceAPI.updateFamilyUserInfo("100576", "12345678", date,"","",""
                ), new RxSubscriber<String>() {
            @Override
            protected void _onNext(String s) {
                Log.i(TAG, s);
            }
        });

    }

    public static String formatData(Date date, String format) {
        return new SimpleDateFormat(format).format(date.getTime());
    }


    public <T> void doBleList(byte[][] bytes, RxSubscriber<byte[]> subscriber) {
        Observable.concatArrayDelayError(doBle(bytes[0]), doBle(bytes[1]), doBle(bytes[2]),
                doBle(bytes[3]), doBle(bytes[3]))

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public <T> Observable<byte[]> doBle(final byte[] bytes) {
        return Observable.create(new ObservableOnSubscribe<byte[]>() {
            @Override
            public void subscribe(ObservableEmitter<byte[]> emitter) throws Exception {
                //模拟请求，返回是否成功
//                boolean writeSuccess = false;
//                if (writeSuccess)
                emitter.onNext(bytes);
//                else
//                    emitter.onError(new Throwable(""));
            }
        })
                .timeout(2, TimeUnit.SECONDS)  //两秒超时重新发送
                .retry(2); //重试两次

    }


    public void doBle(final byte[] bytes, RxSubscriber<byte[]> subscriber) {

        Observable.create(new ObservableOnSubscribe<byte[]>() {
            @Override
            public void subscribe(ObservableEmitter<byte[]> emitter) throws Exception {
                //模拟请求，返回是否成功
//                boolean writeSuccess = true;
//                if (writeSuccess)
//                    emitter.onNext(bytes);
//                else
//                    emitter.onError(new Throwable(""));
            }
        })         //两秒超时重新发送
                .timeout(2, TimeUnit.SECONDS)
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "异常" + throwable.getMessage());
                    }
                })
                .doAfterNext(new Consumer<byte[]>() {
                    @Override
                    public void accept(byte[] bytes) throws Exception {
                        Log.d(TAG, "doAfterNext" + Arrays.toString(bytes));
                    }
                })
                .throttleFirst(1, TimeUnit.SECONDS)  //两秒只发生第一数据
                .retry(2)  //重试两次
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    private void Teansform() {
        //变换操作符
        /**
         * Map,略
         *
         * FlatMap,把上游的每个时间都转换成一个Observable发送给下游，发送是无序的
         *
         * concatMap，跟flatMap基本相同，但是他是有序的
         *
         *  buffer 定期从 被观察者（Obervable）需要发送的事件中 获取一定数量的事件 & 放到缓存区中，最终发送
         *
         * */


        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                ArrayList<String> lists = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    lists.add("转换前的数字：" + integer + "加：" + i);
                }
                //可迭代的
                return Observable.fromIterable(lists);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }
        });

        /**
         * 结果：
         * 01-24 11:43:48.149 21111-21111/lab.dxythch.com.rxjavaproject D/MainActivity: accept: 转换前的数字：1加：0
         01-24 11:43:48.149 21111-21111/lab.dxythch.com.rxjavaproject D/MainActivity: accept: 转换前的数字：1加：1
         01-24 11:43:48.149 21111-21111/lab.dxythch.com.rxjavaproject D/MainActivity: accept: 转换前的数字：1加：2
         01-24 11:43:48.149 21111-21111/lab.dxythch.com.rxjavaproject D/MainActivity: accept: 转换前的数字：2加：0
         01-24 11:43:48.149 21111-21111/lab.dxythch.com.rxjavaproject D/MainActivity: accept: 转换前的数字：2加：1
         01-24 11:43:48.149 21111-21111/lab.dxythch.com.rxjavaproject D/MainActivity: accept: 转换前的数字：2加：2
         01-24 11:43:48.149 21111-21111/lab.dxythch.com.rxjavaproject D/MainActivity: accept: 转换前的数字：3加：0
         01-24 11:43:48.149 21111-21111/lab.dxythch.com.rxjavaproject D/MainActivity: accept: 转换前的数字：3加：1
         01-24 11:43:48.149 21111-21111/lab.dxythch.com.rxjavaproject D/MainActivity: accept: 转换前的数字：3加：2
         *
         * */


        Observable.just(1, 23, 4, 5, 67, 33)
                //参数一缓存区的大小，参数二，每次获取事件的个数
                .buffer(3, 1)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        Log.d(TAG, " 缓存区里的事件数量 = " + integers.size());
                        for (Integer value : integers) {
                            Log.d(TAG, " 事件 = " + value);
                        }

                    }
                });

        /**
         * 结果：
         *
         01-24 11:42:30.319 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  缓存区里的事件数量 = 3
         01-24 11:42:30.319 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  事件 = 1
         01-24 11:42:30.319 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  事件 = 23
         01-24 11:42:30.319 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  事件 = 4
         01-24 11:42:30.319 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  缓存区里的事件数量 = 3
         01-24 11:42:30.319 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  事件 = 23
         01-24 11:42:30.319 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  事件 = 4
         01-24 11:42:30.319 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  事件 = 5
         01-24 11:42:30.329 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  缓存区里的事件数量 = 3
         01-24 11:42:30.329 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  事件 = 4
         01-24 11:42:30.329 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  事件 = 5
         01-24 11:42:30.329 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  事件 = 67
         01-24 11:42:30.329 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  缓存区里的事件数量 = 3
         01-24 11:42:30.329 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  事件 = 5
         01-24 11:42:30.329 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  事件 = 67
         01-24 11:42:30.329 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  事件 = 33
         01-24 11:42:30.329 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  缓存区里的事件数量 = 2
         01-24 11:42:30.329 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  事件 = 67
         01-24 11:42:30.329 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  事件 = 33
         01-24 11:42:30.329 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  缓存区里的事件数量 = 1
         01-24 11:42:30.329 20241-20241/lab.dxythch.com.rxjavaproject D/MainActivity:  事件 = 33
         *
         * */
    }


}
