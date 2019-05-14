package com.example.android.project.entity;

import common.android.http.response.BaseResponse;

/**
 * date: 2019/2/12
 * desc: 登录
 */
public class LoginResponse extends BaseResponse {

    /**
     * MSG_BODY : {"acctNo":"aries","userType":"Admin","userName":"超级管理员","orgId":"SYMBOL","token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcmllcyNTWU1CT0wjQWRtaW4iLCJleHAiOjE1Mzc5NTA1MDV9.OV93SzwXXSacU1fjA-UkfiZVvd5zNRM_gU5U-vWsxxj83ROsL68S8yIkpVOXIacnvQC_A9Qx6WcQmKltZNoD-A"}
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
         * acctNo : aries
         * userType : Admin
         * userName : 超级管理员
         * orgId : SYMBOL
         * token : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcmllcyNTWU1CT0wjQWRtaW4iLCJleHAiOjE1Mzc5NTA1MDV9.OV93SzwXXSacU1fjA-UkfiZVvd5zNRM_gU5U-vWsxxj83ROsL68S8yIkpVOXIacnvQC_A9Qx6WcQmKltZNoD-A
         */

        private String acctNo;
        private String userType;
        private String userName;
        private String orgId;
        private String token;

        public String getAcctNo() {
            return acctNo;
        }

        public void setAcctNo(String acctNo) {
            this.acctNo = acctNo;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

}
