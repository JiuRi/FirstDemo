package jiuri.com.firstapplication.weiget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.HashMap;

import jiuri.com.firstapplication.R;

/**
 * Created by user103 on 2017/7/27.
 */

public class SharePopwindow extends PopupWindow {
    private String [] title={"FaceBook","Linkedin","Line","WeChat","WhatsApp","Email","Google+","Twitter"};
    private View mPopView;
    private Context mcoteext;
    private ArrayList<String> mList=new ArrayList<>();
    public SharePopwindow(Context context) {
        super(context);
        mcoteext=context;
        for (int i = 0; i < title.length; i++) {
            mList.add(title[i]);
        }
        init(context);
        setPopupWindow();
    }

    private void setPopupWindow() {
        //設置相關的屬性
        this.setContentView(mPopView);// 设置View
        this.setWidth(LinearLayoutCompat.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(700);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
        this.setAnimationStyle(R.style.mypopwindow_anim_style);// 设置动画
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));// 设置背景透明
        this.setOutsideTouchable(true);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //绑定布局
        mPopView = inflater.inflate(R.layout.custom_popup_window
                , null);
       RecyclerView mRectcle = (RecyclerView) mPopView.findViewById(R.id.recycle_view);
        mRectcle.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);
        mRectcle.setLayoutManager(gridLayoutManager);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION,80);//底部间距
        mRectcle.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));
        mRectcle.setAdapter(new SharePopAdapter(R.layout.item_share,mList));
    }
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mcoteext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) mcoteext).getWindow().setAttributes(lp);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        setBackgroundAlpha(0.5f);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        setBackgroundAlpha(1f);
    }
}
