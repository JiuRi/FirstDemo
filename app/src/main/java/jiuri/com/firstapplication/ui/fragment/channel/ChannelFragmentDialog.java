package jiuri.com.firstapplication.ui.fragment.channel;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jiuri.com.firstapplication.R;
import jiuri.com.firstapplication.app.Constants;
import jiuri.com.firstapplication.bean.ChannelBean;

/**
 * Created by acer on 2017/8/1.
 */

public class ChannelFragmentDialog extends DialogFragment implements OnChannelListener, OnChannelDragListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.returnto)
    ImageView mReturnto;
    Unbinder unbinder1;
    private int [] arr1={R.mipmap.rm_pns,R.mipmap.yw_pns,R.mipmap.gw_pns,R.mipmap.jy_pns,R.mipmap.sp_pns,R.mipmap.gd_pns,R.mipmap.zg_pns,R.mipmap.gj_pns,R.mipmap.jj_pns,R.mipmap.ty_pns};
    private int [] arr2={R.mipmap.fk_pns,R.mipmap.yl_pns,R.mipmap.yy_pns,R.mipmap.sdbd_pns};
    //"熱門", "要聞", "港聞", "教育", "社評", "觀點", "中國", "國際", "經濟", "體育", "經濟", "副刊", "娛樂", "英文", "深度報道"
    private ArrayList<ChannelBean> mArralist = new ArrayList<>();
    private AdapterChannelFragment mAdapterChannel;
    private ItemTouchHelper mHelper;

    public static ChannelFragmentDialog instance(ArrayList<String> myChanelList, ArrayList<String> otherChanelList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.KEY_MY_CHANNEL, myChanelList);
        bundle.putSerializable(Constants.KEY_OTHER_CHANNEL, otherChanelList);
        ChannelFragmentDialog channelFragmentDialog = new ChannelFragmentDialog();
        channelFragmentDialog.setArguments(bundle);
        return channelFragmentDialog;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.support.v4.app.DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if (dialog == null) {
            dialog.getWindow().setWindowAnimations(R.style.dialogSlideAnim);
        }
        View inflate = inflater.inflate(R.layout.fragment_chinneldialog, container, false);
        unbinder1 = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setData();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void setData() {
        getAllChannelDate();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        mAdapterChannel = new AdapterChannelFragment(getActivity(), mArralist);
        mAdapterChannel.setOnChannelDragListener(this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mAdapterChannel);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mAdapterChannel.getItemViewType(position);

                return itemViewType == ChannelBean.TYPE_MYCHANNE_ITEM || itemViewType == ChannelBean.TYPE_MYCHANNE_PUSH_ITEM ? 1 : 4;
            }
        });


        MyItemTouchHelperCallBack myItemTouchHelperCallBack = new MyItemTouchHelperCallBack(this);
        mHelper = new ItemTouchHelper(myItemTouchHelperCallBack);
        mHelper.attachToRecyclerView(recyclerView);
    }

    public void getAllChannelDate() {
        Bundle arguments = getArguments();
        ArrayList myChannel = (ArrayList) arguments.getSerializable(Constants.KEY_MY_CHANNEL);
        ArrayList otherChannel = (ArrayList) arguments.getSerializable(Constants.KEY_OTHER_CHANNEL);
        mArralist.add(new ChannelBean(ChannelBean.TYPE_MYCHANNE, "我的频道",0));
        for (int i = 0; i < myChannel.size(); i++) {
            mArralist.add(new ChannelBean(ChannelBean.TYPE_MYCHANNE_ITEM, (String) myChannel.get(i),arr1[i]));
        }
        mArralist.add(new ChannelBean(ChannelBean.TYPE_MYCHANNE_PUSH, "推荐频道",0));
        for (int i = 0; i < otherChannel.size(); i++) {
            mArralist.add(new ChannelBean(ChannelBean.TYPE_MYCHANNE_PUSH_ITEM, (String) otherChannel.get(i),arr2[i]));
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    @Override
    public void onItemMove(int starPos, int endPos) {
        onMove(starPos, endPos);
    }

    private void onMove(int starPos, int endPos) {
        ChannelBean startChannel = mArralist.get(starPos);
        //先删除之前的位置
        mArralist.remove(starPos);
        //添加到现在的位置
        mArralist.add(endPos, startChannel);
        mAdapterChannel.notifyItemMoved(starPos, endPos);
    }

    @Override
    public void onMoveToMyChannel(int starPos, int endPos) {
        onMove(starPos, endPos);
    }

    @Override
    public void onMoveToOtherChannel(int starPos, int endPos) {
        onMove(starPos, endPos);
    }

    @Override
    public void onStarDrag(BaseViewHolder baseViewHolder) {
        mHelper.startDrag(baseViewHolder);
    }

    @OnClick(R.id.returnto)
    public void onViewClicked() {
        dismiss();
    }
}
