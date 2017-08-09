package com.kubuni.test.test_livewallpaper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by justin on 4/30/2017.
 */

public class starfield {
    private Map<Integer, List<star>> fields;
    private static final Integer num_stars = 150;
    private int width;
    private int height;
    private static final float front_velocity = 50;
    public starfield(Integer width, Integer height){
        Log.i("STARFIELD:", "Enters constructor");
        fields = new HashMap<Integer, List<star>>();
        fields.put(1, new ArrayList<star>());
        fields.put(2, new ArrayList<star>());
        fields.put(3, new ArrayList<star>());
        fields.put(4, new ArrayList<star>());
        fields.put(5, new ArrayList<star>());

        Random r = new Random();
        this.width  = width;
        this.height = height;
        for(int i = 0; i < num_stars; i++){
            int  rand_depth = r.nextInt(5 - 1) + 1;
            star new_star   = new star(r.nextInt(width - 10) + 10, r.nextInt(height - 10) + 10, width, height);
            fields.get(rand_depth).add(new_star);
        }

        Log.i("STARFIELD:", "Finished Generating Stars");
    }

    public void render(Canvas canvas, Paint paint){
        for(int depth = 1; depth < 6; depth++){
            for(star s : fields.get(depth)){
                paint.setARGB(255, 255/depth, 255/depth, 255/depth);

                s.draw(canvas, paint);
                s.increment_x(front_velocity/depth);
            }
        }
    }
}
