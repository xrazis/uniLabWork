package com.example.lab1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                new AlertDialog.Builder(MainActivity.this).setTitle("Info").setMessage("creator info").setNegativeButton(android.R.string.no, null).setIcon(android.R.drawable.ic_dialog_alert).show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ToggleButton toggle01 = findViewById(R.id.toggleButton);
        final ToggleButton toggle02 = findViewById(R.id.toggleButton2);
        final ImageView Led01 = findViewById(R.id.Led_01);
        final ImageView Led02 = findViewById(R.id.Led_02);

        toggle01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Led01.setImageResource(R.drawable.led_on);
                } else {
                    Led01.setImageResource(R.drawable.led_off);
                }
            }
        });

        toggle02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Led02.setImageResource(R.drawable.led_on);
                } else {
                    Led02.setImageResource(R.drawable.led_off);
                }
            }
        });

        final Button button = findViewById(R.id.reset);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Led01.setImageResource(R.drawable.led_off);
                Led02.setImageResource(R.drawable.led_off);
                toggle01.setChecked(false);
                toggle02.setChecked(false);
            }
        });

    }
}
