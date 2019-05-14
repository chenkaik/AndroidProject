package common.android.http.response;

/**
 * date: 2019/1/30
 * desc: 根据需要自由定制修改
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

//    /**
//     * 接口请求返回的提示信息
//     */
//    public String serverTip;


    /**
     * SYS_HEAD : {"RET":{"RET_CODE":"000000","RET_MSG":"登录成功!"}}
     */
    private SYSHEADBean SYS_HEAD;
    private APPHEADBean APP_HEAD;

    public SYSHEADBean getSYS_HEAD() {
        return SYS_HEAD;
    }

    public void setSYS_HEAD(SYSHEADBean SYS_HEAD) {
        this.SYS_HEAD = SYS_HEAD;
    }

    public APPHEADBean getAPP_HEAD() {
        return APP_HEAD;
    }

    public void setAPP_HEAD(APPHEADBean APP_HEAD) {
        this.APP_HEAD = APP_HEAD;
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

    public static class APPHEADBean {
        /**
         * PAGE_SIZE : 20
         * TOTAL_COUNT : 15
         * START_ROW : 1
         */

        private int PAGE_SIZE;
        private int TOTAL_COUNT;
        private int START_ROW;

        public int getPAGE_SIZE() {
            return PAGE_SIZE;
        }

        public void setPAGE_SIZE(int PAGE_SIZE) {
            this.PAGE_SIZE = PAGE_SIZE;
        }

        public int getTOTAL_COUNT() {
            return TOTAL_COUNT;
        }

        public void setTOTAL_COUNT(int TOTAL_COUNT) {
            this.TOTAL_COUNT = TOTAL_COUNT;
        }

        public int getSTART_ROW() {
            return START_ROW;
        }

        public void setSTART_ROW(int START_ROW) {
            this.START_ROW = START_ROW;
        }
    }

}
