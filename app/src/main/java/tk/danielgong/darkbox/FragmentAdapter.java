package tk.danielgong.darkbox;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by gongzhq on 2017/4/7.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> titleList;//用来存储每个Fragment的title

    public FragmentAdapter(FragmentManager fm, List<Fragment> list, List<String> titleList) {
        super(fm);
        this.list = list;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    //返回标题
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
