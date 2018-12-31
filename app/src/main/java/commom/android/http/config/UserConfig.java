package commom.android.http.config;


import com.android.lib.util.SharedPreferencesUtil;
import com.example.android.project.CommonApplication;


/**
 * @date: 2018/4/17.
 * @author: CHEN
 * @describe: 该SP是和用户信息相关的
 */

public class UserConfig extends SharedPreferencesUtil {

    private static final String FILE_NAME = "user_config";

    private static final String TOKEN = "token";
    private static final String ACCTNO = "acctNo";
    private static final String USERTYPE = "userType";
    private static final String USERNAME = "userName";
    private static final String ORGID = "orgId";

    private static final String USER = "user";
    private static final String PASS = "pass";
    private static final String SAVE = "save";
    private static final String TOHOME = "toHome";


    private UserConfig() {
        super(CommonApplication.getContext(), FILE_NAME);
    }

    public static UserConfig getInstance() {
        return UserConfigHolder.INSTANCE;
    }

    private static class UserConfigHolder {
        private static final UserConfig INSTANCE = new UserConfig();
    }

    public void clearToken() {
        put(TOKEN, "");
    }

    public void putToken(String token) {
        put(TOKEN, token);
    }

    public String getToken() {
        return getString(TOKEN, "");
    }

}
