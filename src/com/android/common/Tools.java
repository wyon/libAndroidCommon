package com.android.common;

import android.os.Bundle;
import android.os.Environment;

public final class Tools {
	private Tools() {
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
}
