package com.simbirsoft.marat;

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
import android.widget.Toast;

import com.simbirsoft.marat.interfaces.PhotoSettingsListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener, PhotoSettingsListener {
    ImageView mProfileImage;
    String mCurrentPhotoPath;
    final static int TAKE_IMAGE_REQUEST = 1;
    final static int CHOOSE_IMAGE_REQUEST = 2;
    final static String TAG = "PROFILE";

    public ProfileFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Toolbar myToolbar = view.findViewById(R.id.my_toolbar);
        myToolbar.inflateMenu(R.menu.profile_menu);
        mProfileImage = view.findViewById(R.id.profileImageView);

        if (savedInstanceState != null && savedInstanceState.getString("PATH") != null) {
            mCurrentPhotoPath = savedInstanceState.getString("PATH");
            Picasso.get().load(Uri.parse(mCurrentPhotoPath)).fit().centerCrop().into(mProfileImage);
        }

        mProfileImage.setOnClickListener(this);

        return view;
    }

    private void takePictureIntent(int id) {
        switch (id) {
            case R.id.dialogTvTakePhoto:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    File photoFile = null;

                    try {
                        photoFile = createImageFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Some thing WRONG", Toast.LENGTH_LONG).show();
                    }

                    if (photoFile != null) {
                        Uri photoUri = FileProvider.getUriForFile(getActivity(), "com.simbirsoft.marat.fileprovider", photoFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        mCurrentPhotoPath = photoUri.toString();
                        startActivityForResult(intent, TAKE_IMAGE_REQUEST);
                    }

                }
                break;
            case R.id.dialogTvChoosePhoto:
                Intent choosePhotoIntent = new Intent(Intent.ACTION_PICK);
                choosePhotoIntent.setType("image/*");
                startActivityForResult(choosePhotoIntent, CHOOSE_IMAGE_REQUEST);
                break;
        }

    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_IMAGE_REQUEST:
                if (resultCode == RESULT_OK)
                    Picasso.get().load(Uri.parse(mCurrentPhotoPath)).fit().centerCrop().into(mProfileImage);
                break;
            case CHOOSE_IMAGE_REQUEST:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    mCurrentPhotoPath = selectedImage.toString();
                    Picasso.get().load(Uri.parse(mCurrentPhotoPath)).fit().centerCrop().into(mProfileImage);
                    break;
                }
        }
    }


    @Override
    public void onClick(View view) {
        PhotoSettingsDialogFragment dialogFragment = new PhotoSettingsDialogFragment();
        dialogFragment.show(getChildFragmentManager(), "DIALOG");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("PATH", mCurrentPhotoPath);
    }

    @Override
    public void onItemClick(int id) {
        switch (id) {
            case R.id.dialogTvChoosePhoto:
                takePictureIntent(R.id.dialogTvChoosePhoto);
                break;
            case R.id.dialogTvTakePhoto:
                takePictureIntent(R.id.dialogTvTakePhoto);
                break;
            case R.id.dialogTvDelete:
                Uri uri = Uri.parse("android.resource://com.simbirsoft.marat/" + R.drawable.ic_user_icon_default);
                mCurrentPhotoPath = uri.toString();
                Picasso.get().load(Uri.parse(mCurrentPhotoPath)).fit().centerCrop().into(mProfileImage);
                break;
        }
    }
}
