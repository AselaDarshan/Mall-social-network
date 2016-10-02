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

import java.util.ArrayList;
import java.util.List;

public class TimelineFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Msg> itemList = new ArrayList<>();
    private DBHandler dbHandler = new DBHandler(getContext());
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

    public void send(String msg){
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        dbHandler.addMsg(new Msg("123456789-"+ts, ts, 1, null, msg, 0, 0, null ));
        itemList.clear();
        itemList.addAll(dbHandler.getAllMsg());
        mAdapter.notifyDataSetChanged();
    }
}
