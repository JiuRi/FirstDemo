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
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MyNewsListBean, BaseViewHolder>{

    public MultipleItemQuickAdapter(Context context, List data) {
        super(data);
        addItemType(MyNewsListBean.TYPE_NEW1, R.layout.item_news1);
        addItemType(MyNewsListBean.TYPE_NEW2, R.layout.item_news2);
        addItemType(MyNewsListBean.TYPE_NEW3, R.layout.item_news3);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyNewsListBean item) {

    }

}
