package com.example.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.bluetoothchat.Msg;
import com.example.android.bluetoothchat.R;

import java.util.ArrayList;
import java.util.List;

public class Timeline extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Msg> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CardAdapter(itemList, Timeline.this);
        mRecyclerView.setAdapter(mAdapter);

        setUpData();
    }

    private void setUpData() {
        /*String uid, Timestamp tstamp, int type, String inReplyToMessageID, String text, int rank, int noOfRankers, String image*/
        Msg item;

        item = new Msg(1, "123456789-ABCD", "time-stamp", 1, null, "This is a great offer", 3, 10, null );
        itemList.add(item);

        item = new Msg(2, "123456789-ABCD", "time-stamp", 1, null, "This isn't good material", 1, 10, null );
        itemList.add(item);

        item = new Msg(3, "123456789-ABCD", "time-stamp", 1, null, "This is a great offer", 5, 10, null );
        itemList.add(item);

        mAdapter.notifyDataSetChanged();
    }
}
