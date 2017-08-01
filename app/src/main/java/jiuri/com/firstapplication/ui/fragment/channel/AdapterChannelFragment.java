package jiuri.com.firstapplication.ui.fragment.channel;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import jiuri.com.firstapplication.R;
import jiuri.com.firstapplication.adapter.MultipleItemQuickAdapter;
import jiuri.com.firstapplication.bean.ChannelBean;

/**
 * Created by acer on 2017/8/1.
 */

public class AdapterChannelFragment extends BaseMultiItemQuickAdapter<ChannelBean,BaseViewHolder> {
    public AdapterChannelFragment(Context context, ArrayList<ChannelBean> data) {
        super(data);
        addItemType(ChannelBean.TYPE_MYCHANNE, R.layout.item_mychannel);
        addItemType(ChannelBean.TYPE_MYCHANNE_ITEM, R.layout.item_itemchannel);
        addItemType(ChannelBean.TYPE_MYCHANNE_PUSH, R.layout.item_chanelpush);
        addItemType(ChannelBean.TYPE_MYCHANNE_PUSH_ITEM, R.layout.item_pushchanel);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChannelBean item) {

    }
}
