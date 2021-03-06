package com.smapley.guess.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by smapley on 15/10/22.
 */
public class JFileKit {
    //录音输出文件
    public static String AUDIO_AMR_FILEPATH = null;

    /**
     * 根据传入的uniqueName获取硬盘缓存的路径地址
     */
    public static String getDiskCacheDir(Context context) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || Environment.isExternalStorageEmulated()) {
            File file = context.getExternalCacheDir();
            cachePath = file.getAbsolutePath();
        } else {
            cachePath = context.getCacheDir().getAbsolutePath();
        }

        return cachePath;
    }

    /**
     * 判断是否有外部存储设备sdcard
     */
    public static boolean isSdcardExit() {
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

    


    /**
     * 获取文件大小
     *
     * @param path,文件的绝对路径
     */
    public static long getFileSize(String path) {
        File mFile = new File(path);
        if (!mFile.exists())
            return -1;
        return mFile.length();
    }
}
