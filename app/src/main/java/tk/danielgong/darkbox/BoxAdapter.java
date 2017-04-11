package tk.danielgong.darkbox;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by gongzhq on 2017/4/11.
 */

public class BoxAdapter extends RecyclerView.Adapter<BoxAdapter.ViewHolder> {
    private static final String TAG = "box_adapter";
    private List<Box> mBoxList;
    private ListDataSaver mListDataSaver;
    static class ViewHolder extends RecyclerView.ViewHolder {
        View boxView;
        ImageView boxImage;
        TextView boxName;
        Button removeButton;

        public ViewHolder(View view) {
            super(view);
            boxView = view;
            boxImage = (ImageView) view.findViewById(R.id.box_image);
            boxName = (TextView) view.findViewById(R.id.box_name);
            removeButton = (Button) view.findViewById(R.id.remove);
        }
    }

    public BoxAdapter(List<Box> boxList) {
        mBoxList = boxList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.box_item, parent, false);
        mListDataSaver = new ListDataSaver(view.getContext(), "darkbox");
        final ViewHolder holder = new ViewHolder(view);
        holder.boxView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Box box = mBoxList.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + box.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.boxImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Box box = mBoxList.get(position);
                Toast.makeText(v.getContext(), "you clicked image " + box.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Box box = mBoxList.get(position);
                Toast.makeText(v.getContext(), "you clicked remove " + box.getName(), Toast.LENGTH_SHORT).show();
                mBoxList.remove(box);
                mListDataSaver.setDataList("darkbox_boxs", mBoxList);
                this.notify();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Box box = mBoxList.get(position);
        holder.boxImage.setImageResource(box.getImageId());
        holder.boxName.setText(box.getName());
        holder.boxView.setBackgroundColor(box.getBackground());
        Log.d(TAG, "onBindViewHolder: " + box.getName());
    }

    @Override
    public int getItemCount() {
        return mBoxList.size();
    }
}
