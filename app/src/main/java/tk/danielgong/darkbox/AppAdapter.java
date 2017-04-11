package tk.danielgong.darkbox;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by gongzhq on 2017/4/11.
 */

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {
    private static final String TAG = "app_adapter";
    private List<App> mAppList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        View appView;
        ImageView appImage;
        TextView appName;
        CheckBox appCheck;

        public ViewHolder(View view) {
            super(view);
            appView = view;
            appImage = (ImageView) view.findViewById(R.id.app_image);
            appName = (TextView) view.findViewById(R.id.app_name);
            appCheck = (CheckBox) view.findViewById(R.id.app_check);
        }
    }

    public AppAdapter(List<App> appList) {
        mAppList = appList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.appView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                App app = mAppList.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + app.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.appImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                App app = mAppList.get(position);
                Toast.makeText(v.getContext(), "you clicked image " + app.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.appCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int position = holder.getAdapterPosition();
                App app = mAppList.get(position);
                app.setCheck(isChecked);
                Log.d(TAG, "onCheckedChanged: " + isChecked + app.getCheck());
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        App app = mAppList.get(position);
        holder.appImage.setImageResource(app.getImageId());
        holder.appName.setText(app.getName());
        holder.appView.setBackgroundColor(app.getBackground());
        holder.appCheck.setChecked(app.getCheck());
        Log.d(TAG, "onBindViewHolder: " + app.getCheck());
    }

    @Override
    public int getItemCount() {
        return mAppList.size();
    }
}

