package com.example.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.bluetoothchat.Comment;
import com.example.android.bluetoothchat.Msg;
import com.example.android.bluetoothchat.R;

import java.util.ArrayList;
import java.util.List;

public class CommentList extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Comment> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.comment_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CommentAdapter(itemList, CommentList.this);
        mRecyclerView.setAdapter(mAdapter);

        setUpData();
    }

    private void setUpData() {
        /*String uid, Timestamp tstamp, int type, String inReplyToMessageID, String text, int rank, int noOfRankers, String image*/
        Comment item;

        item = new Comment("I bought this. Itz awsome", null, "2016-09-20");
        itemList.add(item);

        item = new Comment("I bought this. Itz awsome", null, "2016-09-20");
        itemList.add(item);

        item = new Comment("I bought this. Itz awsome", null, "2016-09-20");
        itemList.add(item);

        mAdapter.notifyDataSetChanged();
    }
}