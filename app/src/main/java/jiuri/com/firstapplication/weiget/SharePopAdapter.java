package jiuri.com.firstapplication.weiget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jiuri.com.firstapplication.R;

/**
 * Created by user103 on 2017/7/24.
 */

public class SharePopAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    private String [] title={"FaceBook","Linkedin","Line","WeChat","WhatsApp","Email","Google+","Twitter"};
    private int [] icon={R.mipmap.facebook,R.mipmap.likkedin,R.mipmap.line,R.mipmap.wechat,R.mipmap.whats,R.mipmap.email,R.mipmap.google,R.mipmap.tuite};
    public SharePopAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);

    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int position = helper.getPosition();
        helper.setImageResource(R.id.icon,icon[position]);
        helper.setText(R.id.title,title[position]);
    }
}
