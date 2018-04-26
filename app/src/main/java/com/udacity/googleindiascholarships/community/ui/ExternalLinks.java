package com.udacity.googleindiascholarships.community.ui;

/**
 * Created by Rajat Kumar Gupta on 26-04-2018.
 */

public class ExternalLinks {
    private String linkUrl;
    private String linkPostedBy;
    private String linkImageUrl;

    public ExternalLinks(String linkUrl, String linkPostedBy) {
        this.linkUrl = linkUrl;
        this.linkPostedBy = linkPostedBy;
    }

    public ExternalLinks(String linkUrl, String linkPostedBy, String linkImageUrl) {
        this.linkUrl = linkUrl;
        this.linkPostedBy = linkPostedBy;
        this.linkImageUrl = linkImageUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getLinkPostedBy() {
        return linkPostedBy;
    }

    public void setLinkPostedBy(String linkPostedBy) {
        this.linkPostedBy = linkPostedBy;
    }

    public String getLinkImageUrl() {
        return linkImageUrl;
    }

    public void setLinkImageUrl(String linkImageUrl) {
        this.linkImageUrl = linkImageUrl;
    }
}
