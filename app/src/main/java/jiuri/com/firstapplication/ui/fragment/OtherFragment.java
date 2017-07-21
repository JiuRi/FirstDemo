package jiuri.com.firstapplication.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import jiuri.com.firstapplication.R;
import jiuri.com.firstapplication.adapter.MultipleItem;
import jiuri.com.firstapplication.adapter.MyRecycleViewAdapter;


/**
 * Created by user103 on 2017/7/20.
 */

public class OtherFragment extends Fragment {

    private View mInflate;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflate = inflater.inflate(R.layout.other, container, false);
        return mInflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycle_view);
        ArrayList<MultipleItem> list=new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            if (i==1){
                list.add(  new MultipleItem("a","b",MultipleItem.TYPE_NEWS1));
                continue;
            }
            if (i==2){
                list.add(  new MultipleItem("a","b",MultipleItem.TYPE_NEWS2));

                continue;
            }
            if (i==3){
                list.add(  new MultipleItem("a","b",MultipleItem.TYPE_NEWS3));

                continue;
            }
            if (i/3==0){
                list.add(  new MultipleItem("a","b",MultipleItem.TYPE_NEWS2));
          }
            if (i/2==0&&i/3==0){
                   list.add(  new MultipleItem("a","b",MultipleItem.TYPE_NEWS3));
              }
            else {
                list.add(  new MultipleItem("a","b",MultipleItem.TYPE_NEWS1));
            }
        }
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       MyRecycleViewAdapter myRecycleViewAdapter = new MyRecycleViewAdapter(getContext(),list);
       mRecyclerView.setAdapter(new MyAdapter()) ;
    }
    class  MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(View.inflate(parent.getContext(),R.layout.item_news3,null));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 100;
        }
        class MyViewHolder extends RecyclerView.ViewHolder {
            public MyViewHolder(View itemView) {
                super(itemView);

            }
        }
    }
}
