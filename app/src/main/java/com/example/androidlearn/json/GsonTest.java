package com.example.androidlearn.json;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: luweiming
 * @Description: TODO
 * @Date: Created in 15:45 2022/3/8
 */
public class GsonTest {
    public static String generate() {
        BaseResponse<String> response = new BaseResponse<>();
        response.setCode(1);
        response.setMsg("2222");
        response.setData("gogo");
        return new Gson().toJson(response);
    }

    public static String generate1() {
        BaseResponse<List<String>> response = new BaseResponse<>();
        response.setMsg("fff");
        response.setCode(0);
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        response.setData(list);
        return new Gson().toJson(response);
    }

    public static void main(String[] args) {
        String json = generate();
        String json1 = generate1();
        String clz = new Gson().fromJson(json, BaseResponse.class).getClass().getCanonicalName();
        String clz1 = new Gson().fromJson(json, new TypeToken<BaseResponse<String>>() {
        }.getType()).getClass().getCanonicalName();
        String clz2 = new Gson().fromJson(json1, BaseResponse.class).getClass().getCanonicalName();
        String clz3 = new Gson().fromJson(json1, new TypeToken<BaseResponse<List<String>>>() {
        }.getType()).getClass().getCanonicalName();
        Log.d("fffffff", clz);
        Log.d("fffffff", clz1);
        Log.d("fffffff", clz2);
        Log.d("fffffff", clz3);

    }
}
