package com.simbirsoft.marat;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class NewsDiffUtilCallback extends DiffUtil.Callback {

    List<NewsEvent> oldList;
    List<NewsEvent> newList;

    public NewsDiffUtilCallback(List<NewsEvent> oldList, List<NewsEvent> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
