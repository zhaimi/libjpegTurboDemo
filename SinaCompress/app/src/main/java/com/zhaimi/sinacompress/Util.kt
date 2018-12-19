package com.zhaimi.bitmapcompress

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.*
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.util.Log
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object Util {
    private val DIR_HOME = Environment.getExternalStorageDirectory().toString() + File.separator + "BitmapCompress" + File.separator   //.ssvideo根目录

    fun getPath(): String {
        val file = File(DIR_HOME)
        if (!file.exists() || !file.isDirectory) {
            file.mkdirs()
        }
        return DIR_HOME
    }
    /**
     * 6.0 权限申请
     */
    fun checkPermission(activity: Activity) {
        if (activity.checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity, arrayOf(
                    Manifest.permission
                        .WRITE_EXTERNAL_STORAGE
                ), 100
            )
        }
    }

}