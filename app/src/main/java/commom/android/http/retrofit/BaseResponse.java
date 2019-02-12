package commom.android.http.retrofit;

/**
 * @date: 2019/1/30
 * @describe: 根据需要自由定制修改
 */
public class BaseResponse {

    /**
     * 接口请求requestCode,用于区分多个请求同时发起的情况
     */
    public int requestCode;

    /**
     * 接口请求返回的状态码
     */
    public int responseCode;

    /**
     * 接口请求返回的提示信息
     */
    public String serverTip;


    /**
     * SYS_HEAD : {"RET":{"RET_CODE":"000000","RET_MSG":"登录成功!"}}
     */

    private SYSHEADBean SYS_HEAD;

    public SYSHEADBean getSYS_HEAD() {
        return SYS_HEAD;
    }

    public void setSYS_HEAD(SYSHEADBean SYS_HEAD) {
        this.SYS_HEAD = SYS_HEAD;
    }

    public static class SYSHEADBean {
        /**
         * RET : {"RET_CODE":"000000","RET_MSG":"登录成功!"}
         */

        private RETBean RET;

        public RETBean getRET() {
            return RET;
        }

        public void setRET(RETBean RET) {
            this.RET = RET;
        }

        public static class RETBean {
            /**
             * RET_CODE : 000000
             * RET_MSG : 登录成功!
             */

            private String RET_CODE;
            private String RET_MSG;

            public String getRET_CODE() {
                return RET_CODE;
            }

            public void setRET_CODE(String RET_CODE) {
                this.RET_CODE = RET_CODE;
            }

            public String getRET_MSG() {
                return RET_MSG;
            }

            public void setRET_MSG(String RET_MSG) {
                this.RET_MSG = RET_MSG;
            }
        }
    }

}
