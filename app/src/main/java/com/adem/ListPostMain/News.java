package com.adem.ListPostMain;

/**
 * Created by DELL on 3/7/2017.
 */

public class News {
    private String time, title, image, content, link;

    public News() {
        this.time = "";
        this.title = "";
        this.image = "";
        this.content = "";
        this.link = "";
    }

    public News(String time, String title, String image, String content, String link) {
        this.time = time;
        this.title = title;
        this.image = image;
        this.content = content;
        this.link = link;
    }

    public void processCData(String s){
        int i = s.indexOf("src=");
        int j = s.indexOf(" ></a></br>");
        //src="http://img.f30.vnecdn.net/2017/02/25/SwordofGoujian-1487955621_180x108.jpg" ></a></br>
        this.image = s.substring(i+5, j-1);

        i = s.indexOf("</br>");
        j = s.length();
        this.content = s.substring(i+5, j);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
