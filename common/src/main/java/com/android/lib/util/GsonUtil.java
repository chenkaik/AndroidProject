package com.android.lib.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.List;

/**
 * @date: 2019/1/30.
 * @author: Kai
 * @describe: gson封装解析json
 */
public class GsonUtil {
    private static final Gson GSON;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        GSON = gsonBuilder.create();
    }

    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

    public static <T> T fromJson(String json, TypeToken<T> token) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            return GSON.fromJson(json, token.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static <T> T fromJson(String content, Class<T> clazz) {
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        try {
            return GSON.fromJson(content, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> fromJsonArray(String content, Class<T[]> clazz) {
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        try {
            T[] arr = GSON.fromJson(content, clazz);
            return Arrays.asList(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Google Gson
     *
     * @param jsonInString
     * @return
     */
    public final static boolean isJsonValid(String jsonInString) {
        if (TextUtils.isEmpty(jsonInString)) {
            return false;
        }
        try {
            GSON.fromJson(jsonInString, Object.class);
            return true;
        } catch (JsonSyntaxException ex) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isJson(String json) {
        return isGoodJson(json);
    }

    public static boolean isGoodJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return false;
        }
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonSyntaxException e) {
            return false;
        } catch (JsonParseException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    // public static <T> List<T> fromJsonArray(String content)
    // {
    //
    // Type listType = new TypeToken<List<ShopKind>>()
    // {
    // }.getType();
    //
    // return GSON.fromJson(content, listType);

    // GsonBuilder gsonAnnotationBuilder = new GsonBuilder();
    // gsonAnnotationBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
    // gsonAnnotationBuilder.excludeFieldsWithoutExposeAnnotation();
    // return gsonAnnotationBuilder.create().fromJson(content, new
    // TypeToken<List<T>>()
    // {
    // }.getType());
    // }

    /**
     * 转成list
     *
     * @param gsonString
     * @param cls
     * @return
     */
    // public static <T> List<T> GsonToList(String gsonString, Class<T> cls)
    // {
    // List<T> list = GSON.fromJson(gsonString, new TypeToken<List<T>>() {
    // }.getType());
    // return list;
    // }

    // public static <T> List<T> stringToArray(String s, Class<T[]> clazz)
    // {
    // T[] arr = GSON.fromJson(s, clazz);
    // return Arrays.asList(arr); // or return Arrays.asList(new
    // // Gson().fromJson(s, clazz)); for a
    // // one-liner
    // }

    // public static <T> List<T> stringToArray2(String s, Class<T> clazz)
    // {
    // T[] t;
    // T[] arr =GSON.fromJson(s,t);
    // return Arrays.asList(arr); // or return Arrays.asList(new
    // // Gson().fromJson(s, clazz)); for a
    // // one-liner
    // }

}