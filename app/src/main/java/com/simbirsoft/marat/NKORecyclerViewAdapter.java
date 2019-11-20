package com.simbirsoft.marat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NKORecyclerViewAdapter extends RecyclerView.Adapter<NKORecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> texts = new ArrayList<>();


    public NKORecyclerViewAdapter(Context mContext, ArrayList<String> texts) {
        this.mContext = mContext;
        this.texts = texts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_nko_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text.setText(texts.get(position));
    }

    @Override
    public int getItemCount() {
        return texts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.text = itemView.findViewById(R.id.tv_nko_item);
        }
    }

}
