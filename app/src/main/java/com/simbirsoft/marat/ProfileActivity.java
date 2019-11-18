package com.simbirsoft.marat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileActivity extends AppCompatActivity {
    ImageView mProfileImage;
    Dialog mChangePhotoDialog;
    String mCurrentPhotoPath;
    final static int IMAGE_REQUEST = 1;
//todo реализовать сохранение состаяния
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(getBottomNavigationListener());

        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_profile);

        mProfileImage = findViewById(R.id.profileImageView);
        mProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChangePhotoDialog = new Dialog(ProfileActivity.this);
                mChangePhotoDialog.setContentView(R.layout.dialog_profile_set_photo);

                TextView tvTakePhoto = mChangePhotoDialog.findViewById(R.id.dialogTvTakePhoto);
                TextView tvDelete = mChangePhotoDialog.findViewById(R.id.dialogTvDelete);
                ImageView ivTakePhoto = mChangePhotoDialog.findViewById(R.id.dialogIvTakePhoto);
                ImageView ivDelete = mChangePhotoDialog.findViewById(R.id.dialogIvDelete);
                dialogClickListener(tvTakePhoto);
                dialogClickListener(tvDelete);

                mChangePhotoDialog.show();

            }
        });
    }

    private void dialogClickListener(View view){

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.dialogTvTakePhoto:
                        takePictureIntent();
                        mChangePhotoDialog.cancel();
                        break;
                    case R.id.dialogTvDelete:
                        mProfileImage.setImageResource(R.drawable.ic_help);
                        mChangePhotoDialog.cancel();
                        Toast.makeText(ProfileActivity.this, "Delete a photo from text", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }
//todo реализовать переключение
    private BottomNavigationView.OnNavigationItemSelectedListener getBottomNavigationListener() {
        return new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return true;
            }
        };


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void takePictureIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this,"Some thing WRONG", Toast.LENGTH_LONG).show();
            }

            if(photoFile != null){
                Uri photoUri = FileProvider.getUriForFile(this,"com.simbirsoft.marat.fileprovider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, IMAGE_REQUEST);
            }

        }
    }

    private void setmProfileImage(){
        Picasso.get().load(new File(mCurrentPhotoPath)).fit().centerCrop().into(mProfileImage);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(IMAGE_REQUEST == requestCode && resultCode == RESULT_OK){
            setmProfileImage();
        }
    }
}
