package jiuri.com.firstapplication.ui.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import jiuri.com.firstapplication.R;


/**
 * Created by user103 on 2017/7/20.
 */

public class SetingActivity extends AppCompatActivity {
    private ImageView mX;
    private Toolbar toolbar;
    private SeekBar seekbarSelf;
    private LinearLayout shareTofrends;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_setting);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        seekbarSelf = (SeekBar) findViewById(R.id.seekbar_self);
        shareTofrends = (LinearLayout) findViewById(R.id.share_tofrends);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        shareTofrends.setOnClickListener(new View.OnClickListener() {
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
        if (item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
