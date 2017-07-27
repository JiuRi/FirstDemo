package jiuri.com.firstapplication.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import jiuri.com.firstapplication.R;

/**
 * Created by user103 on 2017/7/24.
 */

public class AdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_adactivity);
        final StandardGSYVideoPlayer video = (StandardGSYVideoPlayer)findViewById(R.id.video);
        video.setUp("http://baobab.wdjcdn.com/14564977406580.mp4", true , null, "恭喜!恭喜! 又以為宅男女神結婚了");
        video.getTitleTextView().setVisibility(View.VISIBLE);
//非全屏下不显示返回键
        video.setUp("http://baobab.wdjcdn.com/14564977406580.mp4", true,this.getExternalCacheDir(), "carch");
        video.getBackButton().setVisibility(View.VISIBLE);
//打开非全屏下触摸效果
        video.setIsTouchWiget(true);
        video.startPlayLogic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }
}
