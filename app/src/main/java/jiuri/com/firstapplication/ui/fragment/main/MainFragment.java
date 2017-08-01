package jiuri.com.firstapplication.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

import jiuri.com.firstapplication.R;
import jiuri.com.firstapplication.adapter.MultipleItemQuickAdapter;
import jiuri.com.firstapplication.adapter.MyImageLoad;
import jiuri.com.firstapplication.ui.activity.NewsDetailActivity;

import static jiuri.com.firstapplication.ui.fragment.OtherFragment.getMultipleItemData;

/**
 * Created by user103 on 2017/7/25.
 */

public class MainFragment extends Fragment {
    private ArrayList<String> title =new ArrayList<>();
    private ArrayList<String> path =new ArrayList<>();
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,container,false);
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
        View view1 = toMain();
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MultipleItemQuickAdapter myRecycleViewAdapter = new MultipleItemQuickAdapter(getContext(),getMultipleItemData());
        myRecycleViewAdapter.addHeaderView(view1);
        myRecycleViewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent1=new Intent(getActivity(), NewsDetailActivity.class);
                startActivity(intent1);
            }
        });
        mRecyclerView.setAdapter(myRecycleViewAdapter) ;
    }
    private View toMain() {
        View inflate = View.inflate(getContext(), R.layout.main_headerview, null);
        Banner banner = (Banner) inflate.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setBannerTitles(title);
        banner.setImages(path);
        banner.setImageLoader(new MyImageLoad());
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getActivity(), NewsDetailActivity.class);
                startActivity(intent1);
            }
        });
        banner.start();

        return inflate;
    }
}
