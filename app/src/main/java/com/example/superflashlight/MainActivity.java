package com.example.superflashlight;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Settings {
    private Thread thread = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onClick_Controller(View view){
        hideAllUI();
        if (mCurrentUIType != UIType.UI_TYPE_MAIN){
            mUIMain.setVisibility(View.VISIBLE);
            mCurrentUIType = UIType.UI_TYPE_MAIN;
            screenBrightness(mDefaultScreenBrightness/255f);
            if (thread != null){
                try {
                    thread.interrupt();
                    thread = null;
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            switch (mLastUIType){
                case UI_TYPE_FLASHLIGHT:
                    mUIFlashlight.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
                    break;
                case UI_TYPE_WARNINGLIGHT:
                    mUIWarningLight.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_WARNINGLIGHT;
                    break;
                case UI_TYPE_MORSE:
                    mUIMorse.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_MORSE;
                    break;
                case UI_TYPE_BLUB:
                    mUIBulb.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_BLUB;
                    break;
                case UI_TYPE_POLICE:
                    mUIPoliceLight.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_POLICE;
                    new PoliceThread().start();
                    break;
                case UI_TYPE_SETTINGS:
                    mUISettings.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_SETTINGS;
                    break;
                default:
                    break;
            }
        }
    }

    public void onClick_ToFlashlight(View view){
        hideAllUI();
        mUIFlashlight.setVisibility(View.VISIBLE);
        mLastUIType = UIType.UI_TYPE_FLASHLIGHT;
        mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;

    }
    public void onClick_ToWarningLight(View view){
        hideAllUI();
        mUIWarningLight.setVisibility(View.VISIBLE);
        mLastUIType = UIType.UI_TYPE_WARNINGLIGHT;
        mCurrentUIType = UIType.UI_TYPE_WARNINGLIGHT;
        screenBrightness(0.1f);
        thread = new WarningLightThread();
        thread.start();

    }

    public void onClick_ToMorse(View view){
        hideAllUI();
        mUIMorse.setVisibility(View.VISIBLE);
        mLastUIType = UIType.UI_TYPE_MORSE;
        mCurrentUIType = UIType.UI_TYPE_MORSE;

    }

    public void onClick_ToBulb(View view){
        hideAllUI();
        mUIBulb.setVisibility(View.VISIBLE);
        mLastUIType = UIType.UI_TYPE_BLUB;
        mCurrentUIType = UIType.UI_TYPE_BLUB;
        mHideTextViewBulb.hide();
        mHideTextViewBulb.setTextColor(Color.BLACK);

    }

    public void onClick_ToColor(View view){
        hideAllUI();
        mUIColorLight.setVisibility(View.VISIBLE);
        mLastUIType = UIType.UI_TYPE_COLOR;
        mCurrentUIType = UIType.UI_TYPE_COLOR;
        mHideTextViewColorLight.hide();
        mHideTextViewColorLight.setTextColor(Color.rgb(
                255 - Color.red(mCurrentColorLight),
                255 - Color.green(mCurrentColorLight),
                255 - Color.blue(mCurrentColorLight)));

    }

    public void onClick_ToPolice(View view) {
        hideAllUI();
        mUIPoliceLight.setVisibility(View.VISIBLE);
        screenBrightness(1f);
        mLastUIType = UIType.UI_TYPE_POLICE;
        mCurrentUIType = mLastUIType;
        mHideTextviewPoliceLight.hide();
        thread = new PoliceThread();
        thread.start();
    }

    public void onClick_ToSettings(View view) {
        hideAllUI();
        mUISettings.setVisibility(View.VISIBLE);
        mLastUIType = UIType.UI_TYPE_SETTINGS;
        mCurrentUIType = mLastUIType;

    }



}
