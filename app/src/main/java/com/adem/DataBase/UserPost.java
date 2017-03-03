package com.adem.DataBase;

/**
 * Created by DELL on 3/3/2017.
 */

public class UserPost {
    String caption,listimages,listvideos,location,emotion,friendtag,hashtag;

    public UserPost() {
    }

    public UserPost(String caption, String listimages, String listvideos, String location, String emation, String friendtag, String hashtag) {
        this.caption = caption;
        this.listimages = listimages;
        this.listvideos = listvideos;
        this.location = location;
        this.emotion = emation;
        this.friendtag = friendtag;
        this.hashtag = hashtag;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getListimages() {
        return listimages;
    }

    public void setListimages(String listimages) {
        this.listimages = listimages;
    }

    public String getListvideos() {
        return listvideos;
    }

    public void setListvideos(String listvideos) {
        this.listvideos = listvideos;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emation) {
        this.emotion = emation;
    }

    public String getFriendtag() {
        return friendtag;
    }

    public void setFriendtag(String friendtag) {
        this.friendtag = friendtag;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}
