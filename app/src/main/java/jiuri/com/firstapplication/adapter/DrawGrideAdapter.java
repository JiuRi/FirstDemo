package jiuri.com.firstapplication.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import jiuri.com.firstapplication.R;

/**
 * Created by user103 on 2017/7/20.
 */

public class DrawGrideAdapter extends BaseAdapter {
    private int [] arr1={R.mipmap.main,R.mipmap.pns,R.mipmap.ins,1,1,R.mipmap.my,R.mipmap.login,R.mipmap.set};
    private  String [] string1={"主頁","每日明報","即時新聞","","","我的頻道","登陸","設定"};
    private int [] arr2={R.mipmap.hp,R.mipmap.mp,R.mipmap.mt,R.mipmap.pt,R.mipmap.ma,R.mipmap.indie,R.mipmap.jump,R.mipmap.mdl,R.mipmap.cmjq};
    private int [] arrAll;
    private int mA;

    public DrawGrideAdapter(int a) {
        mA=a;
      if (a==1){
          arrAll=arr1;
      }
      else {
          arrAll=arr2;
      }
    }

    @Override
    public int getCount() {
            return arrAll.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mA==1) {
            convertView = View.inflate(parent.getContext(), R.layout.item_drawgrid1, null);
            ImageView ima = (ImageView) convertView.findViewById(R.id.icon);
            TextView text = (TextView) convertView.findViewById(R.id.title);
            if (position==3||position==4){

            }
            else {
                ima.setImageResource(arrAll[position]);
                text.setText(string1[position]);
            }
            return convertView;
        }
        else {
            convertView = View.inflate(parent.getContext(), R.layout.item_drawgrid2, null);
            ImageView ima = (ImageView) convertView.findViewById(R.id.icon);
            ima.setImageResource(arrAll[position]);
            return convertView;
        }

    }
}
