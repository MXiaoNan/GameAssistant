package com.mn.gameassistant.module.money.dao;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.mn.gameassistant.base.ListViewCallBack;
import com.mn.gameassistant.common.constant.Constant;
import com.mn.gameassistant.module.money.bean.TaskGameInfo;

/**
 * Created by XiaoNan on 2016/6/29.
 */
public class MoneyDao {
    public static void getMoneyDao(final ListViewCallBack listViewCallBack) {

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                Gson gson = new Gson();
                TaskGameInfo taskGameInfo = gson.fromJson(Constant.GAME_TASK_LIST_JSON, TaskGameInfo.class);
                listViewCallBack.updateListView(taskGameInfo.getInfo());
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();

    }
}
