package com.example.administrator.testclient.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

public class ImgUrl extends SimpleBannerInfo {
    public ImgUrl(String url) {
        this.url = url;
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Object getXBannerUrl() {
        return url;
    }
}
