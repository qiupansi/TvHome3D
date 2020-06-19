package com.chaozhuo.threedhome.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by fewwind on 18-8-27.
 */

public class ThreeDView extends ImageView {
    Paint mPaint;
    boolean isHasFocus;
    Path mPath;
    Canvas mCanvas;
    int boundColor = 0;
    Line mLine = Line.No;

    public ThreeDView(Context context) {
        this(context, null);
    }

    public ThreeDView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThreeDView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GREEN);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mCanvas = new Canvas();
        setFocusable(true);
        mPath = new Path();
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                isHasFocus = hasFocus;
                invalidate();
            }
        });
//        Logger.i("ThreeDView = 构造方法");
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        Logger.v("ThreeDView = onAttachedToWindow");
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        if (mDst == null) return;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        Matrix matrix = new Matrix();
        float w = getMeasuredWidth();
        float h = getMeasuredHeight();
        int bw = bitmap.getWidth();
        int bh = bitmap.getHeight();
//        matrix.postScale(w / bw, h / bh);
//        Logger.i(w / bw + "onDraw" + w);
        Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, (int) w - 8, (int) h - 8, true);
        handBound(bitmap1);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
                | Paint.FILTER_BITMAP_FLAG)); //设置图形、图片的抗锯齿。可用于线条等。按位或.

        float[] src = {0, 0, 0, h, w, h, w, 0};
        float[] dst = {mDst[0] - 0, mDst[1] - 0, mDst[2] - 0, h - mDst[3], w - mDst[4], h - mDst[5], w - mDst[6], mDst[7]};
        matrix.setPolyToPoly(src, 0, dst, 0, 4);
//        matrix.postRotate(20);
        Bitmap bitmapEmpty = Bitmap.createBitmap((int) w, (int) h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(bitmapEmpty);
        mCanvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
                | Paint.FILTER_BITMAP_FLAG)); //设置图形、图片的抗锯齿。可用于线条等。按位或.
        mPaint.setStrokeWidth(8);
        mCanvas.drawBitmap(bitmap1, 4, 4, mPaint);
        mPaint.setColor(Color.TRANSPARENT);
//        mPaint.setColor(Color.parseColor("#e6004a"));
        mCanvas.drawRect(0, 0, w, h, mPaint);
        if (mLine != Line.No) {
            mPaint.setColor(boundColor);
            if (mLine == Line.Left) {
                mCanvas.drawLine(0, 4, 0, h - 4, mPaint);
            } else if (mLine == Line.Right) {
                mCanvas.drawLine(w, 4, w, h - 4, mPaint);
            }
        }
        mPaint.setColor(Color.RED);
//        canvas.drawBitmap(bitmapEmpty,0,0,mPaint);
        canvas.drawBitmap(bitmapEmpty, matrix, mPaint);
        mPaint.setColor(Color.parseColor("#57abb4"));
        if (isHasFocus) {
            int paintW = 4;
            mPaint.setStrokeWidth(paintW);
            mPaint.setColor(Color.parseColor("#007EFD"));
            paintW = paintW / 2;
            mPath.reset();
            mPath.moveTo(dst[0] + paintW, dst[1] + paintW);
            mPath.lineTo(dst[2] + paintW, dst[3] - paintW);
            mPath.lineTo(dst[4] - paintW, dst[5] - paintW);
            mPath.lineTo(dst[6] - paintW, dst[7] + paintW);
            mPath.close();
            canvas.drawPath(mPath, mPaint);
        }
    }

    private float[] mDst;

    public void setDst(float[] dst) {
        this.mDst = dst;
//        Logger.e("自定义方法");
    }

    public void setLine(Line line) {
        mLine = line;
    }

    void handBound(Bitmap bitmap) {
        if (mLine == Line.No) return;
        if (boundColor != 0) return;
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int color = palette.getDominantColor(Color.LTGRAY);
//                color = ColorUtils.setAlphaComponent(color, 130);
                boundColor = color;
                postInvalidate();
            }
        });
    }

    public enum Line {
        Left, Right, No;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Logger.v("onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
//        Logger.d("onLayout");
    }
}
