package com.qianfeng.day1_retrofit_gson.bean;

import java.util.List;

/**
 * Created by xray on 16/9/26.
 */

public class GiftListBean {

    /**
     * ad : [{"id":249,"title":"绝世武神独家礼包（安卓）","flag":1,"iconurl":"/allimgs/img_iad/_1474623667919.jpg","addtime":"2016-09-17","giftid":"1474618510","appName":"绝世武神","appLogo":"/allimgs/img_iapp/201607/_1469611677571.jpg","appId":1444892111},{"id":246,"title":"天天炫舞中秋独家礼包","flag":1,"iconurl":"/allimgs/img_iad/_1473751476804.jpg","addtime":"2016-09-15","giftid":"1473749901","appName":"天天炫舞","appLogo":"/allimgs/img_iapp/201609/_1474340811708.png","appId":2496048},{"id":243,"title":"航海王强者之路中秋媒体节日礼包","flag":1,"iconurl":"/allimgs/img_iad/_1473316667867.jpg","addtime":"2016-09-14","giftid":"1473316129","appName":"航海王强者之路","appLogo":"/allimgs/img_iapp/201511/_1448446144213.png","appId":1448446177},{"id":245,"title":"大秦帝国OL中秋独家礼包 ","flag":1,"iconurl":"/allimgs/img_iad/_1473749952262.jpg","addtime":"2016-09-14","giftid":"1473742131","appName":"大秦帝国OL","appLogo":"/allimgs/img_iapp/201607/_1469687930154.jpg","appId":1467784200},{"id":247,"title":"风暴部落中秋礼包","flag":1,"iconurl":"/allimgs/img_iad/_1473831738979.jpg","addtime":"2016-09-13","giftid":"1473826961","appName":"风暴部落","appLogo":"/allimgs/img_iapp/201609/_1473238539795.png","appId":1451287404}]
     * pageno : 178
     * list : [{"id":"1474863557","iconurl":"/allimgs/img_iapp/201609/_1474363818871.png","giftname":"新手礼包","number":11,"exchanges":1,"type":1,"gname":"不朽王座","integral":0,"isintegral":0,"addtime":"2016-09-26","ptype":"1,","operators":"","flag":1},{"id":"1474863753","iconurl":"/allimgs/img_iapp/201609/_1474363818871.png","giftname":"高阶礼包","number":19,"exchanges":1,"type":1,"gname":"不朽王座","integral":0,"isintegral":0,"addtime":"2016-09-26","ptype":"1,","operators":"","flag":1},{"id":"1474863928","iconurl":"/allimgs/img_iapp/201609/_1474363818871.png","giftname":"高价值礼包","number":9,"exchanges":1,"type":1,"gname":"不朽王座","integral":0,"isintegral":0,"addtime":"2016-09-26","ptype":"1,","operators":"","flag":1},{"id":"1474868950","iconurl":"/allimgs/img_iapp/201609/_1474868120047.png","giftname":"专属特权礼包","number":30,"exchanges":0,"type":1,"gname":"封神策","integral":0,"isintegral":0,"addtime":"2016-09-26","ptype":"1,","operators":"","flag":1},{"id":"1474871280","iconurl":"/allimgs/img_iapp/201608/_1471412942903.jpg","giftname":"媒体礼包","number":20,"exchanges":0,"type":1,"gname":"熊出没之冲上云霄","integral":0,"isintegral":0,"addtime":"2016-09-26","ptype":"1,","operators":"","flag":1},{"id":"1474871973","iconurl":"/allimgs/img_iapp/201609/_1473672736512.jpg","giftname":"国庆礼包","number":9,"exchanges":1,"type":1,"gname":"啪啪巫妖王","integral":0,"isintegral":0,"addtime":"2016-09-26","ptype":"1,2,","operators":"","flag":1},{"id":"1474873754","iconurl":"/allimgs/img_iapp/201609/_1473056797758.png","giftname":"国庆礼包","number":30,"exchanges":0,"type":1,"gname":"剑与江湖","integral":0,"isintegral":0,"addtime":"2016-09-26","ptype":"1,2,","operators":"","flag":1},{"id":"1474874426","iconurl":"/allimgs/img_iapp/201608/_1471339331321.png","giftname":"首测礼包","number":20,"exchanges":0,"type":1,"gname":"花语学园","integral":0,"isintegral":0,"addtime":"2016-09-26","ptype":"2,","operators":"","flag":1},{"id":"1474874869","iconurl":"/allimgs/img_iapp/201608/_1470650646182.png","giftname":"国庆狂欢礼包","number":30,"exchanges":0,"type":1,"gname":"口袋妖怪重制","integral":0,"isintegral":0,"addtime":"2016-09-26","ptype":"1,2,","operators":"","flag":1},{"id":"1473843532","iconurl":"/allimgs/img_iapp/201609/_1473841701115.png","giftname":"封测补给礼包","number":0,"exchanges":30,"type":1,"gname":"星际争霸-人族崛起","integral":0,"isintegral":0,"addtime":"2016-09-23","ptype":"1,","operators":"","flag":1}]
     */

    private int pageno;
    /**
     * id : 249
     * title : 绝世武神独家礼包（安卓）
     * flag : 1
     * iconurl : /allimgs/img_iad/_1474623667919.jpg
     * addtime : 2016-09-17
     * giftid : 1474618510
     * appName : 绝世武神
     * appLogo : /allimgs/img_iapp/201607/_1469611677571.jpg
     * appId : 1444892111
     */

    private List<AdBean> ad;
    /**
     * id : 1474863557
     * iconurl : /allimgs/img_iapp/201609/_1474363818871.png
     * giftname : 新手礼包
     * number : 11
     * exchanges : 1
     * type : 1
     * gname : 不朽王座
     * integral : 0
     * isintegral : 0
     * addtime : 2016-09-26
     * ptype : 1,
     * operators :
     * flag : 1
     */

    private List<ListBean> list;

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }

    public List<AdBean> getAd() {
        return ad;
    }

    public void setAd(List<AdBean> ad) {
        this.ad = ad;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class AdBean {
        private int id;
        private String title;
        private int flag;
        private String iconurl;
        private String addtime;
        private String giftid;
        private String appName;
        private String appLogo;
        private int appId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getGiftid() {
            return giftid;
        }

        public void setGiftid(String giftid) {
            this.giftid = giftid;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getAppLogo() {
            return appLogo;
        }

        public void setAppLogo(String appLogo) {
            this.appLogo = appLogo;
        }

        public int getAppId() {
            return appId;
        }

        public void setAppId(int appId) {
            this.appId = appId;
        }
    }

    public static class ListBean {
        private String id;
        private String iconurl;
        private String giftname;
        private int number;
        private int exchanges;
        private int type;
        private String gname;
        private int integral;
        private int isintegral;
        private String addtime;
        private String ptype;
        private String operators;
        private int flag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public String getGiftname() {
            return giftname;
        }

        public void setGiftname(String giftname) {
            this.giftname = giftname;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getExchanges() {
            return exchanges;
        }

        public void setExchanges(int exchanges) {
            this.exchanges = exchanges;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public int getIsintegral() {
            return isintegral;
        }

        public void setIsintegral(int isintegral) {
            this.isintegral = isintegral;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getPtype() {
            return ptype;
        }

        public void setPtype(String ptype) {
            this.ptype = ptype;
        }

        public String getOperators() {
            return operators;
        }

        public void setOperators(String operators) {
            this.operators = operators;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }
    }
}
