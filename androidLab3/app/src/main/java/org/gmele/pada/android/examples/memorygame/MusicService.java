package org.gmele.pada.android.examples.memorygame;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.view.Gravity;
import android.widget.Toast;

import java.io.IOException;

public class MusicService extends Service implements MediaPlayer.OnCompletionListener
{
    IBinder Binder;
    public MusicService ()
    {
        Binder = new MyBinder ();
        //ShowMessage ("Service: Constructing....");
    }

    String[] SongTitles = {"Νοσταλγός του Rock N Roll", "Μάλιστα Κύριε", "Nothing Else Matters"};
    int[] SongIDs;
    int CurSong;
    MediaPlayer MP;
    boolean Act;
    GlobalData GD;

    @Override
    public void onCreate ()
    {
        super.onCreate ();
        ShowMessage ("Service: Creating ....");
        SongIDs = new int[3];
        SongIDs[0] = R.raw.nostalgosrocknroll;
        SongIDs[1] = R.raw.malistakyrie;
        SongIDs[2] = R.raw.nothingelsematters;
        CurSong = 0;
        Act = false;
        MP = MediaPlayer.create (this, SongIDs[CurSong]);
        MP.setOnCompletionListener (this);
        GD = GlobalData.getInstance ();
    }

    @Override
    public IBinder onBind (Intent intent)
    {
        ShowMessage ("Service: New Bind...");
        return Binder;
    }

    @Override
    public boolean onUnbind (Intent intent)
    {
        super.onUnbind (intent);
        ShowMessage ("Service: UnBinding....");
        return true;
    }


    @Override
    public void onDestroy()
    {
        //Δοκιμαστε να αφαιρεσετε τις επομενες τρεις γραμμες.
        if (Act)
            MP.stop ();
        MP.release ();
        super.onDestroy();
        ShowMessage ("Service: Destroying....");
    }


    void ShowMessage (String Mess)
    {
        Toast Tst = Toast.makeText (this, Mess, Toast.LENGTH_SHORT);
        Tst.setGravity (Gravity.CENTER, 0, 0);
        Tst.show ();

    }

    public class MyBinder extends Binder
    {
        MusicService getService()
        {
            return MusicService.this;
        }
    }

    @Override
    public void onCompletion (MediaPlayer mp)
    {
        ShowMessage (SongTitles[CurSong] + " Completed..." );
        DoNext ();
    }

    public void DoStart ()
    {
        ShowMessage ("Music: Start...");
        Act = true;
        MP.start ();
        SendMessage (false, true, true);
        SendMessage (SongTitles[CurSong]);

    }

    public void DoPause ()
    {
        ShowMessage ("Music: Pause");
        Act = false;
        MP.pause ();
        SendMessage (true, false, true);
        //BtStart.setImageResource (R.drawable.playb);
    }

    public void DoStop ()
    {
        ShowMessage ("Music: Stop");
        Act = false;
        MP.stop ();
        try
        {
            MP.prepare ();
        }
        catch (IOException E)
        {
            ShowMessage ("Error in repreparing: " + E.getMessage ());
        }
        SendMessage (true, false, false);
        //BtStart.setImageResource (R.drawable.playb);
    }

    public void DoNext ()
    {
        ShowMessage ("Music: Next....");
        if (MP.isPlaying ())
            MP.stop ();
        if (++CurSong == 3)
            CurSong = 0;
        //TvSong.setText (SongTitles[CurSong]);
        MP = MediaPlayer.create (this, SongIDs[CurSong]);
        MP.setOnCompletionListener (this);
        if (Act)
            DoStart ();
        SendMessage (SongTitles[CurSong]);
    }

    public void DoPrev ()
    {
        ShowMessage ("Music: Prev");
        if (MP.isPlaying ())
            MP.stop ();
        if (--CurSong == -1)
            CurSong = 2;
        //TvSong.setText (SongTitles[CurSong]);
        MP = MediaPlayer.create (this, SongIDs[CurSong]);
        if (Act)
            DoStart ();
        SendMessage (SongTitles[CurSong]);
    }

    void SendMessage (boolean B1, boolean B2, boolean B3)
    {
        Bundle b = new Bundle ();
        boolean[] Dat = new boolean [3];
        Dat[0] = B1;
        Dat[1] = B2;
        Dat[2] = B3;
        b.putBooleanArray ("MenuOnOff", Dat);
        Message msg = new Message ();
        msg.setData (b);
        GD.ActHandler.sendMessage (msg);
    }

    void SendMessage (String Sng)
    {
        Bundle b = new Bundle ();
        b.putString ("Song", Sng);
        Message msg = new Message ();
        msg.setData (b);
        GD.ActHandler.sendMessage (msg);
    }

}
