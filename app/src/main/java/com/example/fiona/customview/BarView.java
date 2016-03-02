package com.example.fiona.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by fiona on 15-12-10.
 */
public class BarView extends View{
    Paint p;
    Canvas canvas;
    float x=0;
    float y=0;
    float h=0;
    float w=0;
    int padding=16+25;

    ArrayList<PointF> points=new ArrayList<>();

    public BarView(Context context) {
        super(context);
    }

    public BarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p=new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(Color.argb(128, 0, 0, 255));
        canvas.drawRect(50, 100, 450, 200, p);
        canvas.drawRect(50, 300, 550, 400, p);
        canvas.drawRect(50, 500, 350, 600, p);
        canvas.drawRect(50, 700, 650, 800, p);

        if (h==0){
            h=getHeight();
            w=getWidth();
            x=w/2;
            y=h/2;
        }
        for (PointF point:points){
            canvas.drawCircle(point.x,point.y,50,p);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getX()>padding&&event.getX()<w-padding&&event.getY()>padding&&event.getY()<h-padding){
            x=event.getX();
            y=event.getY();
        }
        points.add(new PointF(x,y));
        invalidate();
        return true;
    }
}
