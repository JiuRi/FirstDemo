package jiuri.com.firstapplication.adapter;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MyBean implements MultiItemEntity {
    public static final int TEXT = 1;
    public static final int IMG = 2;
    public static final int IMG_TEXT = 3;
    public static final int TEXT_SPAN_SIZE = 3;
    public static final int IMG_SPAN_SIZE = 1;
    public static final int IMG_TEXT_SPAN_SIZE = 4;
    public static final int IMG_TEXT_SPAN_SIZE_MIN = 2;
    private int itemType;
    private int spanSize;
    private String name;
    private String num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public MyBean(int itemType, int spanSize, String content) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.content = content;
    }

    public MyBean(int itemType) {
        this.itemType = itemType;

    }

    public MyBean(int itemType, String name, String num) {
        this.itemType = itemType;
        this.name = name;
        this.num = num;
    }

    private String content;

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
