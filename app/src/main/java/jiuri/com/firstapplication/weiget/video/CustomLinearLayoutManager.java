package jiuri.com.firstapplication.weiget.video;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by user103 on 2017/8/21.
 * 用来控制recyclerview 的 禁止滑动
 */

public class CustomLinearLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public CustomLinearLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}