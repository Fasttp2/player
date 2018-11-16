package com.example.jaejun.cs409_player;

import android.util.Log;
import android.view.Surface;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

import java.util.ArrayList;

/**
 * Created by jaejun on 2018-11-12.
 */

public class ExperimentComponentListener extends Player.DefaultEventListener
        implements VideoRendererEventListener {

    private boolean isStart;
    private String results = "";
    private BufferingCallback endCallback = null;

    public ExperimentComponentListener(BufferingCallback callback){
        this.endCallback = callback;
    }

    public void refresh(){
        this.isStart = false;
        this.results = String.format("initTime:%d",Utils.currentTimeNanos());
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        super.onPlayerStateChanged(playWhenReady, playbackState);

        if (!isStart && playbackState == Player.STATE_READY){
            this.isStart = true;
            this.results = String.format("%s\nstartTime:%d",this.results, Utils.currentTimeNanos());
        }
        else{
            switch (playbackState){
                case Player.STATE_BUFFERING:
                    if (isStart) {
                        this.results = String.format("%s\nbufferingStartTime:%d", this.results, Utils.currentTimeNanos());
                    }
                    break;
                case Player.STATE_READY:
                    this.results = String.format("%s\nbufferingEndTime:%d", this.results, Utils.currentTimeNanos());
                    break;
                case Player.STATE_ENDED:
                    this.endCallback.callback(this.results);
                    break;
            }
        }
    }

    @Override
    public void onVideoEnabled(DecoderCounters counters) {
    }

    @Override
    public void onVideoDecoderInitialized(String decoderName, long initializedTimestampMs, long initializationDurationMs) {
    }

    @Override
    public void onVideoInputFormatChanged(Format format) {
    }

    @Override
    public void onDroppedFrames(int count, long elapsedMs) {
    }

    @Override
    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
    }

    @Override
    public void onRenderedFirstFrame(Surface surface) {
    }

    @Override
    public void onVideoDisabled(DecoderCounters counters) {
    }
}

