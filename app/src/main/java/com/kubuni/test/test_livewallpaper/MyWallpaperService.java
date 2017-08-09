package com.kubuni.test.test_livewallpaper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by justin on 4/27/2017.
 */

public class MyWallpaperService extends WallpaperService {
    @Override
    public Engine onCreateEngine() {
        return new MyWallpaperEngine();
    }

    private class MyWallpaperEngine extends Engine {
        private final Handler handler = new Handler();
        private final Runnable drawRunner = new Runnable(){
            @Override
            public void run(){
                draw();
            }
        };
        private Paint paint = new Paint();
        private boolean visible = true;
        private starfield mystarfield;
        private Rect rectangle = null;
        public MyWallpaperEngine(){

            mystarfield = null;
        }
        @Override
        public void onVisibilityChanged(boolean visible) {
            this.visible = visible;
            if (visible) {
                handler.post(drawRunner);
            } else {
                handler.removeCallbacks(drawRunner);
            }
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            this.visible = false;
            handler.removeCallbacks(drawRunner);
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format,
                                     int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            getDimensions(width, height);
        }

        private void draw(){
            SurfaceHolder holder = getSurfaceHolder();
            Canvas canvas = null;

            try{

                canvas = holder.lockCanvas();
                if((canvas != null) && (mystarfield != null))
                {
                    paint.setStyle(Paint.Style.FILL);
                    paint.setStrokeWidth(0);
                    paint.setARGB(255,0,0,0);
                    canvas.drawRect(rectangle, paint);
                    mystarfield.render(canvas, paint);
                }

            }
            finally{
                if(canvas != null)
                    holder.unlockCanvasAndPost(canvas);
            }
            handler.removeCallbacks(drawRunner);
            if(visible) {
                handler.postDelayed(drawRunner, 100);
            }
        }// draw()

        public void getDimensions(Integer width, Integer height){
            if(mystarfield == null){
                rectangle = new Rect(0,0,width,height);
                mystarfield = new starfield(width, height);
            }
        }
    }// MyWallpaperEngine
}
