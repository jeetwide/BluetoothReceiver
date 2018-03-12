package com.example.jeetendraachtani.bluetoothreciever;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


// Program for Single view long press Componenet Click.

public class Main3Activity extends AppCompatActivity {


    @BindView(R.id.tv_bluetooth1)
    Button tv_bluetooth1;


    Boolean isbtnLongPressed = false;

    AudioManager manager;

    boolean shortPress = false;
    boolean longPress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ButterKnife.bind(this);
        takeKeyEvents(true);

        IntentFilter filter3 = new IntentFilter();
        filter3.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter3.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        registerReceiver(mBroadcastReceiver3, filter3);

        tv_bluetooth1.setFocusable(false);

        tv_bluetooth1.setOnLongClickListener(tv1HoldListener);
        tv_bluetooth1.setOnTouchListener(tv1TouchListener);
    }


    View.OnLongClickListener tv1HoldListener = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View pView) {
            Toast.makeText(Main3Activity.this, "Long Press Starts!!!!", Toast.LENGTH_SHORT).show();
            tv_bluetooth1.setFocusable(true);
            isbtnLongPressed = true;
            return true;
        }
    };


    View.OnTouchListener tv1TouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View pView, MotionEvent pEvent) {
            pView.onTouchEvent(pEvent);

            if (pEvent.getAction() == MotionEvent.ACTION_UP) {
                if (isbtnLongPressed) {
                    Toast.makeText(Main3Activity.this, "Long Press Released ", Toast.LENGTH_SHORT).show();
                    tv_bluetooth1.setFocusable(false);
                    isbtnLongPressed = false;
                }
            }
            return false;
        }
    };

    private final BroadcastReceiver mBroadcastReceiver3 = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            switch (action) {
                case BluetoothDevice.ACTION_ACL_CONNECTED:
                    Toast.makeText(context, "BlueTooth Connected", Toast.LENGTH_SHORT).show();
                    break;
                case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                    Toast.makeText(context, "BlueTooth DisConnected", Toast.LENGTH_SHORT).show();
                    break;


            }
        }
    };



    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Toast.makeText(this, "onKeyUp-" + keyCode, Toast.LENGTH_SHORT).show();

        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            Log.d(this.getClass().getName(), "onKeyUp - KEYCODE_ENTER: "+keyCode);


            tv_bluetooth1.setOnLongClickListener(tv1HoldListener);
            tv_bluetooth1.setOnTouchListener(tv1TouchListener);

            tv_bluetooth1.performClick();


        } else {
            tv_bluetooth1.setFocusable(false);
        }

        return super.onKeyUp(keyCode, event);
    }


    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            Log.d(this.getClass().getName(), "onKeyLongPress - KEYCODE_ENTER: "+keyCode);

            Toast.makeText(this, "Long Key Press", Toast.LENGTH_SHORT).show();

        }


        return super.onKeyLongPress(keyCode, event);
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                Log.d(this.getClass().getName(), "onKeyDown - KEYCODE_VOLUME_UP: "+event.getKeyCode());
                manager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                manager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_RAISE,
                        AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Log.d(this.getClass().getName(), "onKeyDown  - KEYCODE_VOLUME_DOWN: "+event.getKeyCode());
                manager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                manager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_LOWER,
                        AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
                return true;

            default:
                return super.onKeyDown(keyCode, event);
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver3);
    }

}
