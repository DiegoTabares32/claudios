package com.ccapps.claudios.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ccapps.claudios.Adapter.ImageAdapter;
import com.ccapps.claudios.CustomView.CircleTransformation;
import com.ccapps.claudios.MediaPlayer.MediaPlayerService;
import com.ccapps.claudios.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_grid);


        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//
//
//            }
//        });

//        int size = (int) (getResources().getDisplayMetrics().heightPixels * 0.15);
//
//        LinearLayout mainLinear = (LinearLayout) findViewById(R.id.main_linear);
//
//
////        ImageButton playButton = (ImageButton) findViewById(R.id.play_button);
////        playButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                MediaPlayerService.getInstance(MainActivity.this).play(R.raw.titanic);
////            }
////        });
//
//        Field[] fields=R.raw.class.getFields();
//        for(int count=0; count < fields.length; count++){
//            Log.v("Raw Asset: ", fields[count].getName());
//
//            try {
//                final int resourceID=fields[count].getInt(fields[count]);
//
//                ImageButton imageButton = new ImageButton(this);
////                imageButton.setImageResource(R.drawable.clau2);
//                imageButton.setBackgroundResource(R.drawable.background_null);
//                imageButton.setScaleType(ImageView.ScaleType.FIT_XY);
//                imageButton.setLayoutParams(new ViewGroup.LayoutParams(size,size));
//                Picasso.with(this).load(R.drawable.clau3).transform(new CircleTransformation()).into(imageButton);
//                imageButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        MediaPlayerService.getInstance(MainActivity.this).play(resourceID);
//                    }
//                });
//
//                mainLinear.addView(imageButton);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }

    }


    @Override
    protected void onDestroy() {
        MediaPlayerService.getInstance(this).destroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        MediaPlayerService.getInstance(this).destroy();
        super.onPause();
    }
}
