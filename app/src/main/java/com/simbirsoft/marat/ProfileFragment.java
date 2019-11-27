package com.simbirsoft.marat;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    ImageView mProfileImage;
    Dialog mChangePhotoDialog;
    String mCurrentPhotoPath;
    boolean mIsDelete = false;
    final static int IMAGE_REQUEST = 1;
    Context mContext;

    public interface IPhotoPath {
        public void savePhotoPath(String photoPath);

    }

    public void setPhotoPathListener(IPhotoPath photoPath){
        this.photoPath = photoPath;
    }

    IPhotoPath photoPath;

    public ProfileFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onAttach(@NonNull Activity activity) {
//        super.onAttach(activity);
//        photoPath = (IPhotoPath) activity;
//    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState != null) {
            mCurrentPhotoPath = savedInstanceState.getString("PATH");
            mIsDelete = savedInstanceState.getBoolean("IsDELETE");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Toolbar myToolbar = view.findViewById(R.id.my_toolbar);
        myToolbar.inflateMenu(R.menu.profile_menu);
        mProfileImage = view.findViewById(R.id.profileImageView);

        if (mCurrentPhotoPath != null && !mIsDelete)
            Picasso.get().load(new File(mCurrentPhotoPath)).fit().centerCrop().into(mProfileImage);

        if (mIsDelete)
            mProfileImage.setImageResource(R.drawable.ic_user_icon_default);
        mContext = view.getContext();

        mProfileImage.setOnClickListener(this);

        return view;
    }

    private void dialogClickListener(View view) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.dialogTvTakePhoto:
                        takePictureIntent();
                        mIsDelete = false;
                        mChangePhotoDialog.cancel();
                        break;
                    case R.id.dialogTvDelete:
                        mProfileImage.setImageResource(R.drawable.ic_user_icon_default);
                        mIsDelete = true;
                        mChangePhotoDialog.cancel();
                        break;
                }
            }
        });
    }

    private void takePictureIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(mContext, "Some thing WRONG", Toast.LENGTH_LONG).show();
            }

            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(mContext, "com.simbirsoft.marat.fileprovider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, IMAGE_REQUEST);
            }

        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (IMAGE_REQUEST == requestCode && resultCode == RESULT_OK) {
            setmProfileImage();
        }
    }

    private void setmProfileImage() {
        Picasso.get().load(new File(mCurrentPhotoPath)).fit().centerCrop().into(mProfileImage);
        photoPath.savePhotoPath(mCurrentPhotoPath);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(), "Photo clicked!!", Toast.LENGTH_SHORT).show();

        mChangePhotoDialog = new Dialog(mContext);
        mChangePhotoDialog.setContentView(R.layout.dialog_profile_set_photo);

        TextView tvTakePhoto = mChangePhotoDialog.findViewById(R.id.dialogTvTakePhoto);
        TextView tvDelete = mChangePhotoDialog.findViewById(R.id.dialogTvDelete);
        dialogClickListener(tvTakePhoto);
        dialogClickListener(tvDelete);

        mChangePhotoDialog.show();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("PATH", mCurrentPhotoPath);
        outState.putBoolean("IsDELETE", mIsDelete);

    }

}
