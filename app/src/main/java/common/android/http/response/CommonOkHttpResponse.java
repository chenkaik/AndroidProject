package common.android.http.response;

/**
 * date: 2019/1/30
 * desc: 请求数据的回调接口(适用于okHttp请求)
 */
public interface CommonOkHttpResponse {

//    /**
//     * 接口响应
//     *
//     * @param requestCode 区分请求的code
//     * @param response    响应
//     */
//    void onSuccess(int requestCode, Response response);

    /**
     * 服务器返回成功的数据(回调时的值object与json二选一处理)
     *
     * @param requestCode 区分请求的code
     * @param object      网络请求返回的数据(直接使用)
     * @param json        网络请求返回的json数据
     */
    void onDataSuccess(int requestCode, Object object, String json);

    /**
     * 失败回调
     *
     * @param requestCode  区分请求的code
     * @param responseCode 服务器响应的code
     * @param message      响应的信息
     * @param isOverdue    当前登录是否失效
     */
    void onDataFailure(int requestCode, int responseCode, String message, boolean isOverdue);

//    /**
//     * 显示dialog
//     *
//     * @param msg
//     */
//    void showLoading(String msg);
//
//    /**
//     * 取消dialog
//     */
//    void dismissLoading();
}
