package com.example.chatapp.Utills;

import com.google.firebase.database.ServerValue;

public class Comment {

    private String username, profileImageUrl, comment;
    private  Object timestamp;


    public Comment() {
    }

    public Comment(String username, String profileImageUrl, String comment) {
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.comment = comment;

        this.timestamp= ServerValue.TIMESTAMP;
    }

    public Comment(String username, String profileImageUrl, String comment, Object timestamp) {
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }
}