package jiuri.com.firstapplication.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jiuri.com.firstapplication.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 * modify by AllenCoder
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MyNewsListBean, BaseViewHolder>{
    private Context mContext;
    public MultipleItemQuickAdapter(Context context, List data) {
        super(data);
        mContext=context;
        addItemType(MyNewsListBean.TYPE_NEW1, R.layout.item_news1);
        addItemType(MyNewsListBean.TYPE_NEW2, R.layout.item_news2);
        addItemType(MyNewsListBean.TYPE_NEW3, R.layout.item_news3);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyNewsListBean item) {

        SharedPreferences sp = mContext.getSharedPreferences("textsize", MODE_PRIVATE);
        int textsize = sp.getInt("textsize", 18);
        TextView view1 = helper.getView(R.id.text1);
        TextView view2 = helper.getView(R.id.text2);
        view1.setTextSize(textsize+2);
        view2.setTextSize(textsize);
    }

}
