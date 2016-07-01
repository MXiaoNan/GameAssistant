package com.mn.gameassistant.module.mygames.bean;

import java.util.List;

/**
 * Created by XiaoNan on 2016/6/30.
 */
public class OldGameInfo {

    /**
     * total : 1
     * pagesize : 10
     * page : 1
     * page_total : 1
     */

    private PageBean page;
    /**
     * info : [{"id":"812","name":"罗马时代:帝国OL","score":"7.5","icon":"http://i3.72g.com/upload/201606/201606071620204837.png","size":"22.58M","dl_back_jifen":"100","limit_number":"0","dl_num":"814","android_dl":"http://market-download.kkk5.com/lmsd/wzsj_0621_7316.apk","ios_dl":"","count_dl":"1014","all_prize":1500,"game_statue":0}]
     * page : {"total":1,"pagesize":10,"page":1,"page_total":1}
     * state : success
     */

    private String state;
    /**
     * id : 812
     * name : 罗马时代:帝国OL
     * score : 7.5
     * icon : http://i3.72g.com/upload/201606/201606071620204837.png
     * size : 22.58M
     * dl_back_jifen : 100
     * limit_number : 0
     * dl_num : 814
     * android_dl : http://market-download.kkk5.com/lmsd/wzsj_0621_7316.apk
     * ios_dl :
     * count_dl : 1014
     * all_prize : 1500
     * game_statue : 0
     */

    private List<InfoBean> info;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class PageBean {
        private int total;
        private int pagesize;
        private int page;
        private int page_total;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPage_total() {
            return page_total;
        }

        public void setPage_total(int page_total) {
            this.page_total = page_total;
        }
    }

    public static class InfoBean {
        private String id;
        private String name;
        private String score;
        private String icon;
        private String size;
        private String dl_back_jifen;
        private String limit_number;
        private String dl_num;
        private String android_dl;
        private String ios_dl;
        private String count_dl;
        private int all_prize;
        private int game_statue;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getDl_back_jifen() {
            return dl_back_jifen;
        }

        public void setDl_back_jifen(String dl_back_jifen) {
            this.dl_back_jifen = dl_back_jifen;
        }

        public String getLimit_number() {
            return limit_number;
        }

        public void setLimit_number(String limit_number) {
            this.limit_number = limit_number;
        }

        public String getDl_num() {
            return dl_num;
        }

        public void setDl_num(String dl_num) {
            this.dl_num = dl_num;
        }

        public String getAndroid_dl() {
            return android_dl;
        }

        public void setAndroid_dl(String android_dl) {
            this.android_dl = android_dl;
        }

        public String getIos_dl() {
            return ios_dl;
        }

        public void setIos_dl(String ios_dl) {
            this.ios_dl = ios_dl;
        }

        public String getCount_dl() {
            return count_dl;
        }

        public void setCount_dl(String count_dl) {
            this.count_dl = count_dl;
        }

        public int getAll_prize() {
            return all_prize;
        }

        public void setAll_prize(int all_prize) {
            this.all_prize = all_prize;
        }

        public int getGame_statue() {
            return game_statue;
        }

        public void setGame_statue(int game_statue) {
            this.game_statue = game_statue;
        }
    }
}
