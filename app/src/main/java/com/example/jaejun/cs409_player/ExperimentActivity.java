package com.example.jaejun.cs409_player;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by jaejun on 2018-11-12.
 */

public class ExperimentActivity extends AppCompatActivity {

    private final String SERVER_URL = "ws://143.248.188.172:19285";
    private ExperimentServerConnection mConnection = null;
    private Handler mHandler = null;
    private int segmentType = 0, rttType = 0, bandwidthType = 0;
    private TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                String message = (String)msg.obj;
                Utils.writeExperimentData("\n" + message);
                int segmentType = Integer.parseInt(message.split("/")[0]);

                Intent intent = new Intent(getApplicationContext(), ExperimentPlayActivity.class);
                intent.putExtra("segmentType", segmentType);
                startActivityForResult(intent, 5);
            }
        };

        textView = (TextView) findViewById(R.id.experimentStateText);

        mConnection = new ExperimentServerConnection(SERVER_URL, mHandler);
        mConnection.connect();
        mConnection.sendMessage(segmentType, rttType, bandwidthType);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5 && resultCode == 5) {
            textView.setText(String.format("%s/%s/%s DONE", segmentType, bandwidthType, rttType));

            bandwidthType++;
            if (bandwidthType > 0) {
                rttType++;
                bandwidthType = 0;
            }
            if (rttType > 3) {
                segmentType++;
                rttType = 0;
            }
            if (!(segmentType > 0)) {
                mConnection.sendMessage(segmentType, rttType, bandwidthType);
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (mConnection != null) {
            mConnection.disconnect();
            mConnection = null;
        }
        super.onDestroy();
    }
}
