package org.gmele.pada.android.examples.memorygame;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class MainAct extends AppCompatActivity implements View.OnClickListener {

    Boolean mResult = false;
    Boolean timerState = false;
    HorizontalScrollView HSVMain;
    TextView TvSong;
    GlobalData GD = GlobalData.getInstance();
    TextView TvFirst, TvSecond;
    ViewInfo InfoFirst, InfoSecond;
    ProgressBar PbTime;
    Timer MainTimer = new Timer();
    Menu MMenu;

    int CurTime;
    Handler MyHandler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message Mess) {
            Bundle b = Mess.getData();
            Set<String> Keys = b.keySet();
            if (Keys.contains("UpdTimer")) {
                int NV = b.getInt("UpdTimer");
                PbTime.setProgress(NV, true);
            }
            if (Keys.contains("EndGame")) {
                boolean WoL = b.getBoolean("EndGame");
                EndGame(WoL);
            }
            if (Keys.contains("MenuOnOff")) {
                boolean[] F = b.getBooleanArray("MenuOnOff");
                MMenu.getItem(0).setEnabled(F[0]);
                MMenu.getItem(1).setEnabled(F[1]);
                MMenu.getItem(2).setEnabled(F[2]);
            }
            if (Keys.contains("Song")) {
                String ST = b.getString("Song");
                TvSong.setText(ST);
            }
        }
    };

    TimerTask MainTask = new TimerTask() {
        @Override
        public void run() {
            GD.PassedTime++;
            timerState = true;
            if (GD.PassedTime > GD.TotalTime) {
                SendMessage("EndGame", false);
            } else {
                SendMessage("UpdTimer", GD.PassedTime);
            }
        }
    };
    //MusicService.MyBinder Binder;
    private ServiceConnection ServConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            GD.ServBound = false;
            ShowMessage("Activity unbinded with Music Service");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder Serv) {
            MusicService.MyBinder Binder = (MusicService.MyBinder) Serv;
            GD.Serv = Binder.getService();
            GD.ServBound = true;
            ShowMessage("Activity binded with Music Service");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_lay);
        HSVMain = findViewById(R.id.HSVMain);
        TvSong = findViewById(R.id.TvSong);

        if (savedInstanceState == null) {
            ShowMessage("Creating Game");
            CreateGame();
        }

        GD.ActHandler = MyHandler;
        Intent intent = new Intent(this, MusicService.class);


        //startService (intent);
        bindService(intent, ServConn, Context.BIND_AUTO_CREATE);

        ShowImages();

        TvFirst = TvSecond = null;
        InfoFirst = InfoSecond = null;
        PbTime = findViewById(R.id.PbTime);
        PbTime.setMax(GD.TotalTime);
        PbTime.setProgress(GD.PassedTime, true);
        MainTimer.schedule(MainTask, 2000, 1000);

        PhoneCallListener phoneListener = new PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager)
                this.getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.musicmenu, menu);
        MMenu = menu;
        return true;
    }

    @Override
    public void onClick(View v) {
        TextView Pressed = (TextView) v;
        ViewInfo Info = (ViewInfo) v.getTag();
        if (Info.Opened)
            return;
        if (TvFirst != null && TvSecond != null) {
            System.out.println("*** Should never be here");
            return;
        }
        if (TvFirst == null) {
            TvFirst = Pressed;
            InfoFirst = Info;
        } else {
            TvSecond = Pressed;
            InfoSecond = Info;
        }
        Info.Opened = true;
        Pressed.setText(String.valueOf(Info.Value));
        if (TvSecond != null)
            CheckPair();

    }

