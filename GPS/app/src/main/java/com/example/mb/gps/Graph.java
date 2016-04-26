package com.example.mb.gps;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.jar.Attributes;

/**
 * Created by MB on 26/04/2016.
 */
public class Graph extends View {
    public Graph(Context context, AttributeSet attri) {
        super(context, attri);
    }

    int size;

    @Override
    public void onSizeChanged(int x, int y, int xO, int yO)
    {
        super.onSizeChanged(x, y, xO, yO);
            xO = x;
            yO = y;
        if(x<y)
        {
            this.size = x;
        }
        else
        {
            this.size = y;
        }
    }
    private Paint paint;


    public void onDraw(Canvas can)
    {
        size = 720;
        paint = new Paint();
        paint.setColor(Color.BLACK);
        can.drawRect(0, 0, size, size, paint);
        paint.setColor(Color.WHITE);
        can.drawLine(0, 0, size, 0, paint);
        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        can.drawLine(0, 121, size, 121, paint2);
        can.drawLine(0,240,size, 240,paint);
        can.drawLine(0,360,size, 360,paint);
        can.drawLine(0,481,size, 481,paint);
        can.drawLine(0,600,size, 600,paint);
        can.drawLine(0,719,size, 719,paint);
        super.onDraw(can);

    }
}
