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
    private int icon;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public ChannelBean(int mItmtype, String body,int icon) {
        this.mItmtype = mItmtype;
        this.body = body;
        this.icon=icon;
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

    public void setItmtype(int itmtype) {
        mItmtype = itmtype;
    }
}
