package jiuri.com.firstapplication.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jiuri.com.firstapplication.R;

/**
 * Created by user103 on 2017/7/21.
 */

public class MyRecycleViewAdapter extends BaseMultiItemQuickAdapter{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MyRecycleViewAdapter(Context context,List data) {
        super(data);
        addItemType(MultipleItem.TYPE_NEWS1, R.layout.item_news1);
        addItemType(MultipleItem.TYPE_NEWS2, R.layout.item_news2);
        addItemType(MultipleItem.TYPE_NEWS3, R.layout.item_news3);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TYPE_NEWS1:

            case MultipleItem.TYPE_NEWS2:

            case MultipleItem.TYPE_NEWS3:
        }
    }

}
