package org.gmele.pada.android.examples.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class OptionsAct extends AppCompatActivity implements View.OnClickListener
{
    Button BtStart;

    EditText EtWidth;
    EditText EtHeight;
    Switch SwMusic;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.options_lay);
        EtWidth = findViewById (R.id.EtWidth);
        EtHeight = findViewById (R.id.EtHeight);
        SwMusic = findViewById (R.id.SwMusic);
        BtStart = findViewById (R.id.BtStart);
        BtStart.setOnClickListener (this);

    }

    @Override
    public void onClick (View v)
    {
        if (v == BtStart)
        {
            int Cols = Integer.parseInt (EtWidth.getText ().toString ());
            int Lins = Integer.parseInt (EtHeight.getText ().toString ());
            if ((Cols * Lins) % 2 != 0)
            {
                ShowMessage ("Απαιτειται αρτιος αριθμος εικονων");
                return;
            }
            Intent Int = new Intent (this, MainAct.class);
            Bundle Bun = new Bundle ();
            Bun.putInt ("Width", Cols);
            Bun.putInt ("Height", Lins);
            Bun.putBoolean ("Music", SwMusic.isActivated ());
            Int.putExtras (Bun);
            startActivity (Int);
        }
    }

    void ShowMessage (String Mess)
    {
        Toast Tst = Toast.makeText (this, Mess, Toast.LENGTH_SHORT);
        Tst.setGravity (Gravity.CENTER, 0, 0);
        Tst.show ();

    }

}

