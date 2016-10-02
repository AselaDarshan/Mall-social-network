package com.example.android.bluetoothchat;

/**
 * Created by Isuru on 10/02/2016.
 */
public class Msg {
    private int ID;
    private String UID;
    private String tstamp;
    private int type;
    /*
    * 1-new
    * 2-reply
    * 3-rank
    * */
    private String inReplyToMessageID;
    private String text;
    private int rank;
    private int noOfRankers;
    private String image;

    public Msg(){}

    public Msg (int id,String uid, String tstamp, int type, String inReplyToMessageID, String text, int rank, int noOfRankers, String image){
        this.ID=id;
        this.UID = uid;
        this.tstamp = tstamp;
        this.type = type;
        this.inReplyToMessageID = inReplyToMessageID;
        this.text = text;
        this.rank = rank;
        this.noOfRankers = noOfRankers;
        this.image = image;
    }

    public Msg (String uid, String tstamp, int type, String inReplyToMessageID, String text, int rank, int noOfRankers, String image){
        this.UID = uid;
        this.tstamp = tstamp;
        this.type = type;
        this.inReplyToMessageID = inReplyToMessageID;
        this.text = text;
        this.rank = rank;
        this.noOfRankers = noOfRankers;
        this.image = image;
    }

    public int getNoOfRankers() {
        return noOfRankers;
    }
    public void setNoOfRankers(int noOfRankers) {
        this.noOfRankers = noOfRankers;
    }

    public int getType() {return type;}
    public void setType(int type) {
        this.type = type;
    }

    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getInReplyToMessageID() {
        return inReplyToMessageID;
    }
    public void setInReplyToMessageID(String inReplyToMessageID) {
        this.inReplyToMessageID = inReplyToMessageID;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getUID() {
        return UID;
    }
    public void setUID(String uid) {
        this.UID = uid;
    }
    public int getID() {
        return ID;
    }
    public void setID(int id) {
        this.ID = id;
    }

    public String getTstamp() {
        return tstamp;
    }
    public void setTstamp(String tstamp) {
        this.tstamp = tstamp;
    }
}
