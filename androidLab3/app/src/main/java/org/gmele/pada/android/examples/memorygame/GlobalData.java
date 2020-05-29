package org.gmele.pada.android.examples.memorygame;

import android.widget.TextView;

import android.os.Handler;

final class GlobalData
{
    static private GlobalData Instance = null;
    int Lines;
    int Cols;
    int TotalTime;
    int PassedTime;
    boolean ActGame;
    TextView[] Matrix;
    MusicService Serv;
    boolean ServBound;
    Handler ActHandler;

    private GlobalData ()
    {
        ActGame = false;
        Matrix = null;
        Lines = Cols = 0;
        Serv = null;
        ServBound = false;
        ActHandler = null;
    }

    public static GlobalData getInstance ()
    {
        if (Instance == null)
            Instance = new GlobalData ();
        return Instance;
    }


}
