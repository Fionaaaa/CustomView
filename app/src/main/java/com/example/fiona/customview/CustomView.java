package com.example.fiona.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;

/**
 * Created by fiona on 15-12-10.
 */
public class CustomView extends View {
    private static final String NAME_SPACE = "http://com.newer.com.android.ui";
    private static final String ATTR_PADDING = "padding";
    Paint paint;
    int w;
    int h;
    int r;
    boolean isInit = true;
    /**
     * 自定义内边距
     *
     * @see ATTR_PADDING
     */
    int padding = 16;

    public CustomView(Context context) {
        this(context, null);
    }

    /**
     * @param context 上下文
     * @param attrs   属性值
     */
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.RED);   //  画笔颜色
        paint.setStyle(Paint.Style.STROKE); //  画笔风格
        paint.setAntiAlias(true);           //  抗锯齿

        padding = attrs.getAttributeIntValue(NAME_SPACE, ATTR_PADDING, 0);
    }

    /**
     * 绘制
     *
     * @param canvas 画布
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isInit) {
            isInit = false;
            h = getHeight();
            w = getWidth();
            r = (w > h ? h : w) / 2 - padding;
        }
        canvas.drawLine(300 + 260 - 1, 150 - 1, w / 2, h / 2, paint);
        canvas.drawCircle(w / 2, h / 2, h / 2, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(w / 2, h / 2, 10, paint);
    }
}
