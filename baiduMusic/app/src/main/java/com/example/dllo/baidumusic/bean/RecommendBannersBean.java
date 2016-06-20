package com.example.dllo.baidumusic.bean;

import java.util.List;

/**
 * Created by dllo on 16/5/26.
 */
public class RecommendBannersBean {

    /**
     * pic : [{"randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_14641660928eff4f15a2f7c6c09e1acc5c210dae93.jpg","randpic_ios5":"","randpic_desc":"We Will Rule 背水一战","randpic_ipad":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1464166099aade5ff5370f7dfd1d8d420c96c05c2d.jpg","randpic_qq":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_14641661050f76c88cbf822290455f88a6f2de5bcb.jpg","randpic_2":"bos_client_1464166111e3cb2e9af9b15a42759de21632e98c6a","randpic_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1464166111e3cb2e9af9b15a42759de21632e98c6a.jpg","special_type":0,"ipad_desc":"We Will Rule 背水一战","is_publish":"111100","mo_type":"2","type":2,"code":"265829677"},{"randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_146416613315827fcbac307c60563780eac0202248.jpg","randpic_ios5":"","randpic_desc":"一周音乐热","randpic_ipad":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1464166141fd13bc125571334d90cb02f981bce440.jpg","randpic_qq":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_14641661511df2b68f0d7a49786bd515378a4a2553.jpg","randpic_2":"bos_client_14641661586479a2b208fdfe4403c50c3f560955f3","randpic_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_14641661586479a2b208fdfe4403c50c3f560955f3.jpg","special_type":0,"ipad_desc":"一周音乐热","is_publish":"111100","mo_type":"4","type":6,"code":"http://music.baidu.com/cms/webview/mobile-temp-special/v35/index.html"},{"randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1464172168277e6dbf6e5fa2bb194927207a78f072.jpg","randpic_ios5":"","randpic_desc":"大牌来啦","randpic_ipad":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1464172175b729b7673ea6b1656918aa75e28eabf9.jpg","randpic_qq":"","randpic_2":"bos_client_1464172184fb823d3f5c233287293eaac18d01ba9a","randpic_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1464172184fb823d3f5c233287293eaac18d01ba9a.jpg","special_type":0,"ipad_desc":"大牌来啦","is_publish":"111100","mo_type":"4","type":6,"code":"http://music.baidu.com/cms/webview/bigwig/20160520/index.html"},{"randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_146417229909bfac33ca5d48d56ba964f004c0e062.jpg","randpic_ios5":"","randpic_desc":"被风吹散的人们","randpic_ipad":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1464172303aef69f2f642b4b86f50ddca7b6c3bc73.jpg","randpic_qq":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1464172309e559002f8da350458e5b07db588cb61a.jpg","randpic_2":"bos_client_1464172315a0626c1bcf1f72f4d942db47dc614933","randpic_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1464172315a0626c1bcf1f72f4d942db47dc614933.jpg","special_type":0,"ipad_desc":"被风吹散的人们","is_publish":"111101","mo_type":"2","type":2,"code":"265794735"},{"randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_14641724181029c2cc9f26a7b1e7f968284bfeb997.jpg","randpic_ios5":"","randpic_desc":"好妹妹乐队","randpic_ipad":"","randpic_qq":"","randpic_2":"bos_client_1464172425560ca22d64b988a54aceec7373c3aa00","randpic_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1464172425560ca22d64b988a54aceec7373c3aa00.jpg","special_type":0,"ipad_desc":"好妹妹乐队","is_publish":"110000","mo_type":"4","type":6,"code":"http://music.baidu.com/cms/webview/song_buy/index/index.html?type=2&type_id=265673608"}]
     * error_code : 22000
     */

    private int error_code;
    /**
     * randpic : http://business.cdn.qianqian.com/qianqian/pic/bos_client_14641660928eff4f15a2f7c6c09e1acc5c210dae93.jpg
     * randpic_ios5 :
     * randpic_desc : We Will Rule 背水一战
     * randpic_ipad : http://business.cdn.qianqian.com/qianqian/pic/bos_client_1464166099aade5ff5370f7dfd1d8d420c96c05c2d.jpg
     * randpic_qq : http://business.cdn.qianqian.com/qianqian/pic/bos_client_14641661050f76c88cbf822290455f88a6f2de5bcb.jpg
     * randpic_2 : bos_client_1464166111e3cb2e9af9b15a42759de21632e98c6a
     * randpic_iphone6 : http://business.cdn.qianqian.com/qianqian/pic/bos_client_1464166111e3cb2e9af9b15a42759de21632e98c6a.jpg
     * special_type : 0
     * ipad_desc : We Will Rule 背水一战
     * is_publish : 111100
     * mo_type : 2
     * type : 2
     * code : 265829677
     */

    private List<PicBean> pic;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<PicBean> getPic() {
        return pic;
    }

    public void setPic(List<PicBean> pic) {
        this.pic = pic;
    }

    public static class PicBean {
        private String randpic;
        private String randpic_ios5;
        private String randpic_desc;
        private String randpic_ipad;
        private String randpic_qq;
        private String randpic_2;
        private String randpic_iphone6;
        private int special_type;
        private String ipad_desc;
        private String is_publish;
        private String mo_type;
        private int type;
        private String code;

        public String getRandpic() {
            return randpic;
        }

        public void setRandpic(String randpic) {
            this.randpic = randpic;
        }

        public String getRandpic_ios5() {
            return randpic_ios5;
        }

        public void setRandpic_ios5(String randpic_ios5) {
            this.randpic_ios5 = randpic_ios5;
        }

        public String getRandpic_desc() {
            return randpic_desc;
        }

        public void setRandpic_desc(String randpic_desc) {
            this.randpic_desc = randpic_desc;
        }

        public String getRandpic_ipad() {
            return randpic_ipad;
        }

        public void setRandpic_ipad(String randpic_ipad) {
            this.randpic_ipad = randpic_ipad;
        }

        public String getRandpic_qq() {
            return randpic_qq;
        }

        public void setRandpic_qq(String randpic_qq) {
            this.randpic_qq = randpic_qq;
        }

        public String getRandpic_2() {
            return randpic_2;
        }

        public void setRandpic_2(String randpic_2) {
            this.randpic_2 = randpic_2;
        }

        public String getRandpic_iphone6() {
            return randpic_iphone6;
        }

        public void setRandpic_iphone6(String randpic_iphone6) {
            this.randpic_iphone6 = randpic_iphone6;
        }

        public int getSpecial_type() {
            return special_type;
        }

        public void setSpecial_type(int special_type) {
            this.special_type = special_type;
        }

        public String getIpad_desc() {
            return ipad_desc;
        }

        public void setIpad_desc(String ipad_desc) {
            this.ipad_desc = ipad_desc;
        }

        public String getIs_publish() {
            return is_publish;
        }

        public void setIs_publish(String is_publish) {
            this.is_publish = is_publish;
        }

        public String getMo_type() {
            return mo_type;
        }

        public void setMo_type(String mo_type) {
            this.mo_type = mo_type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
