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
public class RefreshView extends View {
    Paint p;
    float w = 0;
    float h = 0;
    Canvas canvas;
    RectF rect;
    float from = 0;

    public RefreshView(Context context) {
        this(context, null);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.argb(255, 255, 255, 255));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        if (w == 0) {
            w = getWidth();
            h = getHeight();
        }
        rect = new RectF(w / 2 - 85, h / 2 - 85, w / 2 + 85, h / 2 + 85);

        p.setColor(Color.argb(255, 255, 255, 255));
        canvas.drawArc(rect, 0, 360, true, p);

        p.setColor(Color.argb(0xff, 0xaa, 0x66, 0xcc));
        canvas.drawArc(rect, from, 60, true, p);
        from += 3;
        if (from % 360 >= 270 && from % 360 < 315) {
            from += 3;
        } else if (from % 360 >= 315 && from % 360 < 360) {
            from += 8;
        } else if (from % 360 >= 0 && from % 360 < 45) {
            from += 20;
        } else if (from % 360 >= 45 && from % 360 < 90) {
            from += 15;
        } else if (from % 360 >= 90 && from % 360 < 135) {
            from += 8;
        }

        p.setColor(Color.argb(0xff, 0x00, 0x99, 0xcc));
        canvas.drawCircle(w / 2, h / 2, 70, p);
    }

    public void refresh() {
        invalidate();
    }
}
