package com.simbirsoft.marat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FilterItemAdapter extends RecyclerView.Adapter<FilterItemAdapter.ViewHolder> {

    Context context;
    ArrayList<HelpCategory> categoriesList;
    private OnCheckedChangeListener onItemCheckedListener;

    public FilterItemAdapter(Context context, ArrayList<HelpCategory> categoriesList) {
        this.context = context;
        this.categoriesList = categoriesList;
    }


    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        onItemCheckedListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_filter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        HelpCategory category = categoriesList.get(position);
        holder.tvCategory.setText(category.getName());
        holder.filterSwitch.setChecked(category.isState());
        holder.filterSwitch.setTag(position);
        holder.filterSwitch.setOnCheckedChangeListener(mOnCheckedChangeListener);

    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        Switch filterSwitch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.filter_item_tv);
            filterSwitch = itemView.findViewById(R.id.filter_item_switch);

        }


    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(int position, boolean isChecked);
    }

    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int position = (int) buttonView.getTag();

            if (onItemCheckedListener != null) {
                onItemCheckedListener.onCheckedChanged(position, isChecked);
            }
        }
    };


}
