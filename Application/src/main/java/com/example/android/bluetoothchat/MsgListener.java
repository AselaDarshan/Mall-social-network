package com.example.android.bluetoothchat;
/**
 * Created by Isuru on 10/02/2016.
 */
import java.util.ArrayList;


public interface MsgListener {

    public void addMsg(Msg msg);

    public ArrayList<Msg> getAllMsg();

    public int getMsgCount();

    public ArrayList<Comment> getComments(String uid);

    public void updateRank(String uid, int rank);

    public boolean checkMsg(Msg msg);
}
