package jiuri.com.firstapplication.adapter;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import jiuri.com.firstapplication.R;
import jiuri.com.firstapplication.ui.activity.AdActivity;

import static jiuri.com.firstapplication.R.mipmap.v;

/**
 * Created by user103 on 2017/7/25.
 */

public class MyPagerAdapter extends PagerAdapter {
    private int [] arr={R.mipmap.vo, v,R.mipmap.vv,R.mipmap.vvvv,R.mipmap.vvvv,R.mipmap.vvvvv};
    private ViewPager mViewPager;
    public MyPagerAdapter(ViewPager viewPager) {
        mViewPager=viewPager;

    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        if (position!=0){
            GSYVideoManager.onPause();
        }
        if (position==0){
            ImageView imageView=new ImageView(container.getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(arr[position]);
            View inflate = View.inflate(container.getContext(), R.layout.video, null);
            final StandardGSYVideoPlayer video = (StandardGSYVideoPlayer) inflate.findViewById(R.id.video);
            video.setThumbImageView(imageView);
            video.setUp("http://baobab.wdjcdn.com/14564977406580.mp4", true , null, "恭喜!恭喜! 又以為宅男女神結婚了");
            video.getTitleTextView().setVisibility(View.GONE);
//非全屏下不显示返回键
            video.setUp("http://baobab.wdjcdn.com/14564977406580.mp4", true,container.getContext().getExternalCacheDir(), "carch");
            video.getBackButton().setVisibility(View.GONE);
//打开非全屏下触摸效果
            video.setIsTouchWiget(false);
            video.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                container.getContext().startActivity(new Intent(container.getContext(), AdActivity.class));
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    video.startPlayLogic();
                }
            });
            container.addView(inflate);
            return inflate;
        }

        ImageView imageView=new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(arr[position]);
        container.addView(imageView);
            return imageView;
    }
    public interface  onStopListener{

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
