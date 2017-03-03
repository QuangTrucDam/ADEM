package com.adem.DataBase;

/**
 * Created by DELL on 3/3/2017.
 */

public class UserFriends {
    String friendname,friendid,friendprofilepicture,friendlocation;

    public UserFriends() {
    }

    public UserFriends(String friendname, String friendid, String friendprofilepicture, String friendlocation) {
        this.friendname = friendname;
        this.friendid = friendid;
        this.friendprofilepicture = friendprofilepicture;
        this.friendlocation = friendlocation;
    }

    public String getFriendname() {
        return friendname;
    }

    public void setFriendname(String friendnam) {
        this.friendname = friendnam;
    }

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }

    public String getFriendprofilepicture() {
        return friendprofilepicture;
    }

    public void setFriendprofilepicture(String friendprofilepicture) {
        this.friendprofilepicture = friendprofilepicture;
    }

    public String getFriendlocation() {
        return friendlocation;
    }

    public void setFriendlocation(String friendlocation) {
        this.friendlocation = friendlocation;
    }
}
