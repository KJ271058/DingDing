package com.example.administrator.testclient.util;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.administrator.testclient.MyApplication;
import com.example.administrator.testclient.bean.Student;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import static android.content.Context.MODE_PRIVATE;

public class HttpUtils {
    static private final String HTTP = "http://192.168.42.83:8080/TestServer_war/api/";
    private static final String TAG = "HttpUtil";

    public static void post(String url, JSONObject jsonObject, MyResponseHandler handler) {
        RequestParams params = new RequestParams(getUrl(url));
        params.setAsJsonContent(true);
        params.setBodyContent(jsonObject.toString());
        Log.d(TAG, "post: "+jsonObject.toString());
        x.http().post(params, handler);
    }

    public abstract static class MyResponseHandler implements Callback.CommonCallback<String> {

        @Override
        public void onSuccess(String s) {
            mySuccess(s);
        }

        protected abstract void mySuccess(String s);

        @Override
        public void onError(Throwable throwable, boolean b) {
            myError(throwable,b);
        }

        protected abstract void myError(Throwable throwable, boolean b);

        @Override
        public void onCancelled(CancelledException e) {
            myCancelled(e);
        }

        protected abstract void myCancelled(CancelledException e);

        @Override
        public void onFinished() {
            myFinished();
        }

        protected abstract void myFinished();
    }

    private static String getUrl(String api) {
        String url = HTTP + api;
        Log.d(TAG, "getUrl: " + url);
        return url;
    }

    public static void saveUser(Student stu) {
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("account",stu.getStuid());
        editor.putString("password",stu.getStupassword());
        editor.commit();
    }

    public static Student getUser() {
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences("user", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "user1");
        Student student =new Student();
        student.setStuid(sharedPreferences.getString("account", null));
        student.setStupassword(sharedPreferences.getString("password", null));
        return student;
    }


    private static Gson gson = new Gson();

    public static <T> T toObject(String s, Class<T> tClass) {
        return gson.fromJson(s, tClass);
    }
}
