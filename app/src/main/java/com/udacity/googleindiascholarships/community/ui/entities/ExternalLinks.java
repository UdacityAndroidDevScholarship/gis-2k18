package com.udacity.googleindiascholarships.community.ui.entities;

/**
 * Created by Rajat Kumar Gupta on 26-04-2018.
 */

public class ExternalLinks {
    private String linkUrl;
    private String linkPostedBy;
    private String linkDescription;

    public ExternalLinks() {
    }
    public ExternalLinks(String linkUrl, String linkPostedBy,String linkDescription) {
        this.linkUrl = linkUrl;
        this.linkPostedBy = linkPostedBy;
        this.linkDescription = linkDescription;
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

    public String getLinkDescription() {
        return linkDescription;
    }

    public void setLinkDescription(String linkDescription) {
        this.linkDescription = linkDescription;
    }
}
