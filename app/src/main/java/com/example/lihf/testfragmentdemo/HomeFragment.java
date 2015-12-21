package com.example.lihf.testfragmentdemo;

import java.util.ArrayList;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment extends Fragment{
	
	Resources resources;
    private ViewPager mPager;
    private ArrayList<Fragment> fragmentsList;
    private ImageView ivBottomLine;
    private TextView tvTabNew, tvTabHot,tvTabLove;

    private int currIndex = 0;
    private int bottomLineWidth;
    private int offset = 0;
    private int position_one;
    public final static int NUM = 3 ;
    Fragment home1;
    Fragment home2;
    Fragment home3;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_home, null);
		resources = getResources();
        InitWidth(view);
        InitTextView(view);
        InitViewPager(view);
        TranslateAnimation animation = new TranslateAnimation(position_one, offset, 0, 0);
        tvTabHot.setTextColor(resources.getColor(R.color.lightwhite));
        animation.setFillAfter(true);
        animation.setDuration(300);
        ivBottomLine.startAnimation(animation);
		return view;
	}
	
	  private void InitTextView(View parentView) {
	        tvTabNew = (TextView) parentView.findViewById(R.id.tv_tab_1);
	        tvTabHot = (TextView) parentView.findViewById(R.id.tv_tab_2);
	        tvTabLove = (TextView) parentView.findViewById(R.id.tv_tab_3);

	        tvTabNew.setOnClickListener(new MyOnClickListener(0));
	        tvTabHot.setOnClickListener(new MyOnClickListener(1));
	        tvTabLove.setOnClickListener(new MyOnClickListener(2));
	    }

	    private void InitViewPager(View parentView) {
	        mPager = (ViewPager) parentView.findViewById(R.id.vPager);
	        fragmentsList = new ArrayList<Fragment>();

	        home1 = new HomeFragment_1();
	        home2 = new HomeFragment_2();
	        home3 = new HomeFragment_3();

	        fragmentsList.add(home1);
	        fragmentsList.add(home2);
	        fragmentsList.add(home3);

	        mPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), fragmentsList));
	        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	        mPager.setCurrentItem(0);
	        
	    }

	    private void InitWidth(View parentView) {
	        ivBottomLine = (ImageView) parentView.findViewById(R.id.iv_bottom_line);
	        bottomLineWidth = ivBottomLine.getLayoutParams().width;//40dp
			//获取分辨率
	        DisplayMetrics dm = new DisplayMetrics();
	        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
			//当前分辨率 宽度
			int screenW = dm.widthPixels;
	        offset = (int) ((screenW / NUM - bottomLineWidth) / NUM);
	        int avg = (int) (screenW / NUM);
	        position_one = avg + offset;
	    }

	    public class MyOnClickListener implements View.OnClickListener {
	        private int index = 0;

	        public MyOnClickListener(int i) {
	            index = i;
	        }

	        @Override
	        public void onClick(View v) {
	            mPager.setCurrentItem(index);
	        }
	    };

	    public class MyOnPageChangeListener implements OnPageChangeListener {

	        @Override
	        public void onPageSelected(int arg0) {
	            Animation animation = null;
	            switch (arg0) {
	            case 0:
	                if (currIndex == 1 ||currIndex == 2) {
	                    animation = new TranslateAnimation(position_one, offset, 0, 0);
	                    tvTabHot.setTextColor(resources.getColor(R.color.lightwhite));
						tvTabLove.setTextColor(resources.getColor(R.color.lightwhite));
	                }

	                tvTabNew.setTextColor(resources.getColor(R.color.white));
	                break;
	            case 1:
	                if (currIndex == 0 ||currIndex == 2) {
	                    animation = new TranslateAnimation(offset, position_one, 0, 0);
	                    tvTabNew.setTextColor(resources.getColor(R.color.lightwhite));
						tvTabLove.setTextColor(resources.getColor(R.color.lightwhite));
	                }
	                tvTabHot.setTextColor(resources.getColor(R.color.white));
	                break;
				case 2:
					if (currIndex == 1||currIndex == 0) {
						animation = new TranslateAnimation(offset, position_one, 0, 0);
						tvTabHot.setTextColor(resources.getColor(R.color.lightwhite));
						tvTabNew.setTextColor(resources.getColor(R.color.lightwhite));
					}
					tvTabLove.setTextColor(resources.getColor(R.color.white));
					break;
	            }
	            currIndex = arg0;
//	            animation.setFillAfter(true);
//	            animation.setDuration(300);
//	            ivBottomLine.startAnimation(animation);
	        }

	        @Override
	        public void onPageScrolled(int arg0, float arg1, int arg2) {
	        }

	        @Override
	        public void onPageScrollStateChanged(int arg0) {
	        }
	    }

}
