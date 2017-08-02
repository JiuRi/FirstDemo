package jiuri.com.firstapplication.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jiuri.com.firstapplication.R;
import jiuri.com.firstapplication.adapter.GrideViewAdapter;
import jiuri.com.firstapplication.adapter.MultipleItemQuickAdapter;
import jiuri.com.firstapplication.adapter.MyImageLoad;
import jiuri.com.firstapplication.adapter.MyNewsListBean;
import jiuri.com.firstapplication.weiget.FullyGridLayoutManager;


/**
 * Created by user103 on 2017/7/20.
 */

public class OtherFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TO_FROM="from";
    public static final String TO_POSITION="positon";
    private ArrayList<String> title =new ArrayList<>();
    private ArrayList<String> path =new ArrayList<>();
    private View mInflate;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mReflash;
    private MultipleItemQuickAdapter mMyRecycleViewAdapter;
    private List<MyNewsListBean> mMultipleItemData;

    public  static OtherFragment getInstance(String frome,int position){
       Bundle bundle=new Bundle();
        bundle.putString(TO_FROM,frome);
        bundle.putInt(TO_POSITION,position);
        OtherFragment otherFragment = new OtherFragment();
        otherFragment.setArguments(bundle);
        return otherFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflate = inflater.inflate(R.layout.other, container, false);
        return mInflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title.clear();
        path.clear();
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

        View view1 = toIns();
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycle_view);
        mReflash = (SwipeRefreshLayout) view.findViewById(R.id.reflash);
        mReflash.setColorSchemeColors(Color.BLUE,Color.RED,Color.GREEN);
        mReflash.setOnRefreshListener(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMyRecycleViewAdapter = new MultipleItemQuickAdapter(getContext(),getMultipleItemData());
        mMyRecycleViewAdapter.addHeaderView(view1);
        mMyRecycleViewAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mMyRecycleViewAdapter.setLoadMoreView(new CustomLoadMoreView());
        mMyRecycleViewAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        },mRecyclerView);
        mMultipleItemData = getMultipleItemData();
        mRecyclerView.setAdapter(mMyRecycleViewAdapter) ;
      //  helper.attachToRecyclerView(mRecyclerView);

    }
    //为RecycleView绑定触摸事件
    ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            //首先回调的方法 返回int表示是否监听该方向
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {// GridLayoutManager
                // flag如果值是0，相当于这个功能被关闭
                int dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlag = 0;
                // create make
                return makeMovementFlags(dragFlag, swipeFlag);
            } else if (layoutManager instanceof LinearLayoutManager) {// linearLayoutManager
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                int orientation = linearLayoutManager.getOrientation();

                int dragFlag = 0;
                int swipeFlag = 0;

                // 为了方便理解，相当于分为横着的ListView和竖着的ListView
                if (orientation == LinearLayoutManager.HORIZONTAL) {// 如果是横向的布局
                    swipeFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                } else if (orientation == LinearLayoutManager.VERTICAL) {// 如果是竖向的布局，相当于ListView
                    dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    swipeFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                }
                return makeMovementFlags(dragFlag, swipeFlag);
            }
            return 0;

        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //滑动事件
            Collections.swap(mMultipleItemData,viewHolder.getAdapterPosition(),target.getAdapterPosition());
            mMyRecycleViewAdapter.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            return false;
        }


        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //侧滑事件
            Log.d("aaa", "onSwiped: ______________________"+viewHolder.getAdapterPosition());
            if (viewHolder.getAdapterPosition()==0){
                mMyRecycleViewAdapter.remove(viewHolder.getAdapterPosition());
            }
            mMyRecycleViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition()-1);
       /*     mMultipleItemData.remove(viewHolder.getAdapterPosition()-1);
            mMyRecycleViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());*/
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            //是否可拖拽
            return true;
        }
    });
    private View toIns() {
        title.add("【短命8號波】助理台長指依路徑和安全判斷　聽眾不接受解釋　質疑星期日影響少才掛波");
        View insView = View.inflate(getContext(), R.layout.ins_headerview, null);
        RecyclerView recyclerView = (RecyclerView)insView.findViewById(R.id.recycle_view1);
        recyclerView.setLayoutManager(new FullyGridLayoutManager(getContext(),2));
        recyclerView.setAdapter(new GrideViewAdapter(R.layout.item_recycle_header,title));
        return insView;
    }

    private View toMain() {
        View inflate = View.inflate(getContext(), R.layout.main_headerview, null);
        Banner banner = (Banner) inflate.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setBannerTitles(title);
        banner.setImages(path);
        banner.setImageLoader(new MyImageLoad());
        banner.start();
        return inflate;
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

    @Override
    public void onRefresh() {

    }
}
