package com.example.android.project.entity;

import common.android.http.response.BaseResponse;

/**
 * date: 2019/2/12
 * desc:
 */
public class HomeIndexResponse extends BaseResponse {
    /**
     * SYS_HEAD : {"ORG_ID":"SYMBOL","RET":{"RET_CODE":"000000","RET_MSG":"Successful"}}
     * MSG_BODY : {"Result":{"finialResult":"35.00"}}
     */

    private MSGBODYBean MSG_BODY;

    public MSGBODYBean getMSG_BODY() {
        return MSG_BODY;
    }

    public void setMSG_BODY(MSGBODYBean MSG_BODY) {
        this.MSG_BODY = MSG_BODY;
    }

    public static class MSGBODYBean {
        /**
         * Result : {"finialResult":"35.00"}
         */

        private ResultBean Result;

        public ResultBean getResult() {
            return Result;
        }

        public void setResult(ResultBean Result) {
            this.Result = Result;
        }

        public static class ResultBean {
            /**
             * finialResult : 35.00
             */

            private String finialResult;

            public String getFinialResult() {
                return finialResult;
            }

            public void setFinialResult(String finialResult) {
                this.finialResult = finialResult;
            }
        }
    }
}
