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
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pixelad.AdControl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import jiuri.com.firstapplication.R;
import jiuri.com.firstapplication.adapter.DrawGrideAdapter;
import jiuri.com.firstapplication.bean.MyTextSizeMessage;
import jiuri.com.firstapplication.ui.fragment.SettingFragment;
import jiuri.com.firstapplication.ui.fragment.main.MainFragment;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.pic)
    ImageView mPic;
    @BindView(R.id.toolbartitle)
    TextView mToolbartitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.frame_content)
    FrameLayout mFrameContent;
    @BindView(R.id.crazy_banner)
    AdControl mCrazyBanner;

    @BindView(R.id.user_avatar_view)
    ImageView mUserAvatarView;
    @BindView(R.id.user_name)
    TextView mUserName;
    @BindView(R.id.login)
    LinearLayout mLogin;
    @BindView(R.id.main)
    LinearLayout mMain;
    @BindView(R.id.pns)
    LinearLayout mPns;
    @BindView(R.id.ins)
    LinearLayout mIns;
    @BindView(R.id.set)
    LinearLayout mSet;
    @BindView(R.id.graid2)
    GridView mGraid2;
    @BindView(R.id.drawlayout)
    DrawerLayout mDrawlayout;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private GridView mGridView1;
    private GridView mGridView2;
    private TextView mToolbarTitle;
    private ImageView mImageView;
    private View mDrawbarbar;
    private View mDrawcontain;
    private AdControl adControl;
    private View mStatubar;
    private FrameLayout mFrameLayout;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
    }

    public void initView() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawcontain = (View) findViewById(R.id.drawcontain);
        mToolbarTitle = (TextView) findViewById(R.id.toolbartitle);
        mStatubar = (View) findViewById(R.id.statubar);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            mStatubar.setVisibility(View.GONE);
        }
        mGridView2 = (GridView) findViewById(R.id.graid2);
        mFrameLayout = (FrameLayout) findViewById(R.id.frame_content);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawlayout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mGridView2.setAdapter(new DrawGrideAdapter(2));
        tiaozhuan(findViewById(R.id.ins));
        mCrazyBanner.setSID("4058322222485");
    }

    public void tiaozhuan(View view) {
        switch (view.getId()) {
            case R.id.login:
                mDrawerLayout.closeDrawers();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.mainactivity_enter, R.anim.mainactivity_out);
                break;
            case R.id.main:

                MainFragment mainFragment = new MainFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, mainFragment).commit();
                changeState(View.GONE, "", R.color.main);
                break;
            case R.id.pns:
                SettingFragment settingFragment = new SettingFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("to", R.color.pns);
                settingFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, settingFragment).commit();
                changeState(View.VISIBLE, "每日明報", R.color.pns);
                break;
            case R.id.ins:
                SettingFragment settingFragment1 = new SettingFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putInt("to", R.color.ins);
                settingFragment1.setArguments(bundle1);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, settingFragment1).commit();
                changeState(View.VISIBLE, "即時新聞", R.color.ins);
                break;
            case R.id.set:
                mDrawerLayout.closeDrawers();
                Intent intent1 = new Intent(MainActivity.this, SetingActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.mainactivity_enter, R.anim.mainactivity_out);
                break;
        }
    }

    private void changeState(int visiable, String title, int color) {
        mDrawerLayout.closeDrawers();
        mToolbarTitle.setVisibility(visiable);
        mToolbarTitle.setText(title);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(color));
        mStatubar.setBackgroundColor(getResources().getColor(color));
    }
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void getMessage(MyTextSizeMessage message){
        recreate();
    }
}
