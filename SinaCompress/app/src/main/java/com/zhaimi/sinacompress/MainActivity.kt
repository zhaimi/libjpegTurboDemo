package com.zhaimi.sinacompress

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Button
import com.sina.compress.TurboCompressor
import com.zhaimi.bitmapcompress.Util
import java.io.*

class MainActivity : AppCompatActivity() {

    private lateinit var jpegBtn: Button
    private lateinit var commonBtn: Button
    private val quality = 100
    private val normalPath = Util.getPath() + quality+"_normal.jpg"
    private val jpegPath = Util.getPath() + quality+"j_pegTurbo.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jpegBtn = findViewById(R.id.lib_button)
        commonBtn = findViewById(R.id.common_button)

        Util.checkPermission(this)

        val bitmap = create(this)
        Util.getPath()

        jpegBtn.setOnClickListener {
            TurboCompressor.compressBitmap(bitmap,  quality,jpegPath)
            val result = BitmapFactory.decodeFile(jpegPath)
            Log.e("sinaCompress","jpeg=="+result.config.toString()+ "==="+getFileSize(File(jpegPath)))
        }

        commonBtn.setOnClickListener {
            saveImage(bitmap, quality, normalPath)
            val result = BitmapFactory.decodeFile(normalPath)
            Log.e("sinaCompress","normal=="+result.config.toString()+ "==="+getFileSize(File(normalPath)))
        }

    }

    private fun saveImage(bitmap: Bitmap,quality: Int ,path: String) {
        val saveFile = File(path)
        var bos: BufferedOutputStream? = null
        try {
            bos = BufferedOutputStream(FileOutputStream(saveFile))
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos)
            bos.flush()
        } catch (e: IOException) {

        } finally {
            try {
                bos?.close()
            } catch (e: IOException) {

            }
        }
    }


    private fun create(context: Context):Bitmap {
        return BitmapFactory.decodeResource(context.resources, R.drawable.des).copy(Bitmap.Config.ARGB_8888, true)

    }

    private fun getFileSize(file: File): Long {
        var size: Long = 0
        if (file.exists()) {
            var fis: FileInputStream? = null
            try {
                fis = FileInputStream(file)
                size = fis.available().toLong()/1000

            } catch (e: IOException) {
                e.printStackTrace()
            }

        } else {
            Log.e("sinaCompress", "文件不存在!")
        }
        return size
    }

}
