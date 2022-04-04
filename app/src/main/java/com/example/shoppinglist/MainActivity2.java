package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.example.android.ShoppingList.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void addItem(View view) {
        Button itembtn = (Button) (view);
        String item = itembtn.getText().toString();
        //sending btn text back to main activity
        Intent intent = new Intent();
        intent.putExtra(EXTRA_REPLY, item);
        setResult(RESULT_OK,intent);
        finish();
    }
}