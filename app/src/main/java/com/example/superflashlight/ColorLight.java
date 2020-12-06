package com.example.superflashlight;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.superflashlight.widget.HideTextView;

public class ColorLight extends Bulb  implements ColorPickerDialog.OnColorChangedListener{
	protected int mCurrentColorLight = Color.RED;
	protected HideTextView mHideTextViewColorLight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHideTextViewColorLight = (HideTextView)findViewById(R.id.textview_hide_color_light);
	}

	@Override
	public void colorChanged(int color) {
		mUIColorLight.setBackgroundColor(color);
		mCurrentColorLight = color;

	}

	public void onClick_ShowColorPicker(View view)
	{
        Toast.makeText(this,"手机版本问题，无法正常运行",Toast.LENGTH_SHORT).show();
//        System.out.println("ceshi======================================================");
//		new ColorPickerDialog(this, this, Color.RED).show();
	}

}
