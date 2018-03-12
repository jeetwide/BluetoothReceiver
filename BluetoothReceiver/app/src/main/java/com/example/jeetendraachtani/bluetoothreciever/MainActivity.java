package com.example.jeetendraachtani.bluetoothreciever;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {



    private Button mSpeak;
    private boolean isSpeakButtonLongPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mSpeak = (Button)findViewById(R.id.speakbutton);
        mSpeak.setOnLongClickListener(speakHoldListener);
        mSpeak.setOnTouchListener(speakTouchListener);


    }
    private View.OnLongClickListener speakHoldListener = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View pView) {
            // Do something when your hold starts here.

            Toast.makeText(MainActivity.this, "LONG PRESS STARTS", Toast.LENGTH_SHORT).show();
            isSpeakButtonLongPressed = true;
            return true;
        }
    };

    private View.OnTouchListener speakTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View pView, MotionEvent pEvent) {
            pView.onTouchEvent(pEvent);
            // We're only interested in when the button is released.
            if (pEvent.getAction() == MotionEvent.ACTION_UP) {
                // We're only interested in anything if our speak button is currently pressed.
                if (isSpeakButtonLongPressed) {
                    // Do something when the button is released.
                    Toast.makeText(MainActivity.this, "BUtton is released", Toast.LENGTH_SHORT).show();
                    isSpeakButtonLongPressed = false;
                }
            }
            return false;
        }
    };
}
