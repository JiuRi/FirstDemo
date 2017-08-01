package jiuri.com.firstapplication.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;
import jiuri.com.firstapplication.R;

/**
 * Created by user103 on 2017/7/31.
 */

public class VideoPlayActivity extends Activity {
    @BindView(R.id.video)
    StandardGSYVideoPlayer mVideo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplay);
        ButterKnife.bind(this);
        ImageView imageView=new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(R.mipmap.vo);
        mVideo.setThumbImageView(imageView);
        mVideo.getTitleTextView().setVisibility(View.VISIBLE);
//非全屏下不显示返回键
        mVideo.setUp("http://baobab.wdjcdn.com/14564977406580.mp4", true,getExternalCacheDir(), "恭喜!恭喜! 又以為宅男女神結婚了");
        mVideo.getBackButton().setVisibility(View.VISIBLE);
//打开非全屏下触摸效果
        mVideo.setIsTouchWiget(true);
        View startButton = mVideo.getStartButton();


        mVideo.getFullWindowPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideo.onAutoCompletion();
        GSYVideoManager.instance().releaseMediaPlayer();
    }
}
