package jiuri.com.firstapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import jiuri.com.firstapplication.R;

/**
 * Created by user103 on 2017/8/1.
 */

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder>{
    private ArrayList <String> mArrayList;
    public MyAdapter2(ArrayList<String> list) {
        mArrayList=  list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news1111, parent,false);//解决宽度不能铺满
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mTextView.setText(mArrayList.get(position));
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(v,position);
                mArrayList.remove(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView= (TextView) itemView.findViewById(R.id.title);
        }
    }
    private  onItemClick onItemClick;
    public void setOnItemClick(onItemClick itemClick ){
        onItemClick=itemClick;
    }
    public interface  onItemClick{
        void onItemClick(View view, int i);
    }
}