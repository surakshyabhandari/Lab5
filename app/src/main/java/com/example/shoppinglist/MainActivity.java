package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;
    private ConstraintLayout parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent = (ConstraintLayout)  findViewById(R.id.layout);

        if (savedInstanceState != null){
            for (int index=0; index<parent.getChildCount(); index++){
                final TextView textItem = (TextView) parent.getChildAt(index);
                boolean isVisible = savedInstanceState.getBoolean(Integer.toString(index) + "_visible");

                if (isVisible) {
                    textItem.setVisibility(View.VISIBLE);
                    String text = savedInstanceState.getString(Integer.toString(index) + "_text");
                    textItem.setText(text);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
        for (int index=0; index<parent.getChildCount(); index++){
            final TextView child = (TextView) parent.getChildAt(index);

            if (child.getVisibility() == View.VISIBLE) {
                outState.putBoolean(Integer.toString(index) + "_visible", true);
                outState.putString(Integer.toString(index) + "_text", child.getText().toString());
            }

        }
    }


    public void addItem(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    public void onActivityResult(int request, int result, Intent data){
        super.onActivityResult(request, result, data);
        if(request == TEXT_REQUEST){
            if(result == RESULT_OK){
                String item = data.getStringExtra(MainActivity2.EXTRA_REPLY);

                for(int index = 0; index < parent.getChildCount(); index++){
                    final TextView textItem = (TextView) parent.getChildAt(index);

                    if (textItem.getVisibility() == View.INVISIBLE) {
                        textItem.setText(Integer.toString(index+1) + ". " + item);
                        textItem.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }
        }
    }
}