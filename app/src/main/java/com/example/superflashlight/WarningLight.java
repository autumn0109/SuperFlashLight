package com.example.superflashlight;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.superflashlight.FlashLight;
import com.example.superflashlight.R;

public class WarningLight extends FlashLight {
	protected boolean mWarningLightFlicker;  //  true: 闪烁   false：停止闪烁
	protected boolean mWarningLightState;  //  true:  on-off   false: off-on

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mWarningLightFlicker = true;

	}

	class WarningLightThread extends Thread
	{
		public void run()
		{
			mWarningLightFlicker = true;
			while(mWarningLightFlicker)
			{
				try
				{
					Thread.sleep(300);
					mWarningHandler.sendEmptyMessage(0);
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
	private Handler mWarningHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			if(mWarningLightState)
			{
				mImageViewWarningLight1.setImageResource(R.drawable.warning_light_on);
				mImageViewWarningLight2.setImageResource(R.drawable.warning_light_off);
				mWarningLightState = false;
			}
			else
			{
				mImageViewWarningLight1.setImageResource(R.drawable.warning_light_off);
				mImageViewWarningLight2.setImageResource(R.drawable.warning_light_on);
				mWarningLightState = true;
			}
		}
	};
}
