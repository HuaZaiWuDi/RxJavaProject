package lab.dxythch.com.rxjavaproject;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：RxJavaProject
 * 类描述：
 * 创建人：oden
 * 创建时间：2018/4/12
 */
public class myBean implements Serializable{

    String name;
    int age;
    List list;
    Map map;

    public void setName(String name) {
        this.name = name;
    }
}
