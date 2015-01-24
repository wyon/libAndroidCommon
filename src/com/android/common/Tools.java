package com.android.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;

public final class Tools {
	private Tools() {
	}

	/**
	 * uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"
	 * <p>
	 * 判断是否有网络连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"
	 * <p>
	 * 判断WIFI网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"
	 * <p>
	 * 判断手机网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				return mMobileNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public static boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}

		return false;
	}

	public static boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}

	/**
	 * Get boolean property from Bundle.
	 * 
	 * @param bundle
	 *            bundle store property
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBooleanProperty(Bundle bundle, String name,
			boolean defaultValue) {
		if (bundle == null) {
			return defaultValue;
		}
		// name = name.toLowerCase(Locale.getDefault());
		Boolean p;
		try {
			p = (Boolean) bundle.get(name);
		} catch (ClassCastException e) {
			String s = bundle.get(name).toString();
			if ("true".equals(s)) {
				p = true;
			} else {
				p = false;
			}
		}
		if (p == null) {
			return defaultValue;
		}
		return p.booleanValue();
	}

	/**
	 * Get int property from Bundle.
	 * 
	 * @param bundle
	 *            bundle store property
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static int getIntegerProperty(Bundle bundle, String name,
			int defaultValue) {
		if (bundle == null) {
			return defaultValue;
		}
		// name = name.toLowerCase(Locale.getDefault());
		Integer p;
		try {
			p = (Integer) bundle.get(name);
		} catch (ClassCastException e) {
			p = Integer.parseInt(bundle.get(name).toString());
		}
		if (p == null) {
			return defaultValue;
		}
		return p.intValue();
	}

	/**
	 * Get string property from Bundle.
	 * 
	 * @param bundle
	 *            bundle store property
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static String getStringProperty(Bundle bundle, String name,
			String defaultValue) {
		if (bundle == null) {
			return defaultValue;
		}
		// name = name.toLowerCase(Locale.getDefault());
		String p = bundle.getString(name);
		if (p == null) {
			return defaultValue;
		}
		return p;
	}

	/**
	 * Get double property from Bundle.
	 * 
	 * @param bundle
	 *            bundle store property
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static double getDoubleProperty(Bundle bundle, String name,
			double defaultValue) {
		if (bundle == null) {
			return defaultValue;
		}
		// name = name.toLowerCase(Locale.getDefault());
		Double p;
		try {
			p = (Double) bundle.get(name);
		} catch (ClassCastException e) {
			p = Double.parseDouble(bundle.get(name).toString());
		}
		if (p == null) {
			return defaultValue;
		}
		return p.doubleValue();
	}

	/**
	 * 返回主线程，UI线程
	 * 
	 * @return
	 */
	public static Thread getMainThread() {
		return Looper.getMainLooper().getThread();
	}
}
