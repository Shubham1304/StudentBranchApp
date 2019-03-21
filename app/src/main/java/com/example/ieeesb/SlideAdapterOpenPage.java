package com.example.ieeesb;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SlideAdapterOpenPage extends PagerAdapter {
    Context context;
    LayoutInflater inflater;

    public SlideAdapterOpenPage(Context context) {
        this.context = context;
    }

    // list of images
    public int[] slide_images = {
            R.drawable.inspire3,
            R.drawable.motivate4,
            R.drawable.womenempower3
    };
    //list of titles
    public String[] slide_headings = {
            "INSPIRE",
            "MOTIVATE",
            "EMPOWER"
    }   ;
    /*
    // list of descriptions
    public String[] slide_descs = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,"
    };*/
    // list of background colors
    public int[]  slide_backgroundcolor = {
            Color.rgb(55,55,55),
            Color.rgb(239,85,85),
            Color.rgb(110,49,89)
    };




    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view ==(LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide_open_page,container,false);

        LinearLayout layoutslide = (LinearLayout) view.findViewById(R.id.slide_open_page_Layout);
        ImageView slideImageView = (ImageView)  view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        //TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);
        //TextView testbtn = view.findViewById(R.id.testbtn);


        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        //slideDescription.setText(slide_descs[position]);
        // testbtn.setText("Successful");

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}