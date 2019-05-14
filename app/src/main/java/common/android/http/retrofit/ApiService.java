package common.android.http.retrofit;

import com.example.android.project.entity.HomeIndexResponse;
import com.example.android.project.entity.LoginResponse;
import com.example.android.project.entity.request.HomeIndexRequest;
import com.example.android.project.entity.request.LoginRequest;

import common.android.http.config.URLConfig;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;

/**
 * date: 2019/1/30
 * desc: 定义接口
 */
public interface ApiService {

    /**
     * 登录
     *
     * @param loginRequest
     * @return
     */
    @POST(URLConfig.login)
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    /**
     * 获取安全生产管理指数
     *
     * @param homeIndexRequest
     * @return
     */
    @POST(URLConfig.homeIndex)
    Call<HomeIndexResponse> homeIndex(@Body HomeIndexRequest homeIndexRequest);

    @DELETE("http://123.57.75.231/teams/ffff908efa273accaf86e98545db162b")
    Call<ResponseBody> test();

}
