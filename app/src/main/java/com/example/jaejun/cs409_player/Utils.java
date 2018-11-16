package com.example.jaejun.cs409_player;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by jaejun on 2018-10-11.
 */

public class Utils {
    private final static long currentTimeNanosOffset = (System.currentTimeMillis() * 1000000) - System.nanoTime();
    private final static String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/CS409_data.txt";

    public static long currentTimeNanos() {
        return System.nanoTime() + currentTimeNanosOffset;
    }

    public static void printStateAndTime(String state) {
        Log.d("state, time", state + ", " + String.valueOf(currentTimeNanos()));
    }

    public static void writeExperimentData(String data) {
        File file = new File(filePath);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);

            writer.append(data + "\n");
            writer.close();
            fileOutputStream.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
