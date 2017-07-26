package jiuri.com.firstapplication.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import jiuri.com.firstapplication.R;

/**
 * Created by user103 on 2017/7/25.
 */

public class MyPagerAdapter extends PagerAdapter {
    private int [] arr={R.mipmap.vo,R.mipmap.v,R.mipmap.vv,R.mipmap.vvvv,R.mipmap.vvvv,R.mipmap.vvvvv};
    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView=new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(arr[position]);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnPagerClickListener.click(position);
            }
        });
        container.addView(imageView);
            return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
    private onPagerClickListener mOnPagerClickListener;
    public void setonPagerClickListener(onPagerClickListener clickListener){
        mOnPagerClickListener=clickListener;
    }
    public  interface onPagerClickListener{
        void click(int position);
    }
}
