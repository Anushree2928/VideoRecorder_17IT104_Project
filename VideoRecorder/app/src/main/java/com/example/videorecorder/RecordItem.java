package com.example.videorecorder;

public class RecordItem {

    String id;
    String path;

    public RecordItem(String id, String path) {
        this.id = id;
        this.path = path;
    }

    //change the text code
    public void changeText(String text) {
        path = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

