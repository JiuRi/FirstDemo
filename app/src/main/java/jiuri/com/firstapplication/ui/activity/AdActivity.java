package jiuri.com.firstapplication.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.pixelad.AdControl;

import jiuri.com.firstapplication.R;

/**
 * Created by user103 on 2017/7/24.
 */

public class AdActivity extends AppCompatActivity {
    private AdControl adControl = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_adactivity);
        adControl = (AdControl) this.findViewById(R.id.crazy_banner);
        adControl.setOnPMAdListener(new AdControl.OnPMAdListener() {

            @Override
            public void onFeedCompleted() {
                // TODO Auto-generated method stub

                adControl.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailedToLoad(Exception exception) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onBrowserClosed() {
                // TODO Auto-generated method stub

                adControl.destroyDrawingCache();

            }

            @Override
            public void onAdLoadCompleted() {
                // TODO Auto-generated method stub
            }
        });
        adControl.setSID("6030896314445");

    }
}
