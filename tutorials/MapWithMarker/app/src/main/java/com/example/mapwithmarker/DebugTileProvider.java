package com.example.mapwithmarker;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.Log;

import com.google.android.libraries.maps.model.Tile;
import com.google.android.libraries.maps.model.TileProvider;

import java.io.ByteArrayOutputStream;

public class DebugTileProvider implements TileProvider {

    int imageSize = 512;

    public int i = 0;

    @Override
    public Tile getTile(int x, int y, int zoomLevel) {
        Log.d("TAG", "START getTile x = $x, y = $y, zoom = $zoom");
//        final startTime =DateTime.now().millisecondsSinceEpoch;


        Bitmap bitmap = Bitmap.createBitmap(imageSize, imageSize, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);

        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(40);
        canvas.drawText(x + ", " + y + "\n zoom=" + zoomLevel + " I = " + i, 10, 50, textPaint);

        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.GREEN);
        rectPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(new Rect(0, 0, imageSize, imageSize), rectPaint);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        bitmap.recycle();

        return new Tile(imageSize, imageSize, byteArray);
    }
}
