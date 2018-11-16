package com.example.jaejun.cs409_player;

import java.util.ArrayList;

/**
 * Created by jaejun on 2018-10-11.
 */

public interface Callback {
    void callback(long startTime, ArrayList<String> bufferingTimes);
}
