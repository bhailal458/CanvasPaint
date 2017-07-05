package com.example.sparken02.canvaspaint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View  {
    private int PICK_IMAGE_REQUEST = 1;
    Paint paint = new Paint();
    Bitmap bitmap;
    Context context;
    private static String TAG = "Clicked";

    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public DrawView(Context context) {
        super(context);            
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
        invalidate();
    }

    public Bitmap getSquareBitmap(Bitmap bitmap){
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRect(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);


        return output;
    }
    public Bitmap getRectangleBitmap(Bitmap bitmap){
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
       //////
        final Rect rect = new Rect(0, 50, bitmap.getWidth(), bitmap.getHeight()-50);
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRect(rectF, paint);


        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);


        return output;
    }
    public Bitmap getRoundRectangleBitmap(Bitmap bitmap){
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        //////
        final Rect rect = new Rect(0, 50, bitmap.getWidth(), bitmap.getHeight()-50);
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF,50,50,paint);


        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);


        return output;
    }
    public Bitmap getCircleBitmap(Bitmap bitmap){
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);


        return output;
    }
    public Bitmap getOvalBitmap(Bitmap bitmap){
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 50, bitmap.getWidth(), bitmap.getHeight()-50);
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);


        return output;
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);

        Rect rectchoose = new Rect(100,100,620,620);

        //rectangle choose image
        canvas.drawRect(rectchoose,paint);
        paint.setTextSize(50);
        paint.setColor(Color.RED);
        canvas.drawText("Choose Image..",200,380,paint);


        //set Image in Rectangle
        if (bitmap != null) {
            canvas.drawBitmap(bitmap,null,rectchoose,paint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public Bitmap setframeOne(Bitmap bitmap1, Bitmap bitmap2) {
         Bitmap bmOverlay = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight(), bitmap1.getConfig());
            Canvas canvas = new Canvas(bmOverlay);
            canvas.drawBitmap(bitmap1, new Matrix(), null);
            canvas.drawBitmap(bitmap2, new Matrix(), null);
            return bmOverlay;
        }




}

