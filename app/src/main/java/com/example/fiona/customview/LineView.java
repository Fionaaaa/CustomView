package com.example.fiona.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fiona on 15-12-10.
 */
public class LineView extends View {
    Paint p;

    public LineView(Context context) {
        this(context, null);
    }

    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        p.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setStrokeWidth(10);
        p.setColor(Color.argb(128, 0, 0, 255));
        float[] points = {100, 100, 200, 300, 300, 200, 400, 500};
        canvas.drawPoints(points, p);
        p.setColor(Color.argb(128, 255, 0, 0));
        p.setStrokeWidth(5);
        float[] lines = {100, 100, 200, 300, 200, 300, 300, 200, 300, 200, 400, 500};
        canvas.drawLines(lines, p);
    }
}
