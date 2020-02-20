package com.yiuwin.catcollection;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Samsung on 3/27/2018.
 */

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;

    }

    // Arrays holding values for our walkthrough slider
    public int[] slide_images = {
            R.drawable.dotalbum,
            R.drawable.dotfood,
            R.drawable.dotpaw
    };

    public String[] slide_headings = {
            "Collect",
            "Feed",
            "Pet"
    };

    public String[] slide_descriptions = {
            "View all the cats in your collection in your cat collection album",
            "Spread some love and feed cats that appear around you",
            "Feel free to pet the kitties too :)"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    public Object instantiateItem(ViewGroup container,int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descriptions[position]);

        container.addView(view);

        return view;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object); // stop when last page reached
    }
}
