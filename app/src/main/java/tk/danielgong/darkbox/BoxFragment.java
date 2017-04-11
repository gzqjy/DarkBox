package tk.danielgong.darkbox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gongzhq on 2017/4/7.
 */

public class BoxFragment extends Fragment {
    private ListDataSaver mListDataSaver;
    private List<Box> mBoxList;
    private Button mRemoveButton;
    private static final String TAG = "box_fragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_box, container, false);
        mListDataSaver = new ListDataSaver(getActivity().getApplicationContext(), "darkbox");
        mBoxList = mListDataSaver.getDataList("darkbox_boxs", Box.class);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        BoxAdapter adapter = new BoxAdapter(mBoxList);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
