package jiuri.com.firstapplication.ui.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jiuri.com.firstapplication.R;
import jiuri.com.firstapplication.ui.fragment.channel.ChannelFragmentDialog;


/**
 * Created by user103 on 2017/7/19.
 */

public class SettingFragment extends Fragment {
    @BindView(R.id.add)
    TextView mAdd;
    Unbinder unbinder;
    private String[] pns = {"熱門", "要聞", "港聞", "教育", "社評", "觀點", "中國", "國際", "經濟", "體育", "副刊", "娛樂", "英文", "深度報道"};
    private String[] pns1 = {"熱門", "要聞", "港聞", "教育", "社評", "觀點", "中國", "國際", "經濟", "體育"};
    private String[] pns2 = {"副刊", "娛樂", "英文", "深度報道"};
    private String[] ins = {"熱門", "港聞", "經濟", "地產", "兩岸", "國際", "體育", "娛樂", "文摘"};
    private String[] mArr;
    private RecyclerView list;
    private View mSettingFragment;
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private LinearLayout mMLinearLayou;
    private MyPagerAdapter mMyPagerAdapter;
    private int mColor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mSettingFragment = inflater.inflate(R.layout.fragment_cs, container, false);
        unbinder = ButterKnife.bind(this, mSettingFragment);
        return mSettingFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int to = (int) getArguments().get("to");
        mViewPager = (ViewPager) mSettingFragment.findViewById(R.id.viewpager);
        mMLinearLayou = (LinearLayout) mSettingFragment.findViewById(R.id.contant_bar);
        mTablayout = (TabLayout) mSettingFragment.findViewById(R.id.tab_layout);
        mMyPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        mArr = pns;
        for (int i = 0; i < mArr.length; i++) {
            mMyPagerAdapter.addFragment(OtherFragment.getInstance("mian", 1), mArr[i]);
            mTablayout.addTab(mTablayout.newTab().setText("1"));
        }
        mViewPager.setAdapter(mMyPagerAdapter);
        mTablayout.setupWithViewPager(mViewPager);

        setTablayoutColor(to);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.add)
    public void onViewClicked() {
        ArrayList<String> arrayList1=new ArrayList<>();
        ArrayList<String> arrayList2=new ArrayList<>();
        for (int i = 0; i < pns1.length; i++) {
            arrayList1.add(pns1[i]);
        }
        for (int i = 0; i < pns2.length; i++) {
            arrayList2.add(pns2[i]);
        }
        ChannelFragmentDialog instance = ChannelFragmentDialog.instance(arrayList1, arrayList2);
        instance.show(getActivity().getFragmentManager(), "CHANNEL");
    }

    public static class MyPagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> mFragments = new ArrayList<>();
        private List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        private List<Fragment> getFragments() {
            return mFragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setTablayoutColor(int color) {
        mMLinearLayou.setBackground(getResources().getDrawable(color));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
