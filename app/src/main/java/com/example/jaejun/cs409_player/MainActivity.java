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

    private Intent devIntent = null;
    private Intent stageIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();

        devIntent = new Intent(this, DevActivity.class);
        stageIntent = new Intent(this, StageActivity.class);
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
            case R.id.devButton:
                startActivity(devIntent);
                break;
            case R.id.stageButton:
                startActivity(stageIntent);
                break;
            default:
                break;
        }
    }
}