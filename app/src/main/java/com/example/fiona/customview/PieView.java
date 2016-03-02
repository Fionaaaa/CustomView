package com.example.fiona.customview;

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
public class PieView extends View {
    Paint p;
    int h;
    int w;
    int size;
    int padding = 16;

    int[] colors;

    float more = 0;

    private float[] arcs;

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        h = getHeight();
        w = getWidth();

        p.setColor(Color.RED);

        size = (w < h ? w : h) - padding * 2;
        p.setStyle(Paint.Style.STROKE);
        canvas.drawRect((w - size) / 2, (h - size) / 2, (w + size) / 2, (h + size) / 2, p);

        p.setStyle(Paint.Style.FILL);
        RectF rect = new RectF((w - size) / 2, (h - size) / 2, (w + size) / 2, (h + size) / 2);
        int t = 0;
        if (null != arcs) {
            for (int i = 0; i < arcs.length; i++) {
                p.setColor(colors[i]);
                canvas.drawArc(rect, t, arcs[i], true, p);
                t += arcs[i];
            }
            canvas.drawArc(rect, t, 360 - t, true, p);
        }
    }

    public void setData(float[] data) {
        arcs = new float[data.length];
        colors = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            colors[i] = getColor();
        }
        float sum = 0f;
        for (float n : data) {
            sum += n;
        }
        for (int i = 0; i < data.length; i++) {
            float d = data[i] / sum * 360;
            arcs[i] = d;
            more += d;
        }
        invalidate();       //重绘
    }

    private int getColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return Color.rgb(r, g, b);
    }
}
