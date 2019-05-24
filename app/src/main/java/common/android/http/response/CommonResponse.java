package common.android.http.response;

/**
 * date: 2019/1/30
 * desc: 请求数据(返回解析好的数据直接使用,适用于retrofit请求)的回调接口
 */
public interface CommonResponse<T extends BaseResponse> {
    /**
     * 服务器返回成功回调
     *
     * @param response 网络请求返信息
     */
    void onDataReady(T response);

    /**
     * 失败回调
     *
     * @param requestCode  区分请求的code
     * @param responseCode 服务器响应的code
     * @param message      响应的信息
     * @param isOverdue    当前登录是否失效
     */
    void onDataError(int requestCode, int responseCode, String message, boolean isOverdue);

    /**
     * 显示dialog
     *
     * @param msg tips
     */
    void showLoading(String msg);

    /**
     * 取消dialog
     */
    void dismissLoading();
}
