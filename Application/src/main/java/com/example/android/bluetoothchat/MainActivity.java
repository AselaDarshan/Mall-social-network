/*
* Copyright 2013 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/


package com.example.android.bluetoothchat;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.example.android.TimelineFragment;
import com.example.android.common.activities.SampleActivityBase;

import org.json.JSONObject;

/**
 * A simple launcher activity containing a summary sample description, sample log and a custom
 * {@link android.support.v4.app.Fragment} which can display a view.
 * <p>
 * For devices with displays with a width of 720dp or greater, the sample log is always visible,
 * on other devices it's visibility is controlled by an item on the Action Bar.
 */
public class MainActivity extends SampleActivityBase implements BluetoothChatFragment.ButtonClicked{

    public static final String TAG = "MainActivity";

    // Whether the Log Fragment is currently shown
    private boolean mLogShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            BluetoothChatFragment fragment = new BluetoothChatFragment();
            TimelineFragment timeLineFragment = new TimelineFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.timeline_fragment, timeLineFragment,"TimelineFragment Frag");
            transaction.add(R.id.sample_content_fragment, fragment, "Send message Frag");
            transaction.commit();
        }
    }

    @Override
    public void sendText(String message) {
        TimelineFragment frag = (TimelineFragment)
                getSupportFragmentManager().findFragmentById(R.id.timeline_fragment);
        frag.receive(message);
    }
}
