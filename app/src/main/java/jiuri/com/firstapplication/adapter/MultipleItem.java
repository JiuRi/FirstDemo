package jiuri.com.firstapplication.adapter;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by user103 on 2017/7/21.
 */

public class MultipleItem implements MultiItemEntity{
    private String title;
    private String content;
    public static final int TYPE_NEWS1=0;
    public static final int TYPE_NEWS2=1;
    public static final int TYPE_NEWS3=2;
    private int itemType;


    public MultipleItem(String title, String content ,int type) {
        this.title = title;
        this.content = content;
        itemType=type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
