package com.rtmp.rtmp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class MainActivity extends Activity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private String path;
    //private HashMap<String, String> options;
    private VideoView mVideoView;
    private SDSimpleTitleView mTitle;
    private TextView tv;
    HeartLayout hl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_main);
        mTitle= (SDSimpleTitleView) findViewById(R.id.main_title);
        mVideoView = (VideoView) findViewById(R.id.vitamio_videoView);
        tv= (TextView) findViewById(R.id.member_send_good);
        hl= (HeartLayout) findViewById(R.id.heart_layout);
        tv.setOnClickListener(this);
        init();
        initTitle();
    }
    private void init(){
        path = "rtmp://10.10.10.108/hls/yourname";
      /*options = new HashMap<>();
      options.put("rtmp_playpath", "");
      options.put("rtmp_swfurl", "");
      options.put("rtmp_live", "1");
      options.put("rtmp_pageurl", "");*/
        mVideoView.setVideoPath(path);
        //mVideoView.setVideoURI(Uri.parse(path), options);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setVideoQuality(16);
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
    }
    private void initTitle(){
        mTitle.setTitle("我的直播");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.member_send_good:
                hl.addFavor();
                break;
        }

    }
}
