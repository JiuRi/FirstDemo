package jiuri.com.firstapplication.adapter;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by user103 on 2017/7/24.
 */

public class MyNewsListBean implements MultiItemEntity {
    public  static final int TYPE_NEW1=0;
    public  static final int TYPE_NEW2=1;
    public  static final int TYPE_NEW3=2;
    private int mType;

    public MyNewsListBean(int type) {
        mType = type;
    }

    @Override
    public int getItemType() {
        return mType;
    }

}
