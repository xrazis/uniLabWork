package com.example.secondlab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        boolean status, result;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            status = extras.getBoolean("status");
            Intent mIntent = new Intent();
            result = !status;
            mIntent.putExtra("result", result);
            setResult(Activity.RESULT_OK, mIntent);
        }
        finish();
    }
}
