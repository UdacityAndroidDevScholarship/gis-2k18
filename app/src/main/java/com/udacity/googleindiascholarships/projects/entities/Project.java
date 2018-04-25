package com.udacity.googleindiascholarships.projects.entities;

public class Project {

    private String name;
    private String description;
    private String githubLink;
    private String pathToImage;
    private int placeHolderImage;

    public Project(String name, String description, String githubLink, String pathToImage) {
        this.name = name;
        this.description = description;
        this.githubLink = githubLink;
        this.pathToImage = pathToImage;
    }

    public Project(String name, int placeholderImage) {
        this.name = name;
        this.placeHolderImage = placeholderImage;

    }

    public int getPlaceHolderImage() {
        return placeHolderImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }
}
