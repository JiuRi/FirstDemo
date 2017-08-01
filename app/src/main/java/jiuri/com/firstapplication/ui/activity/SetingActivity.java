package jiuri.com.firstapplication.ui.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import jiuri.com.firstapplication.R;
import jiuri.com.firstapplication.bean.MyTextSizeMessage;


/**
 * Created by user103 on 2017/7/20.
 */

public class SetingActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.seekbar_self)
    SeekBar mSeekbarSelf;
    @BindView(R.id.share_tofrends)
    LinearLayout mShareTofrends;
    @BindView(R.id.textsize)
    TextView mTextsize;
    @BindView(R.id.view)
    CardView mView;
    private Handler mHandler;
    private int textsize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        setContentView(R.layout.fragment_setting);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        SharedPreferences sp = getSharedPreferences("textsize", MODE_PRIVATE);
        int textsize2 = sp.getInt("textsize", 18);
        Log.d("TAG", "onCreate: _________"+textsize2);
        switch (textsize2){
            case 13:
                mSeekbarSelf.setProgress(0);
                break;
            case 15:
                mSeekbarSelf.setProgress(1);
                break;
            case 17:
                mSeekbarSelf.setProgress(2);
                break;
            case 20:
                mSeekbarSelf.setProgress(3);
                break;
            case 23:
                mSeekbarSelf.setProgress(4);
                break;
        }

        mSeekbarSelf.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mView.setVisibility(View.VISIBLE);
                if (progress<=2){
                    textsize=13+progress*2;
                    mTextsize.setTextSize(textsize);
                }
                else {
                    textsize=17+(progress-2)*3;
                    mTextsize.setTextSize(textsize);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences sp = getSharedPreferences("textsize", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putInt("textsize", textsize);
                edit.commit();
                MyTextSizeMessage myTextSizeMessage = new MyTextSizeMessage();
                myTextSizeMessage.setTextsisze(5);
                EventBus.getDefault().post(myTextSizeMessage);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mView.setVisibility(View.GONE);

                    }
                },3000);

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mShareTofrends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_TEXT, "明報新聞 https://play.google.com/store/apps/details?id=com.mingpao.mpnewsandroid&hl=zh-TW" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "");
                startActivity(Intent.createChooser(textIntent, "分享"));
            }
        });
    }

    private String getResourcesUri(@DrawableRes int id) {
        Resources resources = getResources();
        String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);
        Toast.makeText(this, "Uri:" + uriPath, Toast.LENGTH_SHORT).show();
        return uriPath;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
