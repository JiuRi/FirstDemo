package jiuri.com.firstapplication.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by acer on 2017/8/1.
 */

public class ChannelBean implements MultiItemEntity {
    public static final  int TYPE_MYCHANNE=1;
    public static final  int TYPE_MYCHANNE_ITEM=2;
    public static final  int TYPE_MYCHANNE_PUSH=3;
    public static final  int TYPE_MYCHANNE_PUSH_ITEM=4;
    private int mItmtype;
    private String body;

    public ChannelBean(int mItmtype, String body) {
        this.mItmtype = mItmtype;
        this.body = body;
    }

    @Override
    public int getItemType() {
        return mItmtype;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
