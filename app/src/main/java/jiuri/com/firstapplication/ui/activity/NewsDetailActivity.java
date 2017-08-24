package jiuri.com.firstapplication.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiuri.com.firstapplication.R;
import jiuri.com.firstapplication.adapter.GrideViewAdapter2;
import jiuri.com.firstapplication.adapter.MultipleItemQuickAdapter;
import jiuri.com.firstapplication.adapter.MyNewsListBean;
import jiuri.com.firstapplication.adapter.MyPagerAdapter;
import jiuri.com.firstapplication.weiget.FastBlur;
import jiuri.com.firstapplication.weiget.FullyLinearLayoutManager;
import jiuri.com.firstapplication.weiget.SharePopwindow;
import jiuri.com.firstapplication.weiget.video.CustomLinearLayoutManager;

/**
 * Created by user103 on 2017/7/25.
 */

public class NewsDetailActivity extends AppCompatActivity {
    @BindView(R.id.linear)
    RelativeLayout mLinear;
    @BindView(R.id.bg)
    RelativeLayout mBg;
    @BindView(R.id.goto_logoing)
    RelativeLayout mGotoLogoing;
    @BindView(R.id.goto_register)
    TextView mGotoRegister;
    @BindView(R.id.related_news)
    TextView mRelatedNews;
    private boolean ifFirst = true;
    private RecyclerView mRecyclerView;
    private ArrayList<String> title = new ArrayList<>();
    private int[] arr = {R.mipmap.vo, R.mipmap.v, R.mipmap.vv, R.mipmap.vvvv, R.mipmap.vvvv, R.mipmap.vvvvv};
    private LinearLayout mHorizontalScrollView;
    private Toolbar toolbar;
    private CustomLinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_newsdetail);
        ButterKnife.bind(this);
        initView();


    }

    private void initView() {
        title.add("助原居民權益寫進基本法 「新界王」離世 劉皇發享年80");
        title.add("安志杰斷腳筋返港做手術 Jessica C.貼身照顧");
        title.add("建軍90年展 新型導彈曝光 「核常兼備」 射程覆蓋美國大部分目標");
        title.add("聖殿山衝突 阿盟斥以色列玩火 巴人抗議安檢變相侵佔 以方再裝閉路電視");
        title.add("【短命8號波】助理台長指依路徑和安全判斷　聽眾不接受解釋　質疑星期日影響少才掛波");
        title.add("【短命8號波】助理台長指依路徑和安全判斷　聽眾不接受解釋　質疑星期日影響少才掛波");
        title.add("【短命8號波】助理台長指依路徑和安全判斷　聽眾不接受解釋　質疑星期日影響少才掛波");
        title.add("【短命8號波】助理台長指依路徑和安全判斷　聽眾不接受解釋　質疑星期日影響少才掛波");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        View view = findViewById(R.id.x);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePopwindow sharePopwindow = new SharePopwindow(NewsDetailActivity.this);
                sharePopwindow.showAtLocation(NewsDetailActivity.this.findViewById(R.id.recycle_view),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        View inflate = View.inflate(this, R.layout.header_newsdetail, null);
        View inflate2 = View.inflate(this, R.layout.foot_newsdetaila, null);
        final ViewPager viewPager = (ViewPager) inflate.findViewById(R.id.banner);
        mHorizontalScrollView = (LinearLayout) inflate.findViewById(R.id.linear);
        RecyclerView recyclerView = (RecyclerView) inflate2.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        recyclerView.setAdapter(new GrideViewAdapter2(R.layout.foot_newsdetail, title));
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(viewPager);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setCurrentItem(0);
        for (int i = 0; i < 6; i++) {
            final ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(5, 5, 5, 5);

            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(finalI);
                }
            });
            imageView.setLayoutParams(new ViewGroup.LayoutParams(400, 200));
            imageView.setImageResource(arr[i]);
            mHorizontalScrollView.addView(imageView);
        }
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new CustomLinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        MultipleItemQuickAdapter myRecycleViewAdapter = new MultipleItemQuickAdapter(this, getMultipleItemData());
        myRecycleViewAdapter.addHeaderView(inflate);
        myRecycleViewAdapter.addFooterView(inflate2);
        mRecyclerView.setAdapter(myRecycleViewAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (ifFirst) {
                    mLinear.setVisibility(View.VISIBLE);
                    mLayoutManager.setScrollEnabled(false);
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        mLinear.post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void run() {

                mBg.destroyDrawingCache();
                //首先开启Cache图片 ，然后调用view.getDrawingCache()就可以获取Cache图片
                mBg.setDrawingCacheEnabled(true);

                //获取该view的Cache图片
                Bitmap bitmap = Bitmap.createBitmap(mBg.getDrawingCache());
                int width = bitmap.getWidth();
                int height = mLinear.getHeight();
                float y = mLinear.getY();
                Bitmap zero = Bitmap.createBitmap(bitmap, 0, (int) y, width, height);

                //  blur(zero, mLinear, 20);
                Bitmap blur = blur(zero, 20);
                Drawable drawable = new BitmapDrawable(blur);
                mLinear.setBackground(drawable);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static List<MyNewsListBean> getMultipleItemData() {
        List<MyNewsListBean> list = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            list.add(new MyNewsListBean(MyNewsListBean.TYPE_NEW1));
            list.add(new MyNewsListBean(MyNewsListBean.TYPE_NEW2));
            list.add(new MyNewsListBean(MyNewsListBean.TYPE_NEW3));
            list.add(new MyNewsListBean(MyNewsListBean.TYPE_NEW1));
        }
        return list;
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    private void blur(Bitmap bkg, View view) {
        float scaleFactor = 8;
        float radius = 3;
        Bitmap overlay = Bitmap.createBitmap((int) (view.getMeasuredWidth() / scaleFactor),
                (int) (view.getMeasuredHeight() / scaleFactor), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.translate(-view.getLeft() / scaleFactor, -view.getTop() / scaleFactor);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bkg, 0, 0, paint);
        //然后通过FastBlur.doBlur() 实现处理
        overlay = FastBlur.doBlur(overlay, (int) radius, true);
        view.setBackground(new BitmapDrawable(getResources(), overlay));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void blur(Bitmap bkg, View view, float radius) {
        Bitmap overlay = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.drawBitmap(bkg, -view.getLeft(), -view.getTop(), null);
        RenderScript rs = RenderScript.create(this);
        Allocation overlayAlloc = Allocation.createFromBitmap(rs, overlay);
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, overlayAlloc.getElement());
        blur.setInput(overlayAlloc);
        blur.setRadius(radius);
        blur.forEach(overlayAlloc);
        overlayAlloc.copyTo(overlay);
        view.setBackground(new BitmapDrawable(getResources(), overlay));
        rs.destroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private Bitmap blur(Bitmap bitmap, float radius) {
        Bitmap output = Bitmap.createBitmap(bitmap); // 创建输出图片
        RenderScript rs = RenderScript.create(this); // 构建一个RenderScript对象
        ScriptIntrinsicBlur gaussianBlue = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs)); // 创建高斯模糊脚本
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap); // 创建用于输入的脚本类型
        Allocation allOut = Allocation.createFromBitmap(rs, output); // 创建用于输出的脚本类型
        gaussianBlue.setRadius(radius); // 设置模糊半径，范围0f<radius<=25f
        gaussianBlue.setInput(allIn); // 设置输入脚本类型
        gaussianBlue.forEach(allOut); // 执行高斯模糊算法，并将结果填入输出脚本类型中
        allOut.copyTo(output); // 将输出内存编码为Bitmap，图片大小必须注意
        rs.destroy(); // 关闭RenderScript对象，API>=23则使用rs.releaseAllContexts()
        return output;
    }

    @OnClick({R.id.goto_logoing, R.id.goto_register, R.id.related_news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goto_logoing:
                Intent intent =new Intent(this ,LoginActivity.class);
                intent.putExtra("from","newsdetail");
                startActivityForResult(intent,100);
                break;
            case R.id.goto_register:

                break;
            case R.id.related_news:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case  100:
                if (resultCode==1){
                    mLinear.setVisibility(View.GONE);
                    mLayoutManager.setScrollEnabled(true);
                    ifFirst=false;
                }
        }
    }
}
