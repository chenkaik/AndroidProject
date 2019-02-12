package commom.android.http;


import commom.android.http.retrofit.ApiService;
import commom.android.http.retrofit.NetWorkRequest;

/**
 * @date: 2019/1/30
 * @describe: 管理接口初始化
 */
public class ApiManager {

    private ApiService mApiService;

    private ApiManager() {
    }

    public static ApiManager getInstance() {
        return ApiManagerHolder.sInstance;
    }

    private static class ApiManagerHolder {
        private static final ApiManager sInstance = new ApiManager();
    }

    public void init(String baseURL) {
        NetWorkRequest.getInstance().init(baseURL);
    }

    public ApiService getApiService() {
        if (mApiService == null) {
            mApiService = NetWorkRequest.getInstance().create(ApiService.class);
        }
        return mApiService;
    }

    /**
     * 调用此方法后需重新调用init()
     */
    public void release() {
        mApiService = null;
        NetWorkRequest.getInstance().release();
    }

    public void cancelTagCall(String TAG) {
        NetWorkRequest.getInstance().cancelTagCall(TAG);
    }

}
