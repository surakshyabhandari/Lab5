package com.example.twoactivities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;
    private EditText messageText;
    private TextView replyheader;
    private TextView replytext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");
        messageText = findViewById(R.id.messageTextbar);
        Intent intenti = getIntent();
        String message = intenti.getStringExtra(SecondActivity.EXTRA_REPLY);
        replyheader = findViewById(R.id.replyheader);
        replytext = findViewById(R.id.replytext);
        replytext.setText(message);
        if (savedInstanceState != null) {
            boolean isVisible = savedInstanceState.getBoolean("reply is visible");
            if (isVisible) {
                replyheader.setVisibility(View.VISIBLE);
                replyheader.setText(savedInstanceState.getString("reply text"));
                replytext.setVisibility(View.VISIBLE);
            }
        }
    }

    public void secondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        String message = messageText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, TEXT_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                replyheader.setVisibility(View.VISIBLE);
                replytext.setText(reply);
                replytext.setVisibility(View.VISIBLE);
            }
        }
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (replyheader.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text",replyheader.getText().toString());
        }
    }

}