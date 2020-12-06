package com.example.superflashlight;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Settings extends PoliceLight implements OnSeekBarChangeListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		mSeekBarPoliceLight.setOnSeekBarChangeListener(this);
		mSeekBarWarningLight.setOnSeekBarChangeListener(this);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		switch (seekBar.getId()) {
		case R.id.seekbar_warning_light:
			mCurrentWarningLightInterval = progress + 100;

			break;
		case R.id.seekbar_police_light:
			mCurrentPoliceLightInterval = progress + 50;
			break;
		default:
			break;
		}

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}



	private boolean shortcutInScreen() {
		Cursor cursor = getContentResolver()
				.query(Uri
						.parse("content://com.cyanogenmod.trebuchet.settings/favorites"),
						null,
						"intent like ?",
						new String[] { "%component=com.example.superflashlight/.MainActivity%" },
						null);

		if (cursor.getCount() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * #Intent;action=android.intent.action.MAIN;category=android.intent.category
	 * .
	 * LAUNCHER;launchFlags=0x10200000;component=cn.eoe.flashlight/.MainActivity
	 * ;end
	 */
	public void onClick_AddShortcut(View view) {
//		if (!shortcutInScreen()) {
//			Intent installShortcut = new Intent(
//					"com.android.launcher.action.INSTALL_SHORTCUT");
//			installShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "超级手电筒");
//			Parcelable icon = Intent.ShortcutIconResource.fromContext(this,
//					R.drawable.icon);
//
//			Intent flashlightIntent = new Intent();
//			flashlightIntent.setClassName("com.example.superflashlight",
//					"com.example.superflashlight.MainActivity");
//			flashlightIntent.setAction(Intent.ACTION_MAIN);
//			flashlightIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//			installShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
//					flashlightIntent);
//
//			sendBroadcast(installShortcut);
//			Toast.makeText(this, "成功添加快捷方式", Toast.LENGTH_LONG).show();
//		} else {
//			Toast.makeText(this, "快捷方式已存在", Toast.LENGTH_LONG)
//					.show();
//		}
        if (hasShortcut(this)){
            Toast.makeText(this, "快捷方式已存在", Toast.LENGTH_LONG);
        }else {
            addShortcut(this, R.drawable.icon);
            Toast.makeText(this, "成功添加快捷方式", Toast.LENGTH_LONG).show();

        }
	}

	public void onClick_RemoveShortcut(View view) {
	    if(hasShortcut(this)){
            delShortcut(this);
            Toast.makeText(this, "成功移除快捷方式", Toast.LENGTH_LONG).show();
        }else{
			Toast.makeText(this, "快捷方式不存在", Toast.LENGTH_LONG).show();
        }
//		if (shortcutInScreen()) {
//			Intent uninstallShortcut = new Intent(
//					"com.android.launcher.action.UNINSTALL_SHORTCUT");
//			uninstallShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "超级手电筒");
//
//			Intent flashlightIntent = new Intent();
//			flashlightIntent.setClassName("com.example.superflashlight",
//					"com.example.superflashlight.MainActivity");
//
//			uninstallShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
//					flashlightIntent);
//
//			flashlightIntent.setAction(Intent.ACTION_MAIN);
//			flashlightIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//
//			sendBroadcast(uninstallShortcut);
//		} else {
//			Toast.makeText(this, "移除快捷方式失败", Toast.LENGTH_LONG).show();
//		}
	}


    /**
     * 添加当前应用的桌面快捷方式
     *
     * @param context
     */
    public static void addShortcut(Context context, int appIcon) {
        Intent shortcut = new Intent(
                "com.android.launcher.action.INSTALL_SHORTCUT");

        Intent shortcutIntent = context.getPackageManager()
                .getLaunchIntentForPackage(context.getPackageName());
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        // 获取当前应用名称
        String title = null;
        try {
            final PackageManager pm = context.getPackageManager();
            title = pm.getApplicationLabel(
                    pm.getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {
        }
        // 快捷方式名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
        // 不允许重复创建（不一定有效）
        shortcut.putExtra("duplicate", false);
        // 快捷方式的图标
        Parcelable iconResource = Intent.ShortcutIconResource.fromContext(context,
                appIcon);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);

        context.sendBroadcast(shortcut);
    }

    /**
     * 删除当前应用的桌面快捷方式
     *
     * @param context
     */
    public static void delShortcut(Context context) {
        Intent shortcut = new Intent(
                "com.android.launcher.action.UNINSTALL_SHORTCUT");

        // 获取当前应用名称
        String title = null;
        try {
            final PackageManager pm = context.getPackageManager();
            title = pm.getApplicationLabel(
                    pm.getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {
        }
        // 快捷方式名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
        Intent shortcutIntent = context.getPackageManager()
                .getLaunchIntentForPackage(context.getPackageName());
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        context.sendBroadcast(shortcut);
    }

    /**
     * 判断当前应用在桌面是否有桌面快捷方式
     *
     * @param context
     */
    public static boolean hasShortcut(Context context) {
        boolean result = false;
        String title = null;
        try {
            final PackageManager pm = context.getPackageManager();
            title = pm.getApplicationLabel(
                    pm.getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {

        }

        final String uriStr;
        if (android.os.Build.VERSION.SDK_INT < 8) {
            uriStr = "content://com.android.launcher.settings/favorites?notify=true";
        } else if (android.os.Build.VERSION.SDK_INT < 19) {
            uriStr = "content://com.android.launcher2.settings/favorites?notify=true";
        } else {
            uriStr = "content://com.android.launcher3.settings/favorites?notify=true";
        }
        final Uri CONTENT_URI = Uri.parse(uriStr);
        final Cursor c = context.getContentResolver().query(CONTENT_URI, null,
                "title=?", new String[]{title}, null);
        if (c != null && c.getCount() > 0) {
            result = true;
        }
        return result;
    }
}
