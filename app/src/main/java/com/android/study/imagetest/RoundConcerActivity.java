package com.android.study.imagetest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.android.study.R;

/**
 * 圆角图片处理
 * Created by user on 2015/10/27.
 */
public class RoundConcerActivity extends Activity{
    /**
     * 图片布局
     */
    private ImageView roundView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roundconcer);
        roundView = (ImageView) findViewById(R.id.image);
        //获取资源文件中的图片，为了canvas处理资源图片不报错，使用copy方法
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.dameiv).copy(Bitmap.Config.ARGB_8888, true);
        roundView.setImageBitmap(getRoundBitmap(bitmap,50));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private Bitmap getRoundBitmap(Bitmap bitmap,int roundPixels){
        //创建一个和原始图片一样大小的位图
        Bitmap roundConcerImage = Bitmap.createBitmap(new DisplayMetrics(),bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //创建带有位图roundConcerImage的画布
        Canvas canvas = new Canvas(roundConcerImage);
        //画笔
        Paint paint = new Paint();
        //创建一个和原始位图一样大小的矩形
        Rect rect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        RectF rectF = new RectF(rect);
        // 去锯齿
        paint.setAntiAlias(true);
        //画一个和原始图片一样大小的圆角矩形
        canvas.drawRoundRect(rectF, roundPixels, roundPixels, paint);
        //设置相交模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //把图片画到矩形
        canvas.drawBitmap(bitmap,null,rectF,paint);
        return roundConcerImage;
    }
}
