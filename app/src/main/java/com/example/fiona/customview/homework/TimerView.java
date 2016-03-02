package com.example.fiona.customview.homework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fiona on 15-12-11.
 */
public class TimerView extends View {
    Paint p;
    float w;
    float h;
    PointF point;
    float r;

    float minute = 0;
    float second = 0;

    public TimerView(Context context) {
        this(context, null);
    }

    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.argb(0Xff, 0x00, 0x99, 0xcc));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (w == 0) {
            w = getWidth();
            h = getHeight();
            point = new PointF(w / 2, h / 2 - 50);
            r = w / 4;
        }

        p.setStrokeWidth(2);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(point.x, point.y, w / 4, p);
        p.setStyle(Paint.Style.FILL);
        canvas.drawCircle(point.x, point.y, 10, p);

        p.setTextSize(48);
        canvas.drawText("60", w / 2 - 25, h / 2 - 50 - w / 4 + 50, p);
        canvas.drawText("30", w / 2 - 25, h / 2 + w / 4 - 60, p);
        canvas.drawText("15", w * 3 / 4 - 62, h / 2 - 40, p);
        canvas.drawText("45", w / 4 + 7, h / 2 - 30, p);

        canvas.drawLine(point.x + (r - 20) * 0.866f, point.y - (r - 20) * 0.5f, point.x + r * 0.866f, point.y - r * 0.5f, p);//10
        canvas.drawLine(point.x + (r - 20) * 0.5f, point.y - (r - 20) * 0.866f, point.x + r * 0.5f, point.y - r * 0.866f, p);//5
        canvas.drawLine(point.x + (r - 20) * 0.866f, point.y + (r - 20) * 0.5f, point.x + r * 0.866f, point.y + r * 0.5f, p);//20
        canvas.drawLine(point.x + (r - 20) * 0.5f, point.y + (r - 20) * 0.866f, point.x + r * 0.5f, point.y + r * 0.866f, p);//25
        canvas.drawLine(point.x - (r - 20) * 0.5f, point.y + (r - 20) * 0.866f, point.x - r * 0.5f, point.y + r * 0.866f, p);//35
        canvas.drawLine(point.x - (r - 20) * 0.866f, point.y + (r - 20) * 0.5f, point.x - r * 0.866f, point.y + r * 0.5f, p);//40
        canvas.drawLine(point.x - (r - 20) * 0.866f, point.y - (r - 20) * 0.5f, point.x - r * 0.866f, point.y - r * 0.5f, p);//50
        canvas.drawLine(point.x - (r - 20) * 0.5f, point.y - (r - 20) * 0.866f, point.x - r * 0.5f, point.y - r * 0.866f, p);//35

        p.setStrokeWidth(4);
        doSecond(canvas, second + 270);
        doMinute(canvas, minute + 270);
    }

    private void doSecond(Canvas canvas, float f) {
        float x = 0, y = 0;
        f = f % 360;
        if (f >= 270 && f < 360) {
            x = (float) (point.x + (r - 60) * Math.cos((360 - f) * Math.PI / 180));
            y = (float) (point.y - (r - 60) * Math.sin((360 - f) * Math.PI / 180));
        }
        if (f >= 0 && f < 90) {
            x = (float) (point.x + (r - 60) * Math.cos(f * Math.PI / 180));
            y = (float) (point.y + (r - 60) * Math.sin(f * Math.PI / 180));
        }
        if (f >= 90 && f < 180) {
            x = (float) (point.x - (r - 60) * Math.cos((180 - f) * Math.PI / 180));
            y = (float) (point.y + (r - 60) * Math.sin((180 - f) * Math.PI / 180));
        }
        if (f >= 180 && f < 270) {
            x = (float) (point.x - (r - 60) * Math.cos((f - 180) * Math.PI / 180));
            y = (float) (point.y - (r - 60) * Math.sin((f - 180) * Math.PI / 180));
        }
        canvas.drawLine(point.x, point.y, x, y, p);
    }

    private void doMinute(Canvas canvas, float f) {
        float x = 0, y = 0;
        f = f % 360;
        if (f >= 270 && f < 360) {
            x = (float) (point.x + (r - 120) * Math.cos((360 - f) * Math.PI / 180));
            y = (float) (point.y - (r - 120) * Math.sin((360 - f) * Math.PI / 180));
        }
        if (f >= 0 && f < 90) {
            x = (float) (point.x + (r - 120) * Math.cos(f * Math.PI / 180));
            y = (float) (point.y + (r - 120) * Math.sin(f * Math.PI / 180));
        }
        if (f >= 90 && f < 180) {
            x = (float) (point.x - (r - 120) * Math.cos((180 - f) * Math.PI / 180));
            y = (float) (point.y + (r - 120) * Math.sin((180 - f) * Math.PI / 180));
        }
        if (f >= 180 && f < 270) {
            x = (float) (point.x - (r - 120) * Math.cos((f - 180) * Math.PI / 180));
            y = (float) (point.y - (r - 120) * Math.sin((f - 180) * Math.PI / 180));
        }
        canvas.drawLine(point.x, point.y, x, y, p);
    }

    public void callback(int millions) {
        second = 360 * (millions % 100) / 100f;
        minute = 6 * millions / 100f;
        invalidate();
    }
}
