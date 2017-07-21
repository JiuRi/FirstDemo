package jiuri.com.firstapplication.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import jiuri.com.firstapplication.R;


/**
 * Created by user103 on 2017/7/20.
 */

public class SetingActivity extends AppCompatActivity {
    private ImageView mX;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_setting);
        mX = (ImageView) findViewById(R.id.x);
        mX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.loginactivity_enter,R.anim.loginactivityout);
            }
        });
    }
}
