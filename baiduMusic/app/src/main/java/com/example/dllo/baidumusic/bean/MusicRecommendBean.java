package com.example.dllo.baidumusic.bean;

import java.util.List;

/**
 * Created by dllo on 16/5/28.
 */
public class MusicRecommendBean {

    /**
     * error_code : 22000
     * content : {"title":"热门歌单","list":[{"listid":"6634","pic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_affd48cf32d09efe2046f67d1f46faad.jpg","listenum":"7900","collectnum":"348","title":"总有一首歌让你沉醉","tag":"好听,沉醉,舒服,经典","type":"gedan"},{"listid":"6633","pic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_5980ff0314f8abf99179691a0f2a0492.jpg","listenum":"16440","collectnum":"351","title":"用音乐唱出暗恋的小心思","tag":"深情,好听,浪漫","type":"gedan"},{"listid":"6631","pic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_affd48cf32d09efe2046f67d1f46faad.jpg","listenum":"14273","collectnum":"371","title":"唱进你心的温柔声音","tag":"舒服,美好,深情,好听","type":"gedan"},{"listid":"6628","pic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_dcf87bf7ee034301d975230dd6ebc9f4.jpg","listenum":"16034","collectnum":"405","title":"美国队长竟然是卧底？！","tag":"欧美,伤感,好听","type":"gedan"}]}
     */

    private int error_code;
    /**
     * title : 热门歌单
     * list : [{"listid":"6634","pic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_affd48cf32d09efe2046f67d1f46faad.jpg","listenum":"7900","collectnum":"348","title":"总有一首歌让你沉醉","tag":"好听,沉醉,舒服,经典","type":"gedan"},{"listid":"6633","pic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_5980ff0314f8abf99179691a0f2a0492.jpg","listenum":"16440","collectnum":"351","title":"用音乐唱出暗恋的小心思","tag":"深情,好听,浪漫","type":"gedan"},{"listid":"6631","pic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_affd48cf32d09efe2046f67d1f46faad.jpg","listenum":"14273","collectnum":"371","title":"唱进你心的温柔声音","tag":"舒服,美好,深情,好听","type":"gedan"},{"listid":"6628","pic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_dcf87bf7ee034301d975230dd6ebc9f4.jpg","listenum":"16034","collectnum":"405","title":"美国队长竟然是卧底？！","tag":"欧美,伤感,好听","type":"gedan"}]
     */

    private ContentBean content;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        private String title;
        /**
         * listid : 6634
         * pic : http://business.cdn.qianqian.com/qianqian/pic/bos_client_affd48cf32d09efe2046f67d1f46faad.jpg
         * listenum : 7900
         * collectnum : 348
         * title : 总有一首歌让你沉醉
         * tag : 好听,沉醉,舒服,经典
         * type : gedan
         */

        private List<ListBean> list;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String listid;
            private String pic;
            private String listenum;
            private String collectnum;
            private String title;
            private String tag;
            private String type;

            public String getListid() {
                return listid;
            }

            public void setListid(String listid) {
                this.listid = listid;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getListenum() {
                return listenum;
            }

            public void setListenum(String listenum) {
                this.listenum = listenum;
            }

            public String getCollectnum() {
                return collectnum;
            }

            public void setCollectnum(String collectnum) {
                this.collectnum = collectnum;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
