package com.example.android.bluetoothchat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by isuru on 10/2/2016.
 */

public class JsonHandler {
    ArrayList<Msg> msgArrayList;
    DBHandler handler;
    public void fromJson(String serverData){
        try {
            msgArrayList = new ArrayList<Msg>();
            JSONObject jsonObject = new JSONObject(serverData);
            JSONArray jsonArray = jsonObject.getJSONArray("messages");
            for (int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObjectMsg = jsonArray.getJSONObject(i);
                String msgUid = jsonObjectMsg.getString("uid");
                int msgType = jsonObjectMsg.getInt("type");
                String msgText = jsonObjectMsg.getString("text");
                int msgRank = jsonObjectMsg.getInt("rank");
                String msgTstamp = jsonObjectMsg.getString("tStamp");
                int msgNoOfRankers = jsonObjectMsg.getInt("noOfRankers");
                String msgImage = jsonObjectMsg.getString("image");
                String msgInReplyToMessageId = jsonObjectMsg.getString("inReplyToMessageID");
                Msg msg = new Msg();
                msg.setUID(msgUid);
                msg.setText(msgText);
                msg.setImage(msgImage);
                msg.setRank(msgRank);
                msg.setInReplyToMessageID(msgInReplyToMessageId);
                msg.setType(msgType);
                msg.setNoOfRankers(msgNoOfRankers);
                msg.setTstamp(msgTstamp);

                handler.addMsg(msg);// Inserting into DB
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