//    public void onSaveInstanceState(Bundle Bun) {
//        super.onSaveInstanceState(Bun);
//        ShowMessage("Saving Instance...");
//        Bun.putBoolean("Playing", true);
//    }

    public void onDestroy() {
        super.onDestroy();
        ShowMessage("Destroying...");
        for (TextView tmp : GD.Matrix)
            ((TableRow) tmp.getParent()).removeView(tmp);
        if (GD.ServBound)
            unbindService(ServConn);
    }

    public void onPause() {
        super.onPause();
        timerState = false;
        MainTimer.cancel();
        MainTimer.purge();

    }

    public void onResume() {
        super.onResume();

        if (!timerState) {
            Timer newTimer = new Timer();
            TimerTask newTask = new TimerTask() {
                @Override
                public void run() {
                    GD.PassedTime++;
                    timerState = true;
                    if (GD.PassedTime > GD.TotalTime) {
                        SendMessage("EndGame", false);
                    } else {
                        SendMessage("UpdTimer", GD.PassedTime);
                    }
                }
            };
            newTimer.schedule(newTask, 2000, 1000);
        }

    }

    void CheckPair() {
        if (InfoFirst.Value == InfoSecond.Value) {
            TvFirst = TvSecond = null;
            InfoFirst = InfoSecond = null;
        } else {
            final Timer Ti = new Timer();
            TimerTask Tt = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TvFirst.setText("?");
                            TvSecond.setText("?");
                            InfoFirst.Opened = InfoSecond.Opened = false;
                            TvFirst = TvSecond = null;
                            InfoFirst = InfoSecond = null;
                        }
                    });
                    Ti.cancel();
                }
            };
            Ti.schedule(Tt, 2000);
        }
    }

    void CreateGame() {
        Intent Int = getIntent();
        Bundle Bun = Int.getExtras();
        GD.Lines = Bun.getInt("Height");
        GD.Cols = Bun.getInt("Width");
        ShowMessage("***" + GD.Lines + "  " + GD.Cols);
        int[] Posis = Lottery(GD.Lines, GD.Cols);
        CreateImages(Posis);
        GD.TotalTime = Bun.getInt("Duration");
        GD.PassedTime = 0;
    }

    void ShowImages() {
        int Cur = 0;
        TableLayout TlMat = new TableLayout(this);
        for (int i = 0; i < GD.Lines; i++) {
            TableRow Row = new TableRow(this);
            for (int j = 0; j < GD.Cols; j++) {
                TextView T = GD.Matrix[Cur];
                if (((ViewInfo) T.getTag()).Opened)
                    T.setText(String.valueOf(((ViewInfo) T.getTag()).Value));
                else
                    T.setText("?");
                T.setOnClickListener(this);
                Row.addView(T);
                Cur++;
            }
            TlMat.addView(Row);
        }
        HSVMain.addView(TlMat);
    }

    void CreateImages(int[] Posis) {
        int W = Dp2Pixel(80);
        int H = Dp2Pixel(80);
        int S = GD.Lines * GD.Cols;
        int P10 = Dp2Pixel(10);
        GD.Matrix = new TextView[S];
        TableRow.LayoutParams LPs = new TableRow.LayoutParams();
        LPs.setMargins(P10, P10, 0, 0);
        for (int i = 0; i < S; i++) {
            TextView TvA = new TextView(this);
            TvA.setTextSize(TypedValue.COMPLEX_UNIT_SP, 60);
            TvA.setBackgroundColor(Color.RED);
            TvA.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TvA.setLayoutParams(LPs);
            TvA.setWidth(W);
            TvA.setHeight(H);
            TvA.setTag(new ViewInfo(i, Posis[i] + 1, false));
            GD.Matrix[i] = TvA;
        }
    }

    int[] Lottery(int L, int C) {
        Random R;
        int S = L * C;
        int[] Pin = new int[S];
        R = new Random();
        for (int i = 0; i < S; i++)
            Pin[i] = -1;
        for (int i = 0; i < S; i++) {
            int P;
            do {
                P = R.nextInt(S);
            }
            while (Pin[P] != -1);
            Pin[P] = i / 2;
        }
        return Pin;
    }

    int Dp2Pixel(int DPs) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (DPs * scale + 0.5f);
    }

    void ShowMessage(String Mess) {
        Toast Tst = Toast.makeText(this, Mess, Toast.LENGTH_SHORT);
        Tst.setGravity(Gravity.CENTER, 0, 0);
        Tst.show();

    }

    public void SendMessage(String Label, Object Mess) {
        Message msg = new Message();
        Bundle bun = new Bundle();
        if (Mess instanceof Integer)
            bun.putInt(Label, ((Integer) Mess).intValue());
        if (Mess instanceof Boolean)
            bun.putBoolean(Label, (Boolean) Mess);
        if (Mess instanceof String)
            bun.putString(Label, String.valueOf(Mess));
        msg.setData(bun);
        MyHandler.sendMessage(msg);
    }

    void EndGame(boolean WoL) {
        MainTimer.cancel();
        MainTimer.purge();
        if (WoL) {
            AlertMessage("ΚΕΡΔΙΣΑΤΕ!!!", "Η μνημη σας ειναι σε καλη κατασταση");
            finish();
        } else {
            Boolean lResult = AlertMessage("ΤΕΛΟΣ ΧΡΟΝΟΥ", "Η μνημη σας χρειαζεται... γυμναστικη");
            if (lResult) {
                for (TextView tmp : GD.Matrix) {
                    ViewInfo VI = (ViewInfo) (tmp.getTag());
                    if (!VI.Opened) {
                        tmp.setBackgroundColor(Color.BLUE);
                        tmp.setText(String.valueOf(VI.Value));
                    }
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 10000);
            }
        }
    }

    Boolean AlertMessage(String Title, String Message) {
        AlertDialog Dialog = new AlertDialog.Builder(MainAct.this).create();
        Dialog.setTitle(Title);
        Dialog.setMessage(Message);

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                throw new RuntimeException();
            }
        };

        Dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mResult = true;
                handler.sendMessage(handler.obtainMessage());
                dialog.dismiss();
                dialog.dismiss();
            }
        });
        Dialog.show();

        try {
            Looper.loop();
        } catch (RuntimeException ignored) {
        }

        return mResult;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        // Should Check if Service is Binded
        switch (item.getItemId()) {
            case R.id.MiStart:
                return true;
            case R.id.MiPause:
                GD.Serv.DoPause();
                return true;
            case R.id.MiStop:
                GD.Serv.DoStop();
                return true;
            case R.id.MiNext:
                GD.Serv.DoNext();
                return true;
            case R.id.MiPrevious:
                GD.Serv.DoPrev();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class PhoneCallListener extends PhoneStateListener {
        String TAG = "PhoneListener";
        private boolean isPhoneCalling = false;

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if (TelephonyManager.CALL_STATE_RINGING == state) {
                Log.i(TAG, "RINGING, number: " + incomingNumber);
                onPause();
            }
            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                Log.i(TAG, "OFFHOOK");
                isPhoneCalling = true;
                onResume();
            }
            if (TelephonyManager.CALL_STATE_IDLE == state) {
                Log.i(TAG, "IDLE");
                if (isPhoneCalling) {
                    Log.i(TAG, "restart app");
                    Intent i = getBaseContext().getPackageManager().
                            getLaunchIntentForPackage(getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    isPhoneCalling = false;
                    onResume();
                }
            }
        }
    }

    class ViewInfo {
        int Posis;
        int Value;
        boolean Opened;

        ViewInfo(int P, int V, boolean O) {
            Posis = P;
            Value = V;
            Opened = O;
        }
    }
}
