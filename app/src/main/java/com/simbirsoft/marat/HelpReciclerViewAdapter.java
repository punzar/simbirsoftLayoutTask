package com.simbirsoft.marat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HelpReciclerViewAdapter extends RecyclerView.Adapter<HelpReciclerViewAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<String> mTexts = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>();

    public HelpReciclerViewAdapter(Context mContext, ArrayList<String> mTexts, ArrayList<Integer> mImages) {
        this.mContext = mContext;
        this.mTexts = mTexts;
        this.mImages = mImages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_help_item, parent ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.image.setImageResource(mImages.get(position));
        holder.text.setText(mTexts.get(position));

    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.iv_help_item);
            this.text = itemView.findViewById(R.id.tv_help_item);
        }
    }
}
