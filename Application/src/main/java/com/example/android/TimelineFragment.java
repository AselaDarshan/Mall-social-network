package com.example.android;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.view.ViewGroup;

import com.example.android.MsgAdapter;
import com.example.android.bluetoothchat.DBHandler;
import com.example.android.bluetoothchat.Msg;
import com.example.android.bluetoothchat.MsgListener;
import com.example.android.bluetoothchat.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TimelineFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Msg> itemList = new ArrayList<>();
    private DBHandler dbHandler ;
    private boolean timeline = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_timeline, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.msg_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MsgAdapter(itemList, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        dbHandler = new DBHandler(getActivity().getApplicationContext());

        setUpData();
    }
    @Override
    public void onStart() {
        Log.d("Fragment Start","Start Fragment");
        super.onStart();
        if(!timeline)
            setUpData();
    }


    @Override
    public void onDestroy() {
        Log.d("Freagment Destroyed","Destroy Fragment");
        super.onDestroy();
    }

    @Override
    public void onResume() {
        Log.d("Fragment On Resume", "Resume Fragment");
        super.onResume();
        if(!timeline)
            setUpData();
    }

    private void setUpData() {
        timeline = true;
        /*String uid, Timestamp tstamp, int type, String inReplyToMessageID, String text, int rank, int noOfRankers, String image*/

        itemList.clear();
        itemList.addAll(dbHandler.getAllMsg());
        mAdapter.notifyDataSetChanged();
    }


    public void receive(String message){
        Log.d("BluetoothChatService","Message received : "+message);
        JSONObject msg = null;
        try {
            msg = new JSONObject(message);


            String UID = null;
            String tstamp = null;
            int type = 0;
            String inReplyToMessageID = null;
            String text = null;
            int rank = 0;
            int noOfRankers = 0;
            String image = null;

            try {
                UID = msg.getString("UID");
                tstamp = msg.getString("timeStamp");
                type = msg.getInt("type");
                if(msg.has("inReplyToMessageID"))
                    inReplyToMessageID = msg.getString("inReplyToMessageID");
                if(msg.has("text"))
                    text = msg.getString("text");
                rank = msg.getInt("rank");
                noOfRankers = msg.getInt("noOfRankers");
                if(msg.has("image"))
                    image = msg.getString("image");
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("JSONException","Exception occurred while getting data from JSON object");
            }

            dbHandler.checkMsg(new Msg(UID, tstamp, type, inReplyToMessageID, text, rank, noOfRankers, image));
            itemList.clear();
            itemList.addAll(dbHandler.getAllMsg());
            mAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSONException","Exception occurred while creating JSON object");
        }
    }
}
