package jiuri.com.firstapplication.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import jiuri.com.firstapplication.R;
import jiuri.com.firstapplication.adapter.GrideViewAdapter;
import jiuri.com.firstapplication.adapter.MultipleItemQuickAdapter;
import jiuri.com.firstapplication.adapter.MyImageLoad;
import jiuri.com.firstapplication.adapter.MyNewsListBean;


/**
 * Created by user103 on 2017/7/20.
 */

public class OtherFragment extends Fragment {
    private ArrayList<String> title =new ArrayList<>();
    private ArrayList<String> path =new ArrayList<>();
    private View mInflate;
    private RecyclerView mRecyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflate = inflater.inflate(R.layout.other, container, false);
        return mInflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       title.add("助原居民權益寫進基本法 「新界王」離世 劉皇發享年80");
       title.add("安志杰斷腳筋返港做手術 Jessica C.貼身照顧");
       title.add("建軍90年展 新型導彈曝光 「核常兼備」 射程覆蓋美國大部分目標");
       title.add("聖殿山衝突 阿盟斥以色列玩火 巴人抗議安檢變相侵佔 以方再裝閉路電視");
       title.add("【短命8號波】助理台長指依路徑和安全判斷　聽眾不接受解釋　質疑星期日影響少才掛波");
        path.add(R.mipmap.v+"");
        path.add(R.mipmap.vv+"");
        path.add(R.mipmap.vvv+"");
        path.add(R.mipmap.vvvv+"");
        path.add(R.mipmap.vvvvv+"");
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycle_view);
        View inflate = View.inflate(getContext(), R.layout.main_headerview, null);
        Banner banner = (Banner) inflate.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setBannerTitles(title);
        banner.setImages(path);
        banner.setImageLoader(new MyImageLoad());
        banner.start();
        title.add("【短命8號波】助理台長指依路徑和安全判斷　聽眾不接受解釋　質疑星期日影響少才掛波");
        View view1 = View.inflate(getContext(), R.layout.ins_headerview, null);
        RecyclerView recyclerView = (RecyclerView)view1.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new GrideViewAdapter(R.layout.item_recycle_header,title));
/*        ArrayList<MyBean> list=new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            if (i==1){
                list.add(  new MyBean("a","b",MyBean.TYPE_NEWS1));
                continue;
            }
            if (i==2){
                list.add(  new MyBean("a","b",MyBean.TYPE_NEWS2));

                continue;
            }
            if (i==3){
                list.add(  new MyBean("a","b",MyBean.TYPE_NEWS3));

                continue;
            }
            if (i/3==0){
                list.add(  new MyBean("a","b",MyBean.TYPE_NEWS2));
          }
            if (i/2==0&&i/3==0){
                   list.add(  new MyBean("a","b",MyBean.TYPE_NEWS3));
              }
            else {
                list.add(  new MyBean("a","b",MyBean.TYPE_NEWS1));
            }
        }*/
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MultipleItemQuickAdapter myRecycleViewAdapter = new MultipleItemQuickAdapter(getContext(),getMultipleItemData());
        myRecycleViewAdapter.addHeaderView(view1);
       mRecyclerView.setAdapter(myRecycleViewAdapter) ;
    }
    public static List<MyNewsListBean> getMultipleItemData() {
        List<MyNewsListBean> list = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            list.add(new MyNewsListBean(MyNewsListBean.TYPE_NEW1));
            list.add(new MyNewsListBean(MyNewsListBean.TYPE_NEW2));
            list.add(new MyNewsListBean(MyNewsListBean.TYPE_NEW3));
            list.add(new MyNewsListBean(MyNewsListBean.TYPE_NEW1));
            list.add(new MyNewsListBean(MyNewsListBean.TYPE_NEW3));
        }

        return list;
    }
}
