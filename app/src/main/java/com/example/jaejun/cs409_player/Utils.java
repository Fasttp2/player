package com.example.jaejun.cs409_player;

import android.util.Log;

/**
 * Created by jaejun on 2018-10-11.
 */

public class Utils {
    private final static long currentTimeNanosOffset = (System.currentTimeMillis() * 1000000) - System.nanoTime();

    public static long currentTimeNanos() {
        return System.nanoTime() + currentTimeNanosOffset;
    }

    public static void printStateAndTime(String state) {
        Log.d("state, time", state + ", " + String.valueOf(currentTimeNanos()));
    }
}
