package tk.danielgong.darkbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tk.danielgong.darkbox.helper.OnStartDragListener;
import tk.danielgong.darkbox.helper.SimpleItemTouchHelperCallback;

/**
 * Created by gongzhq on 2017/4/7.
 */

public class BoxFragment extends Fragment implements OnStartDragListener{
    private ItemTouchHelper mItemTouchHelper;
    private ListDataSaver mListDataSaver;
    private List<Box> mBoxList;
    private static final String TAG = "box_fragment";

    public BoxFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_box, container, false);
        mListDataSaver = new ListDataSaver(getActivity().getApplicationContext(), "darkbox");
        mBoxList = mListDataSaver.getDataList("darkbox_boxs", Box.class);

        Log.d(TAG, "onCreateView: boxlist size " + mBoxList.size());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyler_view);
        BoxAdapter adapter = new BoxAdapter(mBoxList, this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
