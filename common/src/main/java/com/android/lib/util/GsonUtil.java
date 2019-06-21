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
 * date: 2019/1/30.
 * desc: Gson封装解析Json
 */
public final class GsonUtil {

    private static final Gson GSON;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        GSON = gsonBuilder.create();
    }

    /**
     * 对象转换为json字符串
     *
     * @param object 转换的对象
     * @return 转换后的字符串
     */
    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

    /**
     * 解析json数据(list)
     *
     * @param json  待解析的数据
     * @param token 解析的类型
     * @param <T>   泛型
     * @return 解析后的对象
     */
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

    /**
     * 解析json数据
     *
     * @param json  待解析的数据
     * @param clazz 解析的类型
     * @param <T>   泛型
     * @return 解析后的对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            return GSON.fromJson(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析List json数据
     *
     * @param json  待解析的数据
     * @param clazz 解析的类型
     * @param <T>   泛型
     * @return 返回list
     */
    public static <T> List<T> fromJsonArray(String json, Class<T[]> clazz) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            T[] arr = GSON.fromJson(json, clazz);
            return Arrays.asList(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断json数据是否可用
     *
     * @param jsonInString 数据
     * @return 结果true/false
     */
    public static boolean isJsonValid(String jsonInString) {
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

    /**
     * 判断是否是json数据
     *
     * @param json 数据
     * @return 结果true/false
     */
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

//    /**
//     * 转成list
//     *
//     * @param gsonString
//     * @param cls
//     * @return
//     */
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