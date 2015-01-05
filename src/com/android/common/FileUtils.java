package com.android.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
	 * delete file recursion
	 * @param file
	 */
	public static void deleteFileRecur(File file){
		if(file != null && file.exists()){
			if(file.isDirectory()){
				File[] childFiles = file.listFiles();
				if(childFiles != null && childFiles.length > 0){
					for (File tmp : childFiles) {
						deleteFileRecur(tmp);
					}
				}
			}
			file.delete();
		}
	}
	
	/**
	 * copy dirFrom children to dirTo
	 * @param dirFrom
	 * @param dirTo
	 * @throws IOException 
	 */
	public static void copyDir(File dirFrom, File dirTo) throws IOException{
		if(dirFrom == null || dirTo == null){
			throw new IllegalArgumentException("dirFrom or dirTo can not be null");
		}
		
		if(dirFrom.exists() && dirFrom.isDirectory()){
			dirTo.mkdirs();
			
			File[] fromChildren = dirFrom.listFiles();
			if(fromChildren != null && fromChildren.length > 0){
				for (File file : fromChildren) {
					if(file.isFile()){
						copyFile(file, new File(dirTo, file.getName()));
					}else if(file.isDirectory()){
						copyDir(file, new File(dirTo, file.getName()));
					}
				}
			}
		}
	}
	
	/**
	 * copy fileFrom to fileTo
	 * @param fileFrom
	 * @param fileTo
	 * @throws IOException 
	 */
	public static void copyFile(File fileFrom, File fileTo) throws IOException{
		if(fileFrom == null || fileTo == null){
			throw new IllegalArgumentException("fileFrom or fileTo can not be null");
		}
		
		if(fileFrom.isFile() && fileFrom.exists()){
			fileTo.getParentFile().mkdirs();
			fileTo.delete();
			fileTo.createNewFile();
			
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			byte[] buffer = new byte[10 * 1024];
			int len = 0;
			
			try {
				bis = new BufferedInputStream(new FileInputStream(fileFrom));
				bos = new BufferedOutputStream(new FileOutputStream(fileTo));
				
				while((len = bis.read(buffer)) > 0){
					bos.write(buffer, 0, len);
				}
			} finally{
				if(bis != null){
					bis.close();
				}
				if(bos != null){
					bos.close();
				}
			}
		}
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
