package com.simbirsoft.marat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.simbirsoft.marat.interfaces.PhotoSettingsListener;

public class PhotoSettingsDialogFragment extends DialogFragment {

    TextView choosePhoto, takePhoto, deletePhoto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_profile_set_photo, container, false);
        choosePhoto = view.findViewById(R.id.dialogTvChoosePhoto);
        takePhoto = view.findViewById(R.id.dialogTvTakePhoto);
        deletePhoto = view.findViewById(R.id.dialogTvDelete);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
                if (getParentFragment() instanceof PhotoSettingsListener) {
                    PhotoSettingsListener listener = (PhotoSettingsListener) getParentFragment();
                    listener.onItemClick(view.getId());
                    getDialog().dismiss();
                }

            }
        };
        choosePhoto.setOnClickListener(listener);
        takePhoto.setOnClickListener(listener);
        deletePhoto.setOnClickListener(listener);

        return view;
    }
}
