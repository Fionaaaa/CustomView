package com.example.fiona.customview.homework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fiona on 15-12-10.
 */
public class BarLineView extends View {
    Paint p;

    public BarLineView(Context context) {
        this(context, null);
    }

    public BarLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        p.setColor(Color.argb(128, 00, 00, 225));
        p.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 画一个柱形图和一条折线
         */

        //矩形
        canvas.drawRect(200, 200, 300, 650, p);
        canvas.drawRect(400, 300, 500, 650, p);
        canvas.drawRect(600, 150, 700, 650, p);
        canvas.drawRect(800, 350, 900, 650, p);

        //椭圆
        RectF rect = new RectF(200, 175, 300, 225);
        canvas.drawArc(rect, 0, 360, true, p);
        canvas.drawArc(rect, 180, 180, true, p);
        rect = new RectF(400, 275, 500, 325);
        canvas.drawArc(rect, 0, 360, true, p);
        canvas.drawArc(rect, 180, 180, true, p);
        rect = new RectF(600, 125, 700, 175);
        canvas.drawArc(rect, 0, 360, true, p);
        canvas.drawArc(rect, 180, 180, true, p);
        rect = new RectF(800, 325, 900, 375);
        canvas.drawArc(rect, 0, 360, true, p);
        canvas.drawArc(rect, 180, 180, true, p);

        //折线
        p.setStrokeWidth(20);
        p.setColor(Color.RED);
        float[] points = new float[]{250, 125, 450, 225, 650, 75, 850, 275};
        canvas.drawPoints(points, p);
        points = new float[]{250, 125, 450, 225, 450, 225, 650, 75, 650, 75, 850, 275};
        p.setStrokeWidth(5);
        canvas.drawLines(points, p);

        //坐标系
        p.setColor(Color.argb(128, 00, 00, 225));
        canvas.drawLine(100, 50, 100, 700, p);
        canvas.drawLine(100, 700, 1000, 700, p);
    }
}
