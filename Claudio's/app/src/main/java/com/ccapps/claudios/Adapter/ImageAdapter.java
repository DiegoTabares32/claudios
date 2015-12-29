package com.ccapps.claudios.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ccapps.claudios.Activity.MainActivity;
import com.ccapps.claudios.CustomView.BounceImageButton;
import com.ccapps.claudios.CustomView.CircleTransformation;
import com.ccapps.claudios.MediaPlayer.MediaPlayerService;
import com.ccapps.claudios.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;

/**
 * Created by dtabares on 23/12/15.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return sounds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
//        BounceImageButton imageView;

        final ImageAdapterViewHolder imageAdapterViewHolder;

        int size = (int) (mContext.getResources().getDisplayMetrics().heightPixels * 0.15);

        if (convertView == null) {

            imageAdapterViewHolder = new ImageAdapterViewHolder();

            // if it's not recycled, initialize some attributes
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.image_item, parent, false);

            imageAdapterViewHolder.imageButton = (BounceImageButton) convertView.findViewById(R.id.clau_image);
//            imageView = new BounceImageButton(mContext);
            imageAdapterViewHolder.imageButton.setLayoutParams(new LinearLayout.LayoutParams(size, size));
            imageAdapterViewHolder.imageButton.setBackgroundResource(R.drawable.background_null);
            imageAdapterViewHolder.imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageAdapterViewHolder.imageButton.setPadding(8, 8, 8, 8);

            imageAdapterViewHolder.soundName = (TextView) convertView.findViewById(R.id.sound_name);

            convertView.setTag(imageAdapterViewHolder);
        } else {
            imageAdapterViewHolder = (ImageAdapterViewHolder) convertView.getTag();
        }

        Picasso.with(mContext).load(R.drawable.clau3).transform(new CircleTransformation()).into(imageAdapterViewHolder.imageButton);

        imageAdapterViewHolder.soundName.setText(sounds[position].getName().replace("_", " ").toUpperCase());

        imageAdapterViewHolder.imageButton.setOnClickListener(new BounceImageButton.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    final int resourceID = sounds[position].getInt(sounds[position]);

                    MediaPlayerService.getInstance(mContext).play(resourceID);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        return convertView;
    }

    // references to our images
    private Field[] sounds = R.raw.class.getFields();

    private class ImageAdapterViewHolder{
        private BounceImageButton imageButton;
        private TextView soundName;
    }
}
