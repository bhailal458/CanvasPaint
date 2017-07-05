package com.example.sparken02.canvaspaint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "Clicked";
    private int PICK_IMAGE_REQUEST = 1;
    private DrawView drawview;
    private RecyclerView mRecyclerView,frameRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawview = (DrawView) findViewById(R.id.customview);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        frameRecyclerView = (RecyclerView) findViewById(R.id.framerecyclerView);
        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        frameRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList items = new ArrayList<>(Arrays.asList(
                R.drawable.square,
                R.drawable.rectangle,
                R.drawable.roundrectangle,
                R.drawable.circle,
                R.drawable.oval));
        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(MainActivity.this,items);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                if(bitmap != null) {
                    switch (position){
                        case 0:  //Square
                            Bitmap squareimage = drawview.getSquareBitmap(bitmap);
                            drawview.setBitmap(squareimage);
                            break;
                        case 1:  //Rectangle
                            Bitmap rectangleimage = drawview.getRectangleBitmap(bitmap);
                            drawview.setBitmap(rectangleimage);

                            break;
                        case 2:  //RoundRectangle
                            Bitmap roundRectimage = drawview.getRoundRectangleBitmap(bitmap);
                            drawview.setBitmap(roundRectimage);

                            break;
                        case 3:  //Circle
                            Bitmap circleimage = drawview.getCircleBitmap(bitmap);
                            drawview.setBitmap(circleimage);
                            break;
                        case 4:  //Oval
                            Bitmap ovalimage = drawview.getOvalBitmap(bitmap);
                            drawview.setBitmap(ovalimage);
                            break;
                    }
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        final ArrayList frameitems = new ArrayList<>(Arrays.asList(
                R.drawable.frameone,
                R.drawable.frametwo,
                R.drawable.framethree,
                R.drawable.framefour));

        RecyclerViewAdapter frameAdapter = new RecyclerViewAdapter(MainActivity.this,frameitems);
        frameRecyclerView.setAdapter(frameAdapter);

        frameRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                frameRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                if(bitmap != null) {
                    switch (position){
                        case 0:  //frame one

                            Bitmap frameone = BitmapFactory.decodeResource(getResources
                                    (),R.drawable.frameone);
                            Bitmap one = drawview.setframeOne(frameone,bitmap);
                            drawview.setBitmap(one);
//                            drawview.setBackground(getResources().getDrawable(R.drawable.frameone));

                            break;
                        case 1:  //frame two
                            drawview.setBackground(getResources().getDrawable(R.drawable.frametwo));

                            break;
                        case 2:  //frame three
                            drawview.setBackground(getResources().getDrawable(R.drawable.framethree));
                            break;
                        case 3:  //frame four
                            drawview.setBackground(getResources().getDrawable(R.drawable.framefour));
                            break;

                    }
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
//////////////////////////////////////

        drawview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleTouch(event);
                return true;
            }
        });
    }
    void handleTouch(MotionEvent event)
    {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
                if (x > 150 && x < 600 && y < 550 && y > 150) {
                    Log.i(TAG, "onTouchEvent: ");

                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "select image"), PICK_IMAGE_REQUEST);

                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data !=null && data.getData()!=null){
            Uri uri = data.getData();
            try{

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                drawview.setBitmap(bitmap);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}

