package com.ccapps.claudios.CustomView;

/**
 * Created by dtabares on 09/09/15.
 */

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;

public class BounceImageButton extends ImageButton {

    private SpringSystem springSystem;
    private Spring spring;
    private Rect rect;
    private View view;
    private OnClickListener onClickListener;

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public BounceImageButton(Context context) {
        super(context);
        init();
    }

    public BounceImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BounceImageButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init(){

        view = BounceImageButton.this;

        springSystem = SpringSystem.create();
        spring = springSystem.createSpring();

        // Create a system to run the physics loop for a set of springs.

//        SpringSystem springSystem = SpringSystem.create();
//// Add a spring to the system.
//        final Spring spring = springSystem.createSpring();

// Add a listener to observe the motion of the spring.

        spring.addListener(new SimpleSpringListener() {

            @Override
            public void onSpringUpdate(Spring spring) {
                // You can observe the updates in the spring
                // state by asking its current value in onSpringUpdate.
//                float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 1, 0.5);
                float value = (float) spring.getCurrentValue();
                float scale = 1f - (value * 0.2f);
                view.setScaleX(scale);
                view.setScaleY(scale);

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                view.setPressed(true);

                rect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());

                // When pressed start solving the spring to 1.
                spring.setEndValue(1);
                break;
            case MotionEvent.ACTION_UP:

                spring.setEndValue(0);
                if (!rect.contains(view.getLeft() + (int) event.getX(), view.getTop() + (int) event.getY())) {
                    spring.setEndValue(0);
                }else {

                    if(onClickListener!=null) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onClickListener.onClick(view);
                            }
                        },200);

                    }
                }

                view.setPressed(false);

                break;
            case MotionEvent.ACTION_MOVE:
                if(!rect.contains(view.getLeft() + (int) event.getX(), view.getTop() + (int) event.getY())){
                    spring.setEndValue(0);
                    view.setPressed(false);

                }
                // When released start solving the spring to 0.

                break;
            case MotionEvent.ACTION_CANCEL:
//                if(!rect.contains(view.getLeft() + (int) event.getX(), view.getTop() + (int) event.getY())){
                spring.setEndValue(0);
                view.setPressed(false);

//                }
                // When released start solving the spring to 0.

                break;
        }
        return true;
    }

    public interface OnClickListener{
        void onClick(View view);
    }
}
