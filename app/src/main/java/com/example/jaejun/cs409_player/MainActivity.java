package com.example.jaejun.cs409_player;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Intent devPlayIntent = null, devCalcIntent = null;
    private Intent stagePlayIntent = null, stageCalcIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();

        devPlayIntent = new Intent(this, DevPlayActivity.class);
        devCalcIntent = new Intent(this, DevCalcActivity.class);
        stagePlayIntent = new Intent(this, StagePlayActivity.class);
        stageCalcIntent = new Intent(this, StageCalcActivity.class);
    }

    private void checkPermission() {
        if (!(ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                == PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET}, 123);
        }
    }

    public void click(View view) {
        switch(view.getId()){
            case R.id.devPlay:
                devPlayIntent.putExtra("mode", "play");
                startActivity(devPlayIntent);
                break;
            case R.id.devCalc:
                startActivity(devCalcIntent);
                break;
            case R.id.stagePlay:
                stagePlayIntent.putExtra("mode", "play");
                startActivity(stagePlayIntent);
                break;
            case R.id.stageCalc:
                startActivity(stageCalcIntent);
                break;
            default:
                break;
        }
    }
}