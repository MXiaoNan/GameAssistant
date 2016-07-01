package com.mn.gameassistant.module.mygames.dao;

import com.google.gson.Gson;
import com.mn.gameassistant.base.ListViewCallBack;
import com.mn.gameassistant.base.NetCallback;
import com.mn.gameassistant.common.constant.Constant;
import com.mn.gameassistant.common.net.HttpNet;
import com.mn.gameassistant.module.mygames.bean.OldGameInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XiaoNan on 2016/6/30.
 */
public class OldGameDao {
    public static void getOldGameInfo(int iPage, final ListViewCallBack listViewCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("platform", "2");
//        params.put("state", "1");
        // params.put("token", "8527xlJxdTv/FeeHMPeELY1bdMwA4QXBqCG5c+6l15cWrL8Vo7jtcz17J2jiVlGKn6GMFczKKg");


        HttpNet.doHttpRequest("POST", Constant.MONEY_URL, params, new NetCallback() {
            @Override
            public void success(String strResult) {
                Gson gson = new Gson();
                OldGameInfo oldGameInfo = gson.fromJson(strResult, OldGameInfo.class);
                List<OldGameInfo.InfoBean> info = oldGameInfo.getInfo();
                listViewCallBack.updateListView(info);
            }

            @Override
            public void fail(String strMsg) {

            }
        });
    }
}
