package jiuri.com.firstapplication.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jiuri.com.firstapplication.R;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 * modify by AllenCoder
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public MultipleItemQuickAdapter(Context context, List data) {
        super(data);
        addItemType(MultipleItem.TYPE_NEWS1, R.layout.item_news1);
        addItemType(MultipleItem.TYPE_NEWS2, R.layout.item_news2);
        addItemType(MultipleItem.TYPE_NEWS3, R.layout.item_news3);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {

    }

}
