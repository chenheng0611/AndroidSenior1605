package com.qianfeng.day4_zxing.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.ThumbnailUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.HashMap;
import java.util.Map;

/**
 * 条形码处理工具类
 * Created by xray on 16/9/29.
 */

public class ZXingUtils {

    /**
     * 将文字转换为二维码图片
     * @param content
     * @param width
     * @param height
     * @return
     */
    public static Bitmap createQRCodeBitmap(String content,int width,int height){
        //添加参数
        Map hint = new HashMap();
        hint.put(EncodeHintType.CHARACTER_SET,"utf-8");
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        MultiFormatWriter writer = new MultiFormatWriter();
        //二维码编码
        try {
            BitMatrix matrix =
                    writer.encode(content, BarcodeFormat.QR_CODE, width, height, hint);
            //将矩阵转换为一维数组
            int[] pixels = new int[width * height];
            //外层控制纵向,内层控制横向像素
            for(int y = 0;y < height;y++){
                for(int x = 0;x < width;x++){
                    if(matrix.get(x,y)){
                        pixels[y * width + x] = 0xff000000;//黑像素
                    }else{
                        pixels[y * width + x] = 0xffffffff;//白
                    }
                }
            }
            //将一维数组转换为Bitmap
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels,0,width,0,0,width,height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 在二维码图片中插入logo
     * @param bitmap
     * @param logo
     * @return
     */
    public static Bitmap createBitmapWithLogo(Bitmap bitmap,Bitmap logo){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        logo = ThumbnailUtils.extractThumbnail(logo,width * 1 / 5,height * 1 / 5,ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        Bitmap combine = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(combine);
        canvas.drawBitmap(bitmap,0,0,null);
        canvas.drawBitmap(logo,(width - logo.getWidth()) / 2,(height - logo.getWidth()) / 2,null );
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        if(combine.isRecycled()){
            combine.recycle();
        }
        return combine;
    }

    /**
     * 将二维码转换为文字
     * @param bitmap
     * @return
     */
    public static String readContentFromQRCode(Bitmap bitmap){

//        Bitmap —— int[] —— RGBLuminanceSource
//        —— HybridBinarizer —— BinaryBitmap
        //将Bitmap的内容转换到一维数组
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels,0,width,0,0,width,height);

        RGBLuminanceSource source = new RGBLuminanceSource(width, height, pixels);
        HybridBinarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

        MultiFormatReader reader = new MultiFormatReader();
        //解码二维码图片
        Map hint = new HashMap();
        hint.put(EncodeHintType.CHARACTER_SET,"utf-8");
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        try {
            Result decode = reader.decode(binaryBitmap, hint);
            return decode.getText();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
