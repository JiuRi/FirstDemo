package jiuri.com.firstapplication.ui.fragment.channel;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by user103 on 2017/8/2.
 */

public class MyItemTouchHelperCallBack extends ItemTouchHelper.Callback {
    private OnChannelDragListener mOnChannelListener;
    public MyItemTouchHelperCallBack(OnChannelDragListener channelListener) {
       this. mOnChannelListener=channelListener;
    }

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
        //viewHolder 是指正在移动的viewholder   而 target 是指移动到 这个对象的上面的 本身对象
        // 不同Type之间不可移动
            if (viewHolder.getItemViewType()!=target.getItemViewType()){
                return false;
            }
            if (mOnChannelListener!=null)
                mOnChannelListener.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition()); //获取起始值  和最终所移动到位置.
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //侧滑事件

    }

    @Override
    public boolean isLongPressDragEnabled() {
        //不需要长按拖动，因为我们的标题和 频道推荐 是不需要拖动的，所以手动控制
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        //是否可滑动
        return false;
    }

}
