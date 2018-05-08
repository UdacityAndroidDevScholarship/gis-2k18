package com.udacity.googleindiascholarships.challenges.entities;

import android.media.Image;

import java.util.ArrayList;

public class Challenge {
    private String challenge_name;
   private String challenge_author;
    private String challenge_description;
    private String challenge_github_url;
    ArrayList<String> list_challenge_submissions;


    Challenge(){

    }






   public Challenge(String challenge_name){
        this.challenge_name = challenge_name;
   }

   public Challenge(String challenge_author,String challenge_description,String challenge_github_url){
        this.challenge_author = challenge_author;
        this.challenge_description = challenge_description;
        this.challenge_github_url = challenge_github_url;

   }

    public String getChallenge_author() {
        return challenge_author;
    }

    public String getChallenge_description() {
        return challenge_description;
    }

    public String getChallenge_github_url() {
        return challenge_github_url;
    }

    public String getChallenge_name() {
        return challenge_name;
    }

}
