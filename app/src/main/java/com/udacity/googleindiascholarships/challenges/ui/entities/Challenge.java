package com.udacity.googleindiascholarships.challenges.ui.entities;

import android.media.Image;

public class Challenge {
    private String nameChallenge;
    private String authorNameChallenge;
    private String imageUploadChallenge;
    private String descriptionChallenge;
    private String gitHubLinkChallenge;
    private Image imageAuthor;
    private int placholderImage;

    public Challenge(String authorNameChallenge) {
        this.authorNameChallenge = authorNameChallenge;
    }


    public String getAuthorNameChallenge() {
        return authorNameChallenge;
    }

    public String getNameChallenge() {
        return nameChallenge;
    }

    public String getDescriptionChallenge() {
        return descriptionChallenge;
    }

    public String getGitHubLinkChallenge() {
        return gitHubLinkChallenge;
    }

    public String getImageUploadChallenge() {
        return imageUploadChallenge;
    }

    public Image getImageAuthor() {
        return imageAuthor;
    }
}
