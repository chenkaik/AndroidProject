package common.android.https;


import common.android.https.network.ApiService;
import common.android.https.network.NetWorkRequest;

/**
 * date: 2019/1/30
 * desc: 管理接口初始化
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
        NetWorkRequest.getRequestManager().init(baseURL);
    }

    public ApiService getApiService() {
        if (mApiService == null) {
            mApiService = NetWorkRequest.getRequestManager().create(ApiService.class);
        }
        return mApiService;
    }

    /**
     * 调用此方法后需重新调用init()
     */
    public void release() {
        mApiService = null;
        NetWorkRequest.getRequestManager().release();
    }

    public void cancelTagCall(String TAG) {
        NetWorkRequest.getRequestManager().cancelTagCall(TAG);
    }

}
