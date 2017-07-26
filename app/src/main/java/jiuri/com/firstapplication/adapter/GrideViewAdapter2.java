package jiuri.com.firstapplication.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user103 on 2017/7/24.
 */

public class GrideViewAdapter2 extends BaseQuickAdapter<String,BaseViewHolder>{
    private ArrayList<String> title =new ArrayList<>();
    private ArrayList<Integer> path =new ArrayList<>();
    public GrideViewAdapter2(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);

    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
