package jiuri.com.firstapplication.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import jiuri.com.firstapplication.R;
import jiuri.com.firstapplication.adapter.GrideViewAdapter2;
import jiuri.com.firstapplication.adapter.MultipleItemQuickAdapter;
import jiuri.com.firstapplication.adapter.MyNewsListBean;
import jiuri.com.firstapplication.adapter.MyPagerAdapter;
import jiuri.com.firstapplication.weiget.FullyLinearLayoutManager;
import jiuri.com.firstapplication.weiget.SharePopwindow;

/**
 * Created by user103 on 2017/7/25.
 */

public class NewsDetailActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<String> title =new ArrayList<>();
    private int [] arr={R.mipmap.vo,R.mipmap.v,R.mipmap.vv,R.mipmap.vvvv,R.mipmap.vvvv,R.mipmap.vvvvv};
    private LinearLayout mHorizontalScrollView;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_newsdetail);
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
                        Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
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
        recyclerView.setAdapter(new GrideViewAdapter2(R.layout.foot_newsdetail,title));
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(viewPager);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setCurrentItem(0);
        for (int i = 0; i <6 ; i++) {
            final ImageView imageView=new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(5, 5, 5, 5);

            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(finalI);
                }
            });
            imageView.setLayoutParams(new ViewGroup.LayoutParams(400,200));
            imageView.setImageResource(arr[i]);
            mHorizontalScrollView.addView(imageView);
        }
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MultipleItemQuickAdapter myRecycleViewAdapter = new MultipleItemQuickAdapter(this,getMultipleItemData());
        myRecycleViewAdapter.addHeaderView(inflate);
        myRecycleViewAdapter.addFooterView(inflate2);
        mRecyclerView.setAdapter(myRecycleViewAdapter) ;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
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
}
