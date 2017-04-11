package tk.danielgong.darkbox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gongzhq on 2017/4/7.
 */

public class AppFragment extends Fragment {
    private List<App> mAppList = new ArrayList<App>();
    private CheckBox mCheckBox;
    private EditText mEditText;
    private Button mAddButton;
    private AppAdapter mAdapter;

    private static final String TAG = "app_fragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app, container, false);

        initApps();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new AppAdapter(mAppList);
        recyclerView.setAdapter(mAdapter);

        mEditText = (EditText) view.findViewById(R.id.apps_title);

        mAddButton = (Button) view.findViewById(R.id.apps_darkbox);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<App> darkAppList = new ArrayList<App>();
                ListDataSaver listDataSaver = new ListDataSaver(getActivity().getApplicationContext(), "darkbox");
                List<Box> darkbox_boxs = listDataSaver.getDataList("darkbox_boxs", Box.class);
                String title = mEditText.getText().toString();
                if (title.length() == 0 || title.equals("darkbox_boxs")) {
                    return;
                }
                for (Box darkbox_box : darkbox_boxs) {
                    if(darkbox_box.getName().equals(title)) {
                        return;
                    }
                }

                for (App app : mAppList) {
                    if (app.getCheck()) {
                        darkAppList.add(app);
                    }
                }
                int colorId = R.color.grey;
                if (darkbox_boxs.size() % 2 != 0) {
                    colorId = R.color.white;
                }
                Box box = new Box(title, R.drawable.apple_pic, colorId);
                darkbox_boxs.add(box);
                listDataSaver.setDataList(title, darkAppList);
                listDataSaver.setDataList("darkbox_boxs", darkbox_boxs);
                replaceFragment(new BoxFragment());
            }
        });
        mCheckBox = (CheckBox) view.findViewById(R.id.apps_check);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (App app : mAppList) {
                    app.setCheck(isChecked);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    private void initApps() {
//        for (int i = 0; i < 2; i++) {
        App apple = new App("Apple", R.drawable.apple_pic, R.color.grey);
        mAppList.add(apple);
        App banana = new App("Banana", R.drawable.banana_pic, R.color.white);
        mAppList.add(banana);
        App orange = new App("Orange", R.drawable.orange_pic, R.color.grey);
        mAppList.add(orange);
        App watermelon = new App("Watermelon", R.drawable.watermelon_pic, R.color.white);
        mAppList.add(watermelon);
        App pear = new App("Pear", R.drawable.pear_pic, R.color.grey);
        mAppList.add(pear);
        App grape = new App("Grape", R.drawable.grape_pic, R.color.white);
        mAppList.add(grape);
        App pineapple = new App("Pineapple", R.drawable.pineapple_pic, R.color.grey);
        mAppList.add(pineapple);
        App strawberry = new App("Strawberry", R.drawable.strawberry_pic, R.color.white);
        mAppList.add(strawberry);
        App cherry = new App("Cherry", R.drawable.cherry_pic, R.color.grey);
        mAppList.add(cherry);
        App mango = new App("Mango", R.drawable.mango_pic, R.color.white);
        mAppList.add(mango);
//        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }
}
