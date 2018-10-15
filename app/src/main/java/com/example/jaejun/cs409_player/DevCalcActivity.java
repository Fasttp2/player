package com.example.jaejun.cs409_player;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by jaejun on 2018-10-11.
 */

public class DevCalcActivity extends AppCompatActivity{

    Intent devPlayIntent = null;
    EditText inputText = null;
    TextView resultTextView = null;
    String results = null;
    int resultCount = 0;
    double avgStartupTime, avgSquare, maxStartupTime, minStartupTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        devPlayIntent = new Intent(this, DevPlayActivity.class);
        devPlayIntent.putExtra("mode", "calc");

        inputText = (EditText)findViewById(R.id.inputIterationNumber);
        inputText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    startCalc(Integer.parseInt(inputText.getText().toString()));
                }
                return false;
            }
        });
        resultTextView = (TextView)findViewById(R.id.result);
    }

    public void startCalc(int iteration){
        for (int i=0; i<iteration; i++){
            startActivityForResult(devPlayIntent, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        long initTime = data.getLongExtra("initTime", -1);
        long startTime = data.getLongExtra("startTime", -1);

        updateResult(startTime - initTime);
    }

    public void updateResult(long time){
        double startupTime = time / Math.pow(10, 9);

        if (results == null) {
            results = String.valueOf(startupTime);
            avgStartupTime = startupTime;
            avgSquare = Math.pow(startupTime, 2);
            maxStartupTime = startupTime;
            minStartupTime = startupTime;
        }
        else {
            results += '\n' + String.valueOf(startupTime);
            avgStartupTime = avgStartupTime / (resultCount + 1) * resultCount + startupTime / (resultCount + 1);
            avgSquare = avgSquare / (resultCount + 1) * resultCount + Math.pow(startupTime, 2) / (resultCount + 1);
            maxStartupTime = Math.max(maxStartupTime, startupTime);
            minStartupTime = Math.min(minStartupTime, startupTime);
        }
        resultCount++;

        Double var = avgSquare - Math.pow(avgStartupTime, 2);
        resultTextView.setText(
                "avg : " + String.valueOf(avgStartupTime) + '\n'
                        + "var : " + String.valueOf(var) + "\n"
                        + "min : " + String.valueOf(minStartupTime) + '\n'
                        + "max : " + String.valueOf(maxStartupTime) + '\n'
                        + "count : " + String.valueOf(resultCount) + "\n\n"
                        + results
        );
    }

    public void click(View view) {
        switch(view.getId()){
            case R.id.start:
                startCalc(Integer.parseInt(inputText.getText().toString()));
                break;
            default:
                break;
        }
    }
}
