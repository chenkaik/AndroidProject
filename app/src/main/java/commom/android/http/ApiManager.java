package commom.android.http;


import commom.android.http.retrofit.ApiService;
import commom.android.http.retrofit.NetWorkRequest;

/**
 * Created by chenKai on 2018/9/21.
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
