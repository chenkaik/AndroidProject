package commom.android.http.retrofit;

/**
 * Created by chenKai on 2018/9/25.
 */
public interface CommonResponse<T extends BaseResponseEntity> {
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
     * @param msg
     */
    void showLoading(String msg);

    /**
     * 取消dialog
     */
    void dismissLoading();
}
