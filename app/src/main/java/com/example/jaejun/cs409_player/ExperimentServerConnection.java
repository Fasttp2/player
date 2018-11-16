package com.example.jaejun.cs409_player;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * Created by jaejun on 2018-11-15.
 */

public class ExperimentServerConnection {

    private String SERVER_URL;
    private OkHttpClient mClient;
    private WebSocket mWebSocket;
    private Handler mHandler;

    int segmentType, rttType, bandwidthType;

    public ExperimentServerConnection(String url, Handler handler) {
        SERVER_URL = url;

        mClient = new OkHttpClient.Builder()
                .readTimeout(3,  TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        mHandler = handler;
    }

    public void connect(){
        Request request = new Request.Builder()
                .url(SERVER_URL)
                .build();

        mWebSocket = mClient.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                super.onFailure(webSocket, t, response);
                disconnect();
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);

                if (text.equals("throttle set up")) {
                    Message msg = Message.obtain();
                    msg.obj = String.format("%d/%d/%d", segmentType, rttType, bandwidthType);
                    mHandler.sendMessage(msg);
                }
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
            }
        });
    }

    public void disconnect(){
        mWebSocket.cancel();
    }

    public void sendMessage(int segmentType, int rttType, int bandwidthType) {
        this.segmentType = segmentType;
        this.rttType = rttType;
        this.bandwidthType = bandwidthType;

        String message = String.format("req-b%dR%d", bandwidthType, rttType);
        mWebSocket.send(message);
    }
}
