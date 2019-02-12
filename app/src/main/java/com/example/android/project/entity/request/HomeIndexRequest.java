package com.example.android.project.entity.request;

import java.io.Serializable;

/**
 * @date: 2019/2/12
 * @describe:
 */
public class HomeIndexRequest implements Serializable {
    /**
     * MSG_BODY : {}
     */

    private MSGBODYBean MSG_BODY;

    public MSGBODYBean getMSG_BODY() {
        return MSG_BODY;
    }

    public void setMSG_BODY(MSGBODYBean MSG_BODY) {
        this.MSG_BODY = MSG_BODY;
    }

    public static class MSGBODYBean implements Serializable {
    }
}
