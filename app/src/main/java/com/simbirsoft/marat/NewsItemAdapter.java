package com.simbirsoft.marat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.ViewHolder> {

    Context context;
    List<NewsEvent> dataSource;

    public NewsItemAdapter(Context context, List<NewsEvent> dataSource) {
        this.context = context;
        this.dataSource = dataSource;
    }

    public void insertData(List<NewsEvent> insertList) {
        NewsDiffUtilCallback diffUtilCallback = new NewsDiffUtilCallback(dataSource, insertList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);

        dataSource.addAll(insertList);
        diffResult.dispatchUpdatesTo(this);
    }

    public void updateData(List<NewsEvent> newList) {
        NewsDiffUtilCallback diffUtilCallback = new NewsDiffUtilCallback(dataSource, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);
        dataSource.clear();
        dataSource.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setBackground(dataSource.get(position).getPhotoHead());
        holder.headTextView.setText(dataSource.get(position).getTitel());
        holder.bodyTextView.setText(dataSource.get(position).getArticleText());
        holder.dateTextView.setText(dataSource.get(position).getDateText());


    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView headTextView, bodyTextView, dateTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.news_item_iv);
            this.headTextView = itemView.findViewById(R.id.news_item_tv_head);
            this.bodyTextView = itemView.findViewById(R.id.news_item_tv);
            this.dateTextView = itemView.findViewById(R.id.news_item_tv_date);

        }
    }
}
