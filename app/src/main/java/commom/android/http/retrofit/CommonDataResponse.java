package commom.android.http.retrofit;

/**
 * Created by chenKai on 2018/9/25.
 */
public interface CommonDataResponse {

    /**
     * 服务器返回成功的数据(回调时的值object与json二选一处理)
     *
     * @param requestCode 区分请求的code
     * @param object      网络请求返回的数据(直接使用)
     * @param json        网络请求返回的json数据
     */
    void onDataReady(int requestCode, Object object, String json);

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
