package com.udacity.googleindiascholarships.projects.entities;

public class ContactModerator {

    private String mod_name;
    private String mod_title;
    private String mod_email;
    private String mod_linkedin;
    private String mod_github;
    private String mod_profile;

    public ContactModerator() {

    }

    public ContactModerator(String mod_name, String mod_email, String mod_github, String mod_linkedin, String mod_title, String mod_profile) {
        this.mod_email = mod_email;
        this.mod_name = mod_name;
        this.mod_github = mod_github;
        this.mod_linkedin = mod_linkedin;
        this.mod_profile = mod_profile;
        this.mod_title = mod_title;

    }

    public String getMod_email() {
        return mod_email;
    }

    public String getMod_github() {
        return mod_github;
    }

    public String getMod_linkedin() {
        return mod_linkedin;
    }

    public String getMod_name() {
        return mod_name;
    }

    public String getMod_profile() {
        return mod_profile;
    }

    public String getMod_title() {
        return mod_title;
    }


}
