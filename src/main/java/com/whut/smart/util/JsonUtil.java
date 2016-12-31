package com.whut.smart.util;

import com.google.gson.Gson;

/**
 * json 工具类
 *
 * Created by null on 2016/12/31.
 */
public class JsonUtil {

    private static final Gson gson = new Gson();

    public static <T> String toJson(T t) {
        return gson.toJson(t);
    }
}
