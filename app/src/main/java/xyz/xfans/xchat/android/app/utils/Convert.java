package xyz.xfans.xchat.android.app.utils;

import com.google.gson.Gson;

/**
 * Created by xfans on 2015/7/18.
 */
public class Convert {
    public static String ObjectToJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static <T> T JsonToObject(String str,Class<T> classOfT){
        Gson gson = new Gson();
        return gson.fromJson(str,classOfT);
    }
}
