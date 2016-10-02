package com.example.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.bluetoothchat.Comment;
import com.example.android.bluetoothchat.DBHandler;
import com.example.android.bluetoothchat.Msg;
import com.example.android.bluetoothchat.R;

import java.util.ArrayList;
import java.util.List;

public class CommentList extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Comment> itemList = new ArrayList<>();
    private EditText mEdit;
    private DBHandler dbHandler = new DBHandler(this);
    private String messageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        Intent intent = getIntent();
        messageID = intent.getStringExtra(MsgAdapter.MESSAGE_ID);
        mRecyclerView = (RecyclerView) findViewById(R.id.comment_recycler_view);
        mEdit   = (EditText)findViewById(R.id.comment);

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
        /*Comment item;

        item = new Comment("I bought this. Itz awsome", null, "2016-09-20");
        itemList.add(item);

        item = new Comment("I bought this. Itz awsome", null, "2016-09-20");
        itemList.add(item);

        item = new Comment("I bought this. Itz awsome", null, "2016-09-20");
        itemList.add(item);*/
        itemList.clear();
        itemList.addAll(dbHandler.getComments(messageID));
        mAdapter.notifyDataSetChanged();
    }

    public void send(View v){
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        dbHandler.addMsg(new Msg("123456789-"+ts, ts, 2, messageID, mEdit.getText().toString(), 0, 0, null ));
        mEdit.setText(null);
        itemList.clear();
        itemList.addAll(dbHandler.getComments(messageID));
        mAdapter.notifyDataSetChanged();
    }
}
