package com.android.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.AssetManager;

public final class FileUtils {

	private FileUtils() {
	}

	/**
	 * copy asset dir to /data/data/.../files/
	 * 
	 * @param context
	 * @param dirname
	 *            dir name. can not be null, "" is assets dir
	 * @throws IOException
	 */
	public static void copyAssetDirToFiles(Context context, String dirname)
			throws IOException {
		File dir = new File(context.getFilesDir() + "/" + dirname);
		dir.mkdir();

		AssetManager assetManager = context.getAssets();
		String[] children = assetManager.list(dirname);
		for (String child : children) {
			child = dirname + "/" + child;

			String[] grandChildren = assetManager.list(child);
			if (0 == grandChildren.length) {
				copyAssetFileToFiles(context, child);
			} else {
				copyAssetDirToFiles(context, child);
			}
		}
	}

	/**
	 * copy asset file to /data/data/.../files/filename
	 * 
	 * @param context
	 * @param filename
	 *            filename the releative file path with assets
	 * @throws IOException
	 */
	public static void copyAssetFileToFiles(Context context, String filename)
			throws IOException {
		InputStream is = null;
		OutputStream ops = null;

		File nf = new File(context.getFilesDir() + "/" + filename);
		nf.createNewFile();

		try {
			is = context.getAssets().open(filename);
			ops = new FileOutputStream(nf);

			int l = 0;
			byte[] buffer = new byte[10 * 1024];

			while ((l = is.read(buffer)) > 0) {
				ops.write(buffer, 0, l);
			}
		} finally {
			if (is != null) {
				is.close();
			}
			if (ops != null) {
				ops.close();
			}
		}
	}

}
