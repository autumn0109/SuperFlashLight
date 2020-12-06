package com.example.superflashlight;

import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ly.lib.FlashLightLib;


public class FlashLight extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        mImageViewFlashlight.setTag(false);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        ViewGroup.LayoutParams layoutParams =  mImageViewFlashlightController.getLayoutParams();
//        System.out.println(layoutParams.height);
        layoutParams.height = point.y * 3 / 4;
        layoutParams.width = point.x / 3;
        mImageViewFlashlightController.setLayoutParams(layoutParams);
    }

    Boolean openOrClose = false;
    public void onClick_Flashlight(View view){
       // System.out.println(mImageViewFlashLight.getTag());
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            Toast.makeText(this, "当前设备没有闪光灯", Toast.LENGTH_SHORT).show();
            return;
        }
        if (openOrClose == false){
            FlashLightLib.changeFlashLight(true, this);
            openOrClose = true;
        }else{
            FlashLightLib.changeFlashLight(false,this);
            openOrClose = false;

        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        FlashLightLib.changeFlashLight(false,this);//关闭闪光灯
        openOrClose = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        FlashLightLib.changeFlashLight(false,this);//关闭闪光灯
        openOrClose = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FlashLightLib.changeFlashLight(false,this);//关闭闪光灯
        openOrClose = false;
    }
}
