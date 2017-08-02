package jiuri.com.firstapplication.ui.fragment.channel;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import jiuri.com.firstapplication.R;
import jiuri.com.firstapplication.bean.ChannelBean;

/**
 * Created by acer on 2017/8/1.
 */

public class AdapterChannelFragment extends BaseMultiItemQuickAdapter<ChannelBean,BaseViewHolder> {
    private boolean isEdit;
    private BaseViewHolder mBaseViewHolder;
    private RecyclerView mRecyclerView;
    private long mStart;
    private ArrayList<ChannelBean> mChannelBeanArrayList;

    public AdapterChannelFragment(Context context, ArrayList<ChannelBean> data) {
        super(data);
        isEdit=false;
        mChannelBeanArrayList=data;
        addItemType(ChannelBean.TYPE_MYCHANNE, R.layout.item_mychannel);
        addItemType(ChannelBean.TYPE_MYCHANNE_ITEM, R.layout.item_itemchannel);
        addItemType(ChannelBean.TYPE_MYCHANNE_PUSH, R.layout.item_chanelpush);
        addItemType(ChannelBean.TYPE_MYCHANNE_PUSH_ITEM, R.layout.item_pushchanel);
    }
    private OnChannelDragListener onChannelDragListener;

    public void setOnChannelDragListener(OnChannelDragListener onChannelDragListener) {
        this.onChannelDragListener = onChannelDragListener;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final ChannelBean item) {
            switch (item.getItemType()){
                case ChannelBean.TYPE_MYCHANNE :
                    mBaseViewHolder=baseViewHolder;
                    baseViewHolder.setText(R.id.tvTitle,item.getBody()).setOnClickListener(R.id.tvEdit, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!isEdit){
                                //邊上的X是否隱藏
                                showOrHitX(true);
                                baseViewHolder.setText(R.id.tvEdit,"完成");
                            }
                            else{
                                //邊上的X是否隱藏
                                baseViewHolder.setText(R.id.tvEdit,"編輯");
                                showOrHitX(false);
                            }
                        }
                    });

                    break;
                case ChannelBean.TYPE_MYCHANNE_ITEM :
                    baseViewHolder.setVisible(R.id.ivDelete,isEdit).
                            setOnLongClickListener(R.id.rlItemView, new View.OnLongClickListener() {
                                @Override
                                public boolean onLongClick(View v) {
                                    if (!isEdit){
                                        showOrHitX(true);
                                        mBaseViewHolder.setText(R.id.tvEdit,"完成");
                                        onChannelDragListener.onStarDrag(baseViewHolder);
                                    }
                                    return true;
                                }
                            }).setOnTouchListener(R.id.rlItemView, new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:

                                    mStart = System.currentTimeMillis();
                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    long end = System.currentTimeMillis();
                                    if (end- mStart >100){
                                        if (onChannelDragListener!=null) {
                                            onChannelDragListener.onStarDrag(baseViewHolder);
                                        }
                                    }
                                    break;
                                case MotionEvent.ACTION_CANCEL:
                                case MotionEvent.ACTION_UP:
                                    mStart=0;
                                    break;
                            }
                            return false;
                        }
                    }).getView(R.id.ivDelete).setTag(true);//在我的频道里面设置true标示，之后会根据这个标示来判断编辑模式是否显示;
                    baseViewHolder.setText(R.id.tvChannel,item.getBody()).setOnClickListener(R.id.ivDelete, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isEdit) {
                                int pushChannelFirstPositoin = getPushChannelFirstPositoin();
                                int currentposition = baseViewHolder.getPosition();
                                //获取到目标View
                                View targetView = mRecyclerView.getLayoutManager().findViewByPosition(pushChannelFirstPositoin);
                                //获取当前需要移动的View
                                View currentView = mRecyclerView.getLayoutManager().findViewByPosition(currentposition);
                                // 如果targetView不在屏幕内,则indexOfChild为-1  此时不需要添加动画,因为此时notifyItemMoved自带一个向目标移动的动画
                                // 如果targetView不在屏幕内,则indexOfChild为-1  此时不需要添加动画,因为此时notifyItemMoved自带一个向目标移动的动画
                                // 如果在屏幕内,则添加一个位移动画
                                if (mRecyclerView.indexOfChild(targetView)>=0&&pushChannelFirstPositoin>-1){
                                    RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
                                    int spanCount = ((GridLayoutManager) manager).getSpanCount();
                                    int targetX = targetView.getLeft();
                                    int targetY = targetView.getTop();
                                    int myChannelSize = getMyChannelSize();
                                    if (myChannelSize % spanCount == 1) {
                                        //我的频道最后一行 之后一个，移动后
                                        targetY -= targetView.getHeight();
                                    }
                                    item.setItmtype(ChannelBean.TYPE_MYCHANNE_PUSH_ITEM);//改为推荐频道类型

                                    if (onChannelDragListener != null)
                                        onChannelDragListener.onMoveToOtherChannel(currentposition, pushChannelFirstPositoin - 1);
                                    startAnimation(currentView, targetX, targetY);
                                } else {
                                    item .setItmtype(ChannelBean.TYPE_MYCHANNE_PUSH_ITEM);//改为推荐频道类型
                                    if (pushChannelFirstPositoin == -1)
                                        pushChannelFirstPositoin = 0;//推薦頻道沒有了，改成0
                                    if (onChannelDragListener != null)
                                        onChannelDragListener.onMoveToOtherChannel(currentposition, mChannelBeanArrayList.size()-1);
                                }
                            }
                        }
                    });


                    break;
                case ChannelBean.TYPE_MYCHANNE_PUSH :
                    baseViewHolder.setText(R.id.tvTitle, item.getBody());
                    break;
                case ChannelBean.TYPE_MYCHANNE_PUSH_ITEM :
                    //频道推荐列表
                    baseViewHolder.setText(R.id.tvChannel, item.getBody())
                            .setOnClickListener(R.id.tvChannel, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int myLastPosition = getMyLastPosition();
                                    int currentPosition =  baseViewHolder.getPosition();
                                    //获取到目标View
                                    View targetView = mRecyclerView.getLayoutManager().findViewByPosition(myLastPosition);
                                    //获取当前需要移动的View
                                    View currentView = mRecyclerView.getLayoutManager().findViewByPosition(currentPosition);
                                    // 如果targetView不在屏幕内,则indexOfChild为-1  此时不需要添加动画,因为此时notifyItemMoved自带一个向目标移动的动画
                                    // 如果在屏幕内,则添加一个位移动画
                                    if (mRecyclerView.indexOfChild(targetView) >= 0 && myLastPosition != -1) {
                                        RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
                                        int spanCount = ((GridLayoutManager) manager).getSpanCount();
                                        int targetX = targetView.getLeft() + targetView.getWidth();
                                        int targetY = targetView.getTop();

                                        int myChannelSize = getMyChannelSize();//这里我是为了偷懒 ，算出来我的频道的大小
                                        if (myChannelSize % spanCount == 0) {
                                            //添加到我的频道后会换行，所以找到倒数第4个的位置

                                            View lastFourthView = mRecyclerView.getLayoutManager().findViewByPosition(getMyLastPosition() - 3);
//                                        View lastFourthView = mRecyclerView.getChildAt(getMyLastPosition() - 3);
                                            targetX = lastFourthView.getLeft();
                                            targetY = lastFourthView.getTop() + lastFourthView.getHeight();
                                        }


                                        // 推荐频道 移动到 我的频道的最后一个
                                        item.setItmtype(ChannelBean.TYPE_MYCHANNE_ITEM);//改为推荐频道类型
                                        if (onChannelDragListener != null)
                                            onChannelDragListener.onMoveToMyChannel(currentPosition, myLastPosition + 1);
                                        startAnimation(currentView, targetX, targetY);
                                    } else {
                                        item.setItmtype(ChannelBean.TYPE_MYCHANNE_ITEM);//改为推荐频道类型
                                        if (myLastPosition == -1) myLastPosition = 0;//我的频道没有了，改成0
                                        if (onChannelDragListener != null)
                                            onChannelDragListener.onMoveToMyChannel(currentPosition, myLastPosition + 1);
                                    }
//                                GlobalParams.mRemovedChannels.remove(channel);

                                }
                            });
                    break;
            }
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        mRecyclerView = (RecyclerView)parent;
        return super.onCreateDefViewHolder(parent, viewType);
    }

    private int getMyLastPosition() {
        for (int i = mData.size() - 1; i > -1; i--) {
            ChannelBean channel = (ChannelBean) mChannelBeanArrayList.get(i);
            if (ChannelBean.TYPE_MYCHANNE_ITEM == channel.getItemType()) {
                //找到第一个直接返回
                return i;
            }
        }
        return -1;
    }

    private TranslateAnimation getTranslateAnimator(float targetX, float targetY) {


        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.ABSOLUTE, targetX,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.ABSOLUTE, targetY);
        // RecyclerView默认移动动画250ms 这里设置360ms 是为了防止在位移动画结束后 remove(view)过早 导致闪烁
        translateAnimation.setDuration(ANIM_TIME);
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }

    private int ANIM_TIME = 360;

    private void startAnimation(final View currentView, int targetX, int targetY) {
        final ViewGroup parent = (ViewGroup) mRecyclerView.getParent();
        final ImageView mirrorView = addMirrorView(parent, currentView);

        TranslateAnimation animator = getTranslateAnimator(targetX - currentView.getLeft(), targetY - currentView.getTop());
        currentView.setVisibility(View.INVISIBLE);//暂时隐藏
        mirrorView.startAnimation(animator);
        animator.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                parent.removeView(mirrorView);//删除添加的镜像View
                if (currentView.getVisibility() == View.INVISIBLE) {
                    currentView.setVisibility(View.VISIBLE);//显示隐藏的View
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private ImageView addMirrorView(ViewGroup parent, View view) {
        view.destroyDrawingCache();
        //首先开启Cache图片 ，然后调用view.getDrawingCache()就可以获取Cache图片
        view.setDrawingCacheEnabled(true);
        ImageView mirrorView = new ImageView(view.getContext());
        //获取该view的Cache图片
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        mirrorView.setImageBitmap(bitmap);
        //销毁掉cache图片
        view.setDrawingCacheEnabled(false);
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);//获取当前View的坐标
        int[] parenLocations = new int[2];
        mRecyclerView.getLocationOnScreen(parenLocations);//获取RecyclerView所在坐标
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(bitmap.getWidth(), bitmap.getHeight());
        params.setMargins(locations[0], locations[1] - parenLocations[1], 0, 0);
        parent.addView(mirrorView, params);//在RecyclerView的Parent添加我们的镜像View，parent要是FrameLayout这样才可以放到那个坐标点
        return mirrorView;
    }

    private  int getPushChannelFirstPositoin(){
        for (int i = 0; i < mChannelBeanArrayList.size(); i++) {
            if (mChannelBeanArrayList.get(i).getItemType()==ChannelBean.TYPE_MYCHANNE_PUSH_ITEM){
                return i;
            }
        }
        return -1;
    }


    public int getMyChannelSize() {
        int size = 0;
        for (int i = 0; i < mChannelBeanArrayList.size(); i++) {
            ChannelBean channel = (ChannelBean) mChannelBeanArrayList.get(i);
            if (channel.getItemType() == ChannelBean.TYPE_MYCHANNE) {
                size++;
            }
        }
        return size;

    }

    private void showOrHitX(boolean b) {
        isEdit=b;
        int childCount = mRecyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = mRecyclerView.getChildAt(i);
            ImageView imgEdit = (ImageView) view.findViewById(R.id.ivDelete);
            if (imgEdit != null) {
                boolean isVis = imgEdit.getTag() == null ? false : (boolean) imgEdit.getTag();
                imgEdit.setVisibility(isVis && b ? View.VISIBLE : View.INVISIBLE);
            }
        }
    }
}
