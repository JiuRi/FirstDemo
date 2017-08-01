package jiuri.com.firstapplication.ui.fragment.channel;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jiuri.com.firstapplication.R;
import jiuri.com.firstapplication.app.Constants;
import jiuri.com.firstapplication.bean.ChannelBean;

/**
 * Created by acer on 2017/8/1.
 */

public class ChannelFragmentDialog extends DialogFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private ArrayList<ChannelBean> mArralist = new ArrayList<>();
    private AdapterChannelFragment mAdapterChannel;

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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        mAdapterChannel = new AdapterChannelFragment(getContext(), mArralist);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mAdapterChannel);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mAdapterChannel.getItemViewType(position);

                return itemViewType==ChannelBean.TYPE_MYCHANNE_ITEM||itemViewType==ChannelBean.TYPE_MYCHANNE_PUSH_ITEM?1:4;
            }
        });
    }

    public void getAllChannelDate() {
        Bundle arguments = getArguments();
        ArrayList myChannel = (ArrayList) arguments.getSerializable(Constants.KEY_MY_CHANNEL);
        ArrayList otherChannel = (ArrayList) arguments.getSerializable(Constants.KEY_OTHER_CHANNEL);
        mArralist.add(new ChannelBean(ChannelBean.TYPE_MYCHANNE, "我的频道"));
        for (int i = 0; i < myChannel.size(); i++) {
            mArralist.add(new ChannelBean(ChannelBean.TYPE_MYCHANNE_ITEM, (String) myChannel.get(i)));
        }
        mArralist.add(new ChannelBean(ChannelBean.TYPE_MYCHANNE_PUSH, "推荐频道"));
        for (int i = 0; i < otherChannel.size(); i++) {
            mArralist.add(new ChannelBean(ChannelBean.TYPE_MYCHANNE_PUSH_ITEM, (String) otherChannel.get(i)));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
