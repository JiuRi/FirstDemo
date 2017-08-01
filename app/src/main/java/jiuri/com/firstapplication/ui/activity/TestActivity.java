package jiuri.com.firstapplication.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import jiuri.com.firstapplication.R;
import jiuri.com.firstapplication.adapter.MyAdapter;
import jiuri.com.firstapplication.adapter.MyAdapter2;

/**
 * Created by user103 on 2017/8/1.
 */

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    @BindView(R.id.recycle_view1)
    RecyclerView mRecycleView1;
    @BindView(R.id.re)
    RelativeLayout mRe;
    private MyAdapter mMyAdapter;
    private ArrayList<String> mStrings;
    private ArrayList<String> mStrings2;
    private MyAdapter2 mMyAdapter2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        mStrings = new ArrayList<>();
        mStrings2 = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            mStrings.add("我是上面第" + i + "个");
            mStrings2.add("我是下面第" + i + "个");
        }

        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new GridLayoutManager(this, 4));
        mMyAdapter = new MyAdapter(mStrings);
        mRecycleView.setAdapter(mMyAdapter);
        helper.attachToRecyclerView(mRecycleView);


        mRecycleView1.setLayoutManager(new GridLayoutManager(this, 4));
        mMyAdapter2 = new MyAdapter2(mStrings2);
        mRecycleView1.setAdapter(mMyAdapter2);
        //itemTouchHelper.attachToRecyclerView(mRecycleView1);
        mMyAdapter2.setOnItemClick(new MyAdapter2.onItemClick() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(View view, int i) {
        /*        TextView imageView = new TextView(TestActivity.this);

                imageView.setText("我是上面第0个");
                imageView.setBackgroundColor(getColor(R.color.colorPrimary));
                mRe.addView(imageView);
                int width = view.getWidth();
                int height = view.getHeight();
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width,height);
                params.setMargins(3,479,0,0);
                imageView.setLayoutParams(params);
                TranslateAnimation translateAnimation = new TranslateAnimation(0, -100, 0, 400);
                translateAnimation.setDuration(400);
                translateAnimation.setFillAfter(true);
                imageView.startAnimation(translateAnimation);*/
                mStrings2.remove(i);
                   mMyAdapter2.notifyItemRemoved(i);
                 if(i != mStrings2.size()){      // 这个判断的意义就是如果移除的是最后一个，就不用管它了，= =whatever，老板还不发工资啊啊啊啊啊啊
                      mMyAdapter2. notifyItemRangeChanged(i, mStrings2.size() - i);
                 }
            }
        });

    }

    ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

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
            Collections.swap(mStrings, viewHolder.getAdapterPosition(), target.getAdapterPosition());
            mMyAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //侧滑事件
            mStrings.remove(viewHolder.getAdapterPosition());
            mMyAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }

        @Override
        public boolean isLongPressDragEnabled() {
            //是否可拖拽
            return true;
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return false;
        }
    });


}
