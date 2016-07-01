package com.mn.gameassistant.module.mygames.dao;

import com.google.gson.Gson;
import com.mn.gameassistant.base.ListViewCallBack;
import com.mn.gameassistant.base.NetCallback;
import com.mn.gameassistant.common.constant.Constant;
import com.mn.gameassistant.common.net.HttpNet;
import com.mn.gameassistant.module.mygames.bean.MyGameInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XiaoNan on 2016/6/30.
 */
public class MyGameDao {
    public static void getMyGameAllInfo(int iPage, final ListViewCallBack listViewCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("platform", "2");
        params.put("page", iPage + "");

        HttpNet.doHttpRequest("POST", Constant.MONEY_URL, params, new NetCallback() {
            @Override
            public void success(String strResult) {
                Gson gson = new Gson();
                MyGameInfo myGameInfo = gson.fromJson(strResult, MyGameInfo.class);
                List<MyGameInfo.InfoBean> info = myGameInfo.getInfo();
                listViewCallBack.updateListView(info);
            }

            @Override
            public void fail(String strMsg) {

            }
        });
    }
}
