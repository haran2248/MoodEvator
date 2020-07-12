package com.example.moodevator;

public class Post {
    String caption,name,Imguri;
    public Post(){

    }

    public Post(String caption, String name, String imguri) {
        this.caption = caption;
        this.name = name;
        Imguri = imguri;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImguri() {
        return Imguri;
    }

    public void setImguri(String imguri) {
        Imguri = imguri;
    }
}
