package com.example.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.bluetoothchat.Msg;
import com.example.android.bluetoothchat.MsgListener;
import com.example.android.bluetoothchat.R;

public class NewMessage extends AppCompatActivity {

    private EditText mEdit;
    private MsgListener msgListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        mEdit   = (EditText)findViewById(R.id.new_msg);
    }

    public void send(){
        msgListener.addMsg(new Msg("123456789-ABCD", "time-stamp", 1, null, mEdit.getText().toString(), 0, 0, null ));
    }
}
