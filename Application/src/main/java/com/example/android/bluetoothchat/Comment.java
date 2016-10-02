package com.example.android.bluetoothchat;

/**
 * Created by thilina on 10/2/16.
 */
public class Comment {

    private String text;
    private String image;
    private String timeStamp;

    public Comment (String text, String image, String timeStamp){
        this.text = text;
        this.image = image;
        this.timeStamp = timeStamp;
    }

    public String getImage() {
        return image;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getText() {
        return text;
    }
}
