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
public class CompassView extends View {
    private static final String APP_NAME = "compass";
    Paint p;
    float w = 0;
    float h = 0;
    float r = 0;
    PointF pf;
    float len;
    float direction = 0;

    public CompassView(Context context) {
        this(context, null);
    }

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.argb(0xff, 0x66, 0x99, 0xcc));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (w == 0) {
            w = getWidth();
            h = getHeight();
            r = (w < h ? w / 4 : h / 4);
            pf = new PointF(w / 2, h / 2);
            len = 30;
        }

        p.setColor(Color.argb(0xff, 0x66, 0x99, 0xcc));
        p.setStrokeWidth(4);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(pf.x, pf.y, r, p);

        p.setStrokeWidth(2);
        p.setTextSize(48);
        canvas.drawText(APP_NAME, pf.x - 100, pf.y - 500, p);

        if (0 != direction) {
            drawPointLine(canvas, direction, len);

            p.setColor(Color.argb(0xff, 0xff, 0xff, 0xff));
            drawPointLine(canvas, direction, r - len - 10);
        }

        p.setColor(Color.argb(0xff, 0x66, 0x99, 0xcc));
        if (direction % 90 >= 0 && direction % 90 <= 2) {
            p.setStrokeWidth(4);
            p.setColor(Color.argb(0xff, 0x99, 0x00, 0x00));
        } else if (direction % 90 >= 88 && direction % 90 < 90) {
            p.setStrokeWidth(4);
            p.setColor(Color.argb(0xff, 0x99, 0x00, 0x00));
        }
        canvas.drawLine(pf.x, pf.y, pf.x, pf.y + len, p);
        canvas.drawLine(pf.x, pf.y, pf.x, pf.y - len, p);
        canvas.drawLine(pf.x, pf.y, pf.x + len, pf.y, p);
        canvas.drawLine(pf.x, pf.y, pf.x - len, pf.y, p);
        canvas.drawLine(pf.x + r - len, pf.y, pf.x + r, pf.y, p);
        canvas.drawLine(pf.x - r + len, pf.y, pf.x - r, pf.y, p);
        canvas.drawLine(pf.x, pf.y + r - len, pf.x, pf.y + r, p);
        canvas.drawLine(pf.x, pf.y - r + len, pf.x, pf.y - r, p);
    }

    private void drawPointLine(Canvas canvas, float f, float len) {
        float x = 0, y = 0;
        f = 360 - f + 180;
        f = f % 360;
        if (f >= 0 && f < 90) {
            x = (float) (pf.x + (r - len - 5) * Math.cos((90 - f) * Math.PI / 180));
            y = (float) (pf.y - (r - len - 5) * Math.sin((90 - f) * Math.PI / 180));
        }
        if (f >= 90 && f < 180) {
            x = (float) (pf.x + (r - len - 5) * Math.cos((f - 90) * Math.PI / 180));
            y = (float) (pf.y + (r - len - 5) * Math.sin((f - 90) * Math.PI / 180));
        }
        if (f >= 180 && f < 270) {
            x = (float) (pf.x - (r - len - 5) * Math.cos((270 - f) * Math.PI / 180));
            y = (float) (pf.y + (r - len - 5) * Math.sin((270 - f) * Math.PI / 180));
        }
        if (f >= 270 && f < 360) {
            x = (float) (pf.x - (r - len - 5) * Math.cos((f - 270) * Math.PI / 180));
            y = (float) (pf.y - (r - len - 5) * Math.sin((f - 270) * Math.PI / 180));
        }
        canvas.drawLine(pf.x, pf.y, x, y, p);
    }

    public void callback(float f) {
        direction = f;
        invalidate();
    }
}
