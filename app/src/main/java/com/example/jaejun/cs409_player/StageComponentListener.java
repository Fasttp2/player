package com.example.jaejun.cs409_player;

import android.view.Surface;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

/**
 * Created by jaejun on 2018-10-04.
 */

public class StageComponentListener extends Player.DefaultEventListener
        implements VideoRendererEventListener {

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        super.onPlayerStateChanged(playWhenReady, playbackState);
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
