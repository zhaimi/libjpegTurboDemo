package com.sina.compress;

import android.graphics.Bitmap;

import java.io.File;

public class TurboCompressor {

    static {
        System.loadLibrary("compress");
    }


    public static File compressBitmap(Bitmap bmp, int quality, String outfile){
        nativeCompress(bmp, quality, outfile,true);

        return new File(outfile);
    }

    static native int nativeCompress(Bitmap bitmap, int quality, String outfile, boolean optimize);

}
