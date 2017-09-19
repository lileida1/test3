package cn.nodemedia.liveencoder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.Toast;
import cn.nodemedia.LiveEncoder;
import cn.nodemedia.LiveEncoder.LiveEncoderDelegate;

public class PushActivity extends Activity implements OnClickListener, LiveEncoderDelegate {
	private SurfaceView sv;
	private Button micBtn, swtBtn, videoBtn, flashBtn, menuBtn;
	private boolean isStarting = false;
	private RotateAnimation rotateAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_push_video);
		sv = (SurfaceView) findViewById(R.id.cameraView);
		micBtn = (Button) findViewById(R.id.button_mic);
		swtBtn = (Button) findViewById(R.id.button_sw);
		videoBtn = (Button) findViewById(R.id.button_video);
		flashBtn = (Button) findViewById(R.id.button_flash);
		menuBtn = (Button) findViewById(R.id.button_menu);

		micBtn.setOnClickListener(this);
		swtBtn.setOnClickListener(this);
		videoBtn.setOnClickListener(this);
		flashBtn.setOnClickListener(this);
		menuBtn.setOnClickListener(this);

		LiveEncoder.init(this);
		LiveEncoder.setDelegate(this);
		LiveEncoder.setAudioParam(32 * 1000);
		LiveEncoder.setVideoParam(640, 360, 15, 300 * 1000);                            
		LiveEncoder.startPreview(sv);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		LiveEncoder.release();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.button_mic:

			break;
		case R.id.button_sw:
			LiveEncoder.switchCamera();
			break;
		case R.id.button_video:
			if (isStarting) {
				LiveEncoder.stopPublish();
			} else {
				//修改推流地址
				 //LiveEncoder.startPublish("rtmp://42.99.17.13/live/test");
				LiveEncoder.startPublish("rtmp://10.10.10.108/hls/yourname");

			    
			}
			break;
		case R.id.button_flash:

			break;
		case R.id.button_menu:

			break;
		default:
			break;
		}
	}

	@Override
	public void onEventCallback(int event, String msg) {
		handler.sendEmptyMessage(event);
	}

	private Handler handler = new Handler() {
		// 回调处理
		@SuppressLint("NewApi")
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 2000:
				Toast.makeText(PushActivity.this, "正在发布视频", Toast.LENGTH_SHORT).show();
				break;
			case 2001:
				Toast.makeText(PushActivity.this, "视频发布成功", Toast.LENGTH_SHORT).show();
				videoBtn.setBackgroundResource(R.drawable.ic_video_start);
				isStarting = true;
				break;
			case 2002:
				Toast.makeText(PushActivity.this, "视频发布失败", Toast.LENGTH_SHORT).show();
				break;
			case 2004:
				Toast.makeText(PushActivity.this, "视频发布结束", Toast.LENGTH_SHORT).show();
				videoBtn.setBackgroundResource(R.drawable.ic_video_stop);
				isStarting = false;
				break;
			case 2005:
				Toast.makeText(PushActivity.this, "网络异常,发布中断", Toast.LENGTH_SHORT).show();
				break;
			case 2006:
				// Toast.makeText(CameraActivity.this, "流名称重复，无法发布",
				// Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	};

	@Override
	public void onSensorOrientationChange(int from_orientation, int to_rientation) {

		rotateAnimation = new RotateAnimation(from_orientation, to_rientation, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnimation.setDuration(500);
		rotateAnimation.setFillAfter(true);

		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				micBtn.startAnimation(rotateAnimation);
				swtBtn.startAnimation(rotateAnimation);
				videoBtn.startAnimation(rotateAnimation);
				flashBtn.startAnimation(rotateAnimation);
				menuBtn.startAnimation(rotateAnimation);
			}
		});
	}

}
