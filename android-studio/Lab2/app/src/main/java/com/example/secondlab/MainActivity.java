package com.example.secondlab;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver br = new MyBroadcastReceiver();
    private Button clear;
    private ToggleButton btn01, btn02, btn03;
    private ImageView Led01, Led02, Led03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter("MyBroadcastReceiver");
        this.registerReceiver(br, filter);

        btn01 = findViewById(R.id.button01);
        btn03 = findViewById(R.id.button03);
        btn02 = findViewById(R.id.button02);
        clear = findViewById(R.id.clearbtn);

        Led01 = findViewById(R.id.Led01);
        Led02 = findViewById(R.id.Led02);
        Led03 = findViewById(R.id.Led03);

        btn01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent mIntent = new Intent(MainActivity.this, SecondActivity.class);
                if (isChecked) {
                    mIntent.putExtra("status", false);
                } else {
                    mIntent.putExtra("status", true);
                }
                startActivityForResult(mIntent, 1);
            }
        });

        btn02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent mIntent = new Intent(MainActivity.this, SecondActivity.class);
                if (isChecked) {
                    mIntent.putExtra("status", false);
                } else {
                    mIntent.putExtra("status", true);
                }
                startActivityForResult(mIntent, 2);
            }
        });

        btn03.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent mIntent = new Intent(MainActivity.this, SecondActivity.class);
                if (isChecked) {
                    mIntent.putExtra("status", false);
                } else {
                    mIntent.putExtra("status", true);
                }
                startActivityForResult(mIntent, 3);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent("MyBroadcastReceiver");
                sendBroadcast(intent);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent mIntent) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                boolean result = Objects.requireNonNull(mIntent.getExtras()).getBoolean("result");
                if (result) {
                    Led01.setImageResource(R.drawable.led_on);
                } else {
                    Led01.setImageResource(R.drawable.led_off);
                }
            } else if (requestCode == 2) {
                boolean result = Objects.requireNonNull(mIntent.getExtras()).getBoolean("result");
                if (result) {
                    Led02.setImageResource(R.drawable.led_on);
                } else {
                    Led02.setImageResource(R.drawable.led_off);
                }
            } else if (requestCode == 3) {
                boolean result = Objects.requireNonNull(mIntent.getExtras()).getBoolean("result");
                if (result) {
                    Led03.setImageResource(R.drawable.led_on);
                } else {
                    Led03.setImageResource(R.drawable.led_off);
                }
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(br);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            btn01.setChecked(false);
            btn02.setChecked(false);
            btn03.setChecked(false);

            Led01.setImageResource(R.drawable.led_off);
            Led02.setImageResource(R.drawable.led_off);
            Led03.setImageResource(R.drawable.led_off);
        }
    }
}
