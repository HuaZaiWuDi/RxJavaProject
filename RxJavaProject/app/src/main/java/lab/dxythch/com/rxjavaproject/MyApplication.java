package lab.dxythch.com.rxjavaproject;

import android.app.Application;

import com.tencent.tauth.Tencent;

/**
 * 项目名称：RxJavaProject
 * 类描述：
 * 创建人：oden
 * 创建时间：2018/1/25
 */
public class MyApplication extends Application {

    private static String QQ_Login_ID = "1106565969";
    public static String WEIBO_LOGIN_KEY = "2436112383";
    public static String WEIBO_LOGIN_SECRET = "c4a9e96ad23df29702e70c1de203824b";
    public static String WEIBO_LOGIN_URL = "http://app-manager.dxycloud.com/admin/productmanage";

    private String weiboRedirectUrl = "https://api.weibo.com/oauth2/default.html";

    private String weiboScope = "email";


    private static Application application;
    public static String BMOB_APPID = "4d0b207b9731b474694cbfdc2bf5fbd4";

    public static Tencent mTencent;




    @Override
    public void onCreate() {
        super.onCreate();
//        Bmob.initialize(this, "4d0b207b9731b474694cbfdc2bf5fbd4");


//        mTencent = Tencent.createInstance(QQ_Login_ID, this);



    }
}
