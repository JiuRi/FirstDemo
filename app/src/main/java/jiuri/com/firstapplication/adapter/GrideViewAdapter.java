package jiuri.com.firstapplication.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import jiuri.com.firstapplication.R;

/**
 * Created by user103 on 2017/7/24.
 */

public class GrideViewAdapter extends BaseQuickAdapter{
    private ArrayList<String> title =new ArrayList<>();
    private ArrayList<Integer> path =new ArrayList<>();
    public GrideViewAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
        title.add("助原居民權益寫進基本法 「新界王」離世 劉皇發享年80");
        title.add("安志杰斷腳筋返港做手術 Jessica C.貼身照顧");
        title.add("建軍90年展 新型導彈曝光 「核常兼備」 射程覆蓋美國大部分目標");
        title.add("聖殿山衝突 阿盟斥以色列玩火 巴人抗議安檢變相侵佔 以方再裝閉路電視");
        title.add("【短命8號波】助理台長指依路徑和安全判斷　聽眾不接受解釋　質疑星期日影響少才掛波");
        path.add(R.mipmap.v);
        path.add(R.mipmap.vv);
        path.add(R.mipmap.vvv);
        path.add(R.mipmap.vvvv);
        path.add(R.mipmap.vvvvv);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        int position = helper.getPosition();
        helper.setImageResource(R.id.image,path.get(position%5));
        helper.setText(R.id.text,title.get(position%5));
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

}
