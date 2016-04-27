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
        else {
            this.size = y;
        }
    }
    private Paint paint;


    public void onDraw(Canvas can)
    {
        super.onDraw(can);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        can.drawRect(0, 0, size, size, paint);
        paint.setColor(Color.WHITE);
        can.drawLine(0, 1, size, 1, paint);
        can.drawLine(0, size-(5*size/6), size, size-(5*size/6), paint);
        can.drawLine(0,size-(4*size/6),size, size-(4*size/6),paint);
        can.drawLine(0,(size-(3*size/6))+4,size, (size-(3*size/6))+4,paint);
        can.drawLine(0,size-(2*size/6),size, size-(2*size/6),paint);
        can.drawLine(0,(size-(size/6))+1,size, (size-(size/6))+1,paint);
        can.drawLine(0, size - 1, size, size - 1, paint);
        Paint paint2 = new Paint();
        paint2.setColor(Color.GREEN);
        float tata= (float) 3.6;
        for(int i= 0; i<29; i++)
        {
            if (GPS.localist[i] != null && GPS.localist[i+1]!=null)
            {
                can.drawLine((size/30)*i, (float) ((size - (GPS.localist[i].getSpeed()*size)/60)),(size/30)*(i+1), (float) ((size-(GPS.localist[i+1].getSpeed()*size)/60)),paint2);
            }
        }
        float speed = 0;

        for(int j = 0; j<GPS.ind; j++)
        {
            if (GPS.localist[j] != null)
            {
                speed += GPS.localist[j].getSpeed()*3.6f;
            }
        }
        speed = speed/GPS.ind;
        Paint paint3 = new Paint();
        paint3.setColor(Color.RED);
        can.drawLine(0, (speed * size) / 90, size, (speed * size) / 90, paint3);
        GPS.t2.setText("Averrage Speed : "+speed);
        invalidate();
    }
}
