package commom.android.http.config;

/**
 * @date: 2019/1/30
 * @describe: 配置服务端地址
 */
public class HttpConfig {
    //    public static String BASE_URL = "http://116.196.107.174:9000/";
//    public static String BASE_URL = "http://192.168.96.117:8785/";
    // 测试环境
    public static String BASE_URL = "https://www.lixiaotech.com/server/";
    public static String INDEX_URL = "https://www.lixiaotech.com/#/mobileInterface";
    // 正式环境
//    public static String BASE_URL = "https://www.shjacf.com/server/";
//    public static String INDEX_URL = "https://www.shjacf.com/#/mobileInterface";

    public static long READ_TIMEOUT = 15L;
    public static long WRITE_TIMEOUT = 15L;
    public static long CONNECT_TIMEOUT = 15L;

    public static final String SUCCESS = "000000"; // 成功
    public static final String TOKENERROR = "100403"; // 登录失效
    public static final String FAILED = "error"; // 失败
}
