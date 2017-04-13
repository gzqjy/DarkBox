package tk.danielgong.darkbox;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.Map;

import tk.danielgong.darkbox.helper.ItemTouchHelperAdapter;
import tk.danielgong.darkbox.helper.ItemTouchHelperViewHolder;
import tk.danielgong.darkbox.helper.OnStartDragListener;

/**
 * Created by gongzhq on 2017/4/11.
 */

public class BoxAdapter extends RecyclerView.Adapter<BoxAdapter.ViewHolder>  implements ItemTouchHelperAdapter {
    private static final String TAG = "box_adapter";
    private List<Box> mBoxList;
    private final OnStartDragListener mDragStartListener;
    private ListDataSaver mListDataSaver;
    public static class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        View boxView;
        ImageView boxImage;
        ImageView deleteImage;
        TextView boxName;
        TextView deleteName;
        int backgroundColor = 0;
        Switch switchButton;

        public TextView getDeleteName() {
            return deleteName;
        }

        public ImageView getDeleteImage() {
            return deleteImage;
        }

        public ViewHolder(View view) {
            super(view);
            boxView = view;
            boxImage = (ImageView) view.findViewById(R.id.box_image);
            boxName = (TextView) view.findViewById(R.id.box_name);
            switchButton = (Switch) view.findViewById(R.id.switch_button);
            deleteName = (TextView) view.findViewById(R.id.item_delete_txt);
            deleteImage = (ImageView) view.findViewById(R.id.item_delete_img);
        }
        @Override
        public void onItemSelected() {
            ColorDrawable colorDrawable = (ColorDrawable)itemView.getBackground();
            backgroundColor = colorDrawable.getColor();
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(backgroundColor);
        }
    }

    public BoxAdapter(List<Box> boxList, OnStartDragListener dragStartListener) {
        mDragStartListener = dragStartListener;
        mBoxList = boxList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.box_item, parent, false);
        mListDataSaver = new ListDataSaver(view.getContext(), "darkbox");
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Box box = mBoxList.get(position);
        holder.boxImage.setImageResource(box.getImageId());
        holder.boxName.setText(box.getName());
        holder.boxView.setBackgroundColor(box.getBackground());
        holder.switchButton.setChecked(box.getCheck());
        Log.d(TAG, "onBindViewHolder: " + box.getName());

        // Start a drag whenever the handle view it touched
        holder.boxView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });

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


        holder.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                final int position = holder.getAdapterPosition();
                final Box box = mBoxList.get(position);
                ArrayList<App> ss = box.getApps();
                for (App s : ss) {
                    Log.d(TAG, "onCheckedChanged: apps " + s.getName());
                }
                Log.d(TAG, "onCheckedChanged: size " + ss.size());
            }
        });
    }

    @Override
    public void onItemDismiss(int position) {
        Box box = mBoxList.remove(position);
        mListDataSaver.removeData(box.getName());
        mListDataSaver.setDataList("darkbox_boxs", mBoxList);
        Log.d(TAG, "onItemDismiss: boxlist size " + mBoxList.size());
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mBoxList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public int getItemCount() {
        return mBoxList.size();
    }
}
