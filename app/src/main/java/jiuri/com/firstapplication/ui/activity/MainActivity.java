package jiuri.com.firstapplication.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.pixelad.AdControl;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import jiuri.com.firstapplication.R;
import jiuri.com.firstapplication.adapter.DrawGrideAdapter;
import jiuri.com.firstapplication.ui.fragment.SettingFragment;

public class MainActivity extends AppCompatActivity {
    private NavigationView mNavigationView;
    Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private GridView mGridView1;
    private GridView mGridView2;
    private TextView mToolbarTitle;
    private TextView mDrawTitle;
    private ImageView mImageView;
    private View mDrawbarbar;
    private View mDrawcontain;
    private SettingFragment mSettingFragment;
    private AdControl adControl;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main);
        //添加一句话
        //aaaaaaa
        initView();
        initAD();
    }

    private void initAD() {
        adControl = (AdControl) this.findViewById(R.id.crazy_banner);
/*        adControl.setOnPMAdListener(new AdControl.OnPMAdListener() {

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
        });*/
        adControl.setSID("6030896314445");

    }


    public void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawbarbar = (View) findViewById(R.id.drawbarbar);
        mDrawcontain = (View) findViewById(R.id.drawcontain);
        mImageView = (ImageView) findViewById(R.id.x);
        mToolbarTitle = (TextView) findViewById(R.id.toolbartitle);
        mDrawTitle = (TextView) findViewById(R.id.drawbartitle);
        mGridView1 = (GridView) findViewById(R.id.graid1);
        mGridView2 = (GridView) findViewById(R.id.graid2);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawlayout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,mToolbar, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        getSupportActionBar().setTitle("明报新闻");
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);
        }
        mGridView1.setAdapter(new DrawGrideAdapter(1));
        mGridView2.setAdapter(new DrawGrideAdapter(2));
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();
            }
        });
        mGridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        changeState(View.GONE,"",R.color.main);

                        break;
                    case 1:
                        changeState(View.VISIBLE,"每日明報",R.color.pns);
                        break;
                    case 2:
                        changeState(View.VISIBLE,"即時新聞",R.color.ins);
                        break;
                    case 6:
                        mDrawerLayout.closeDrawers();
                        Intent intent =new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.mainactivity_enter,R.anim.mainactivity_out);
                        break;
                    case 5:
                        mDrawerLayout.closeDrawers();
                        Intent intent2 =new Intent(MainActivity.this,AdActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(R.anim.mainactivity_enter,R.anim.mainactivity_out);
                        break;

                    case 7:
                        mDrawerLayout.closeDrawers();
                        Intent intent1 =new Intent(MainActivity.this,SetingActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(R.anim.mainactivity_enter,R.anim.mainactivity_out);
                        break;
                }
            }
        });
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.main));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(MainActivity.this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.main);
        }

        mSettingFragment = new SettingFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, mSettingFragment).commit();

    }

    private void changeState(int visiable,String title,int color) {
        mDrawerLayout.closeDrawers();
        mDrawTitle.setVisibility(visiable);
        mDrawTitle.setText(title);
        mToolbarTitle.setVisibility(visiable);
        mToolbarTitle.setText(title);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(color));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(color);
        }
        mSettingFragment.setTablayoutColor(color);
        mDrawbarbar.setBackgroundDrawable(getResources().getDrawable(color));
    }

}
