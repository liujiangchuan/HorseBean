package com.ll.services.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

import java.io.InputStream;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FBitmapUtil
{
    /**
     * Compress bitmap in case of out of memory. Any device's OS of which VERSION.SDK_INT < 11 suggested return a smaller image.
     *
     * @param srcPath      Image source path from local.
     * @param targetWidth  The image width after compressed.
     * @param targetHeight The image height after compressed.
     * @return compressed bitmap
     */
    public static Bitmap getImage(String srcPath, int targetWidth, int targetHeight)
    {
        Options newOpts = new Options();
        newOpts.inPurgeable = true;
        newOpts.inInputShareable = true;
        newOpts.inPreferredConfig = Config.RGB_565;
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int inSampleSize = caculateInSampleSize(newOpts, targetWidth, targetHeight);
        if (1 == inSampleSize && FScreenUtil.getScreenWidth() <= 480)
        {
            inSampleSize = 2;
        }
        newOpts.inSampleSize = inSampleSize;
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return bitmap;
    }

    /**
     * Compress bitmap in case of out of memory. Any device's OS of which VERSION.SDK_INT < 11 suggested return a smaller image.
     *
     * @param srcPath         Image source path from local.
     * @param targetWidth     The image width after compressed.
     * @param targetHeight    The image height after compressed.
     * @param minInSampleSize If the original bitmap size is as big as the target size, the return is NO different from the original.
     *                        Set this value to force to compress, any value <= 1 is treated the same as 1.
     * @return compressed bitmap
     */
    public static Bitmap getImage(String srcPath, int targetWidth, int targetHeight,
                                  int minInSampleSize)
    {
        Options newOpts = new Options();
        newOpts.inPurgeable = true;
        newOpts.inInputShareable = true;
        newOpts.inPreferredConfig = Config.RGB_565;
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int inSampleSize = caculateInSampleSize(newOpts, targetWidth, targetHeight);
        newOpts.inSampleSize = Math.max(inSampleSize, minInSampleSize);
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return bitmap;
    }

    /**
     * Compress bitmap in case of out of memory. Any device's OS of which VERSION.SDK_INT < 11 suggested return a smaller image.
     *
     * @param inputStream
     * @param targetWidth  The image width after compressed.
     * @param targetHeight The image height after compressed.
     * @return compressed bitmap
     */
    public static Bitmap getImage(InputStream inputStream, int targetWidth, int targetHeight)
    {
        Bitmap bitmap = null;
        try
        {
            Options newOpts = new Options();
            newOpts.inPurgeable = true;
            newOpts.inInputShareable = true;
            newOpts.inPreferredConfig = Config.RGB_565;
            newOpts.inJustDecodeBounds = true;
            inputStream.mark(inputStream.available());
            bitmap = BitmapFactory.decodeStream(inputStream, null, newOpts);

            newOpts.inJustDecodeBounds = false;
            int inSampleSize = caculateInSampleSize(newOpts, targetWidth, targetHeight);
            if (1 == inSampleSize && FScreenUtil.getScreenWidth() <= 480)
            {
                inSampleSize = 2;
            }
            newOpts.inSampleSize = inSampleSize;
            inputStream.reset();
            bitmap = BitmapFactory.decodeStream(inputStream, null, newOpts);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * Compress bitmap in case of out of memory. Any device's OS of which VERSION.SDK_INT < 11 suggested return a smaller image.
     *
     * @param inputStream
     * @param targetWidth     The image width after compressed.
     * @param targetHeight    The image height after compressed.
     * @param minInSampleSize If the original bitmap size is as big as the target size, the return is NO different from the original.
     *                        Set this value to force to compress, any value <= 1 is treated the same as 1.
     * @return compressed bitmap
     */
    public static Bitmap getImage(InputStream inputStream, int targetWidth, int targetHeight,
                                  int minInSampleSize)
    {
        Options newOpts = new Options();
        newOpts.inPurgeable = true;
        newOpts.inInputShareable = true;
        newOpts.inPreferredConfig = Config.RGB_565;
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, newOpts);

        newOpts.inJustDecodeBounds = false;
        int inSampleSize = caculateInSampleSize(newOpts, targetWidth, targetHeight);
        newOpts.inSampleSize = Math.max(inSampleSize, minInSampleSize);
        bitmap = BitmapFactory.decodeStream(inputStream, null, newOpts);
        return bitmap;
    }

    /**
     * Caculate inSampleSize.
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static int caculateInSampleSize(Options options, int reqWidth, int reqHeight)
    {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if (0 != reqWidth && 0 != reqHeight)
        {
            inSampleSize = (width / reqWidth + height / reqHeight) >> 1;
        }
        if (inSampleSize < 1)
        {
            inSampleSize = 1;
        }
        return inSampleSize;
    }

    /**
     * convert gray image.
     *
     * @param bitmap color bitmap
     * @return gray bitmap
     */
    public static Bitmap getGreyImage(Bitmap bitmap)
    {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(w, h, Config.RGB_565);
        Canvas c = new Canvas(newBitmap);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bitmap, 0, 0, paint);
        return newBitmap;
    }
}
