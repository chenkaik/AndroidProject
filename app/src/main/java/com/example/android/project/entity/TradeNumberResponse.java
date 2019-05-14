package com.example.android.project.entity;

import common.android.http.response.BaseResponse;

/**
 * date: 2019/2/13
 * desc:
 */
public class TradeNumberResponse extends BaseResponse {

    /**
     * SYS_HEAD : {"RET":{"RET_CODE":"000000","RET_MSG":"Successful"}}
     * MSG_BODY : {"Result":"PW201805171526523388645000006"}
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
         * Result : PW201805171526523388645000006
         */

        private String Result;

        public String getResult() {
            return Result;
        }

        public void setResult(String Result) {
            this.Result = Result;
        }
    }


}
