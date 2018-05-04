package com.udacity.googleindiascholarships.projects.entities;

public class Project {

    private String project_name;
    private String project_description;
    private String project_logo_url;
    private String project_github_url;
    private int placeHolderImage;

    private int contactImage;


    public Project(){

    }

    public Project(String project_name, String project_description, String project_logo_url, String project_github_url) {
        this.project_name = project_name;
        this.project_description = project_description;
        this. project_logo_url =  project_logo_url;
        this.project_github_url = project_github_url;
    }

    public Project(String project_name, int placeholderImage) {
        this.project_name = project_name;
        this.placeHolderImage = placeholderImage;

    }

    public int getContactImage() {
        return contactImage;
    }

    public Project(int contactImage){
        this.contactImage = contactImage;
    }

    public int getPlaceHolderImage() {
        return placeHolderImage;
    }


    public String getProject_github_url() {
        return project_github_url;
    }

    public String getProject_logo_url() {
        return project_logo_url;
    }

    public String getProject_description() {
        return project_description;
    }

    public String getProject_name() {
        return project_name;
    }
}
