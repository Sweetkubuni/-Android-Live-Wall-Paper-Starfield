package com.kubuni.test.test_livewallpaper;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by justin on 4/29/2017.
 */

public class star {
    private float x;
    private float y;
    private float width_lim;
    private float height_lim;
    private final float start_pos = 0;

    public star(float x, float y, float width_lim, float height_lim){
        this.x = x;
        this.y = y;

        this.width_lim  = width_lim;
        this.height_lim = height_lim;
    }

    public void increment_x(float v){
        if(x < width_lim)
        {
            x += v;
        }
        else {
            x = start_pos;
        }
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(x, y, 5, paint);
    }
};
