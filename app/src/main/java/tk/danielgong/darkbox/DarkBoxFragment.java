package tk.danielgong.darkbox;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gongzhq on 2017/4/7.
 */

public class DarkBoxFragment extends Fragment {
    protected ViewPager viewPager;
    protected TabLayout tabLayout;
    protected View view;
    protected List<Fragment> fragmentList;
    protected List<String> titleList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        fragmentList.add(new BoxListFragment());
        fragmentList.add(new AppsListFragment());
        titleList.add(getResources().getString(R.string.view_darkbox));
        titleList.add(getResources().getString(R.string.add_darkbox));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_darkbox, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setBackgroundColor(Color.parseColor("#ff3344"));
        //设置tabLayout的属性
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#0ddcff"));//设置tab上文字的颜色，第一个参数表示没有选中状态下的文字颜色，第二个参数表示选中后的文字颜色
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#0ddcff"));//设置tab选中的底部的指示条的颜色

        viewPager = (ViewPager) view.findViewById(R.id.addViewPager);
        //给Fragment1里面的ViewPager添加自定义的适配器。
        viewPager.setAdapter(new FragmentAdapter(getChildFragmentManager(), fragmentList, titleList));
        //然后让TabLayout和ViewPager关联，只需要一句话，简直也是没谁了.
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
