package com.rtmp.rtmp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SDSimpleTitleView extends FrameLayout implements View.OnClickListener
{

	public TextView mTxtLeft;
	public TextView mTxtRight;
	public TextView mTxtTitle;
	public TextView mTxtLeftClose;
	private TextView mTxtTitBot;
	private TextView mTxtTitTag;

	private LinearLayout mLinLeft = null;
	private LinearLayout mLinRight = null;
	private LinearLayout mLinLeftClose = null;
	private LinearLayout mLinTitle = null;

	private ImageView mImgLeft = null;
	private ImageView mImgRight = null;
	private ImageView mImgTitle = null;

	private OnLeftButtonClickListener mOnLeftButtonClickListener;
	private OnRightButtonClickListener mOnRightButtonClickListener;
	private OnLeftCloseButtonClickListener mOnLeftCloseButtonClickListener;
	private OnTitleButtonClickListener mOnTitleButtonClickListener;

	public void setLeftButton(String text, int imageId, OnLeftButtonClickListener listener, Integer backgroundImgId)
	{
		mLinLeft.setVisibility(View.VISIBLE);
		mTxtLeft.setVisibility(View.VISIBLE);
		mImgLeft.setVisibility(View.VISIBLE);
		mTxtLeft.setText(text);
		mImgLeft.setImageResource(imageId);
		mOnLeftButtonClickListener = listener;
		if (backgroundImgId != null)
		{
			mLinLeft.setBackgroundResource(backgroundImgId);
		}
	}

	public void setLeftCloseButton(String text, OnLeftCloseButtonClickListener listener, Integer backgroundImgId)
	{
		mLinLeftClose.setVisibility(View.VISIBLE);
		mTxtLeftClose.setText(text);
		mOnLeftCloseButtonClickListener = listener;
		if (backgroundImgId != null)
		{
			mLinLeftClose.setBackgroundResource(backgroundImgId);
		}
	}

	public void setLeftButtonText(String text, OnLeftButtonClickListener listener, Integer textBackgroundImgId, Integer backgroundImgId)
	{
		mLinLeft.setVisibility(View.VISIBLE);
		mTxtLeft.setVisibility(View.VISIBLE);
		mImgLeft.setVisibility(View.GONE);
		mTxtLeft.setText(text);
		mOnLeftButtonClickListener = listener;
		if (textBackgroundImgId != null)
		{
			mTxtLeft.setBackgroundResource(textBackgroundImgId);
		}
		if (backgroundImgId != null)
		{
			mLinLeft.setBackgroundResource(backgroundImgId);
		}
	}

	public void setLeftButtonImage(int imageId, OnLeftButtonClickListener listener, Integer backgroundImgId)
	{
		mLinLeft.setVisibility(View.VISIBLE);
		mTxtLeft.setVisibility(View.GONE);
		mImgLeft.setVisibility(View.VISIBLE);
		mImgLeft.setImageResource(imageId);
		mOnLeftButtonClickListener = listener;
		if (backgroundImgId != null)
		{
			mLinLeft.setBackgroundResource(backgroundImgId);
		}
	}

	public void removeLeftButton()
	{
		mLinLeft.setVisibility(View.GONE);
		mOnLeftButtonClickListener = null;
	}

	public void setRightButton(String text, int imageId, OnRightButtonClickListener listener, Integer backgroundImgId)
	{
		mLinRight.setVisibility(View.VISIBLE);
		mTxtRight.setVisibility(View.VISIBLE);
		mImgRight.setVisibility(View.VISIBLE);
		mTxtRight.setText(text);
		mTxtTitTag.setVisibility(View.GONE);
		mImgRight.setImageResource(imageId);
		mOnRightButtonClickListener = listener;
		if (backgroundImgId != null)
		{
			mLinRight.setBackgroundResource(backgroundImgId);
		}
	}

	public void setRightButtonText(String text, OnRightButtonClickListener listener, Integer textBackgroundImgId, Integer backgroundImgId)
	{
		mLinRight.setVisibility(View.VISIBLE);
		mTxtRight.setVisibility(View.VISIBLE);
		mImgRight.setVisibility(View.GONE);
		mTxtTitTag.setVisibility(View.GONE);
		mTxtRight.setText(text);
		mOnRightButtonClickListener = listener;
		if (textBackgroundImgId != null)
		{
			mTxtRight.setBackgroundResource(textBackgroundImgId);
		}
		if (backgroundImgId != null)
		{
			mLinRight.setBackgroundResource(backgroundImgId);
		}
	}

	public void setRightButtonImage(int imageId, OnRightButtonClickListener listener, Integer backgroundImgId)
	{
		mLinRight.setVisibility(View.VISIBLE);
		mTxtRight.setVisibility(View.GONE);
		mImgRight.setVisibility(View.VISIBLE);
		mTxtTitTag.setVisibility(View.GONE);
		mImgRight.setImageResource(imageId);
		mOnRightButtonClickListener = listener;
		if (backgroundImgId != null)
		{
			mLinRight.setBackgroundResource(backgroundImgId);
		}
	}

	public void setRightTag(int text){
		mLinRight.setVisibility(View.VISIBLE);
		mTxtRight.setVisibility(View.GONE);
		mImgRight.setVisibility(View.VISIBLE);
		if(text == 0){
			mTxtTitTag.setVisibility(View.GONE);
		}
		else if(text >=99){
			mTxtTitTag.setVisibility(View.VISIBLE);
			mTxtTitTag.setText(Integer.toString(99));
		}
		else{
			mTxtTitTag.setVisibility(View.VISIBLE);
			mTxtTitTag.setText(Integer.toString(text));
		}
	}

	public void removeRightButton()
	{
		mLinRight.setVisibility(View.GONE);
		mOnRightButtonClickListener = null;
	}

	public SDSimpleTitleView(Context context)
	{
		this(context, null);
	}

	public SDSimpleTitleView(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public SDSimpleTitleView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_simple_title, this, true);

		mLinLeft = (LinearLayout) findViewById(R.id.view_simple_title_lin_left);
		mLinRight = (LinearLayout) findViewById(R.id.view_simple_title_lin_right);
		mLinLeftClose = (LinearLayout)findViewById(R.id.view_simple_title_lin_left_ofright);
		mLinTitle = (LinearLayout)findViewById(R.id.view_simple_title_lin_center_title);

		mImgLeft = (ImageView) findViewById(R.id.view_simple_title_img_left);
		mImgRight = (ImageView) findViewById(R.id.view_simple_title_img_right);
		mImgTitle = (ImageView) findViewById(R.id.view_simple_title_center_img);

		mTxtLeft = (TextView) findViewById(R.id.view_simple_title_txt_left);
		mTxtRight = (TextView) findViewById(R.id.view_simple_title_txt_right);
		mTxtLeftClose = (TextView) findViewById(R.id.view_simple_title_txt_img_right);

		mTxtTitle = (TextView) findViewById(R.id.view_simple_title_center_txt_title);

		mTxtTitBot = (TextView) findViewById(R.id.view_simple_title_txt_title_bottom);

		mTxtTitTag = (TextView) findViewById(R.id.view_simple_title_tag);

		mLinLeft.setOnClickListener(this);
		mLinRight.setOnClickListener(this);
		mLinLeftClose.setOnClickListener(this);
		mLinTitle.setOnClickListener(this);

		mLinLeft.setVisibility(View.GONE);
		mLinRight.setVisibility(View.GONE);
		mTxtTitle.setVisibility(View.GONE);
		mLinLeftClose.setVisibility(View.GONE);
		mImgRight.setVisibility(View.GONE);

	}

	public void setTitle(String text)
	{
		mTxtTitle.setVisibility(View.VISIBLE);
		mTxtTitle.setText(text);
	}

	public void setTitleImageButton(String text,OnTitleButtonClickListener listener ,int imageId)
	{
		mTxtTitle.setVisibility(View.VISIBLE);
		mImgTitle.setVisibility(View.VISIBLE);
		mTxtTitle.setText(text);
		mOnTitleButtonClickListener = listener;
		mImgTitle.setImageResource(imageId);
	}

	public void setTitle(int stringID)
	{
		mTxtTitle.setVisibility(View.VISIBLE);
		mTxtTitle.setText(stringID);
	}

	public void setTitleBottom(String text)
	{
		mTxtTitBot.setVisibility(View.VISIBLE);
		mTxtTitBot.setText(text);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.view_simple_title_lin_left:
				if (mOnLeftButtonClickListener != null)
					mOnLeftButtonClickListener.onLeftBtnClick(v);
				break;
			case R.id.view_simple_title_lin_right:
				if (mOnRightButtonClickListener != null)
					mOnRightButtonClickListener.onRightBtnClick(v);
				break;
			case R.id.view_simple_title_lin_left_ofright:
				if( mOnLeftCloseButtonClickListener != null)
					mOnLeftCloseButtonClickListener.onLeftCloseBtnClick(v);
				break;
			case R.id.view_simple_title_lin_center_title:
				if(mOnTitleButtonClickListener != null)
					mOnTitleButtonClickListener.onTitleBtnClick(v);
				break;
		}
	}

	public interface OnLeftButtonClickListener
	{
		public void onLeftBtnClick(View button);
	}

	public interface OnRightButtonClickListener
	{
		public void onRightBtnClick(View button);
	}
	public interface OnLeftCloseButtonClickListener
	{
		public void onLeftCloseBtnClick(View button);
	}
	public interface OnTitleButtonClickListener
	{
		public void onTitleBtnClick(View button);
	}

}
