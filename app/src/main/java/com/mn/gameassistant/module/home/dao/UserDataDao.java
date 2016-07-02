package com.mn.gameassistant.module.home.dao;

import com.google.gson.Gson;
import com.mn.gameassistant.base.ListViewCallBack;
import com.mn.gameassistant.base.NetCallback;
import com.mn.gameassistant.common.constant.Constant;
import com.mn.gameassistant.common.net.HttpNet;
import com.mn.gameassistant.module.home.bean.UserDataInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by XiaoNan on 2016/7/2.
 */
public class UserDataDao {

    public static void getMyGameAllInfo(final ListViewCallBack listViewCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("uid", "318529448");
        params.put("token", "f9872wYFjZ/oq7Guywo7ZfvDfT2BYTVMdcEymW/fTfigdTzqhqhVtSZmd1Z8+1auE8MQdQ5jew");

        HttpNet.doHttpRequest("POST", Constant.USER_URL_JSON, params, new NetCallback() {

            @Override
            public void success(String strResult) {
                Gson gson = new Gson();
                UserDataInfo userDataInfo = gson.fromJson(strResult, UserDataInfo.class);
                UserDataInfo.InfoBean info = userDataInfo.getInfo();
                listViewCallBack.updateListView(info);
            }

            @Override
            public void fail(String strMsg) {

            }
        });
    }
}
