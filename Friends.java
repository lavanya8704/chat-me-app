package com.example.chatapp.Utills;

public class Friends {
    private String username,profession,profileImageUrl;

    public Friends(String username, String profession, String profileImageUrl) {
        this.username = username;
        this.profession = profession;
        this.profileImageUrl = profileImageUrl;
    }

    public Friends() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}

