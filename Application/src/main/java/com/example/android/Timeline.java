package com.example.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.bluetoothchat.DBHandler;
import com.example.android.bluetoothchat.Msg;
import com.example.android.bluetoothchat.MsgListener;
import com.example.android.bluetoothchat.R;

import java.util.ArrayList;
import java.util.List;

public class Timeline extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Msg> itemList = new ArrayList<>();
    private EditText mEdit;
    private DBHandler dbHandler = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        mRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        mEdit   = (EditText)findViewById(R.id.new_msg);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MsgAdapter(itemList, Timeline.this);
        mRecyclerView.setAdapter(mAdapter);

        setUpData();
    }

    private void setUpData() {
        /*String uid, Timestamp tstamp, int type, String inReplyToMessageID, String text, int rank, int noOfRankers, String image*/
        /*Msg item;

        item = new Msg(1, "123456789-ABCD", "time-stamp", 1, null, "This is a great offer", 3, 10, null );
        itemList.add(item);

        item = new Msg(2, "123456789-ABCD", "time-stamp", 1, null, "This isn't good material", 1, 10, null );
        itemList.add(item);

        item = new Msg(3, "123456789-ABCD", "time-stamp", 1, null, "This is a great offer", 5, 10, null );
        itemList.add(item);

        item = new Msg(1, "123456789-ABCD", "time-stamp", 1, null, "This is a great offer", 3, 10, null );
        itemList.add(item);

        item = new Msg(2, "123456789-ABCD", "time-stamp", 1, null, "This isn't good material", 1, 10, null );
        itemList.add(item);

        item = new Msg(3, "123456789-ABCD", "time-stamp", 1, null, "This is a great offer", 5, 10, null );
        itemList.add(item);*/

        itemList.clear();
        itemList.addAll(dbHandler.getAllMsg());
        mAdapter.notifyDataSetChanged();
    }

    public void send(View v){
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        dbHandler.addMsg(new Msg("123456789-"+ts, ts, 1, null, mEdit.getText().toString(), 0, 0, null ));
        mEdit.setText(null);
        itemList.clear();
        itemList.addAll(dbHandler.getAllMsg());
        mAdapter.notifyDataSetChanged();
    }
}
