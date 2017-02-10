package com.example.chayen.cookingsupporter.NavigationAndSearch;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.chayen.cookingsupporter.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Profile extends AppCompatActivity {

    Button upload_image, update_user_profile;
    EditText text_display_name;
    ImageView profile_image;
    Uri selectedImage;
    Bitmap bitmap;
    int select_image = 1; //code number for return result
    private StorageReference mStorageRef;
    Uri userphotoURL_firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initialize();
    }

    private void initialize(){
        upload_image = (Button)findViewById(R.id.upload_image_button);
        update_user_profile = (Button)findViewById(R.id.user_profile_update_button);
        text_display_name = (EditText)findViewById(R.id.text_updateDisplatName);
        profile_image = (ImageView)findViewById(R.id.profile_image);

        update_user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text_display_name.getText().toString().equals("")){
                    checkUpdateInfo(Profile.this, "Your information not complete", "Please fill information.", "OK");
                } else{
                    uploadImageToFirebaseStorage();
                }
            }
        });

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImageFromGallery();
            }
        });

        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    private void loadImageFromGallery(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), select_image);
//        Intent intent_gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent_gallery, select_image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == select_image && resultCode == RESULT_OK){
            selectedImage = data.getData();
            Picasso.with(getApplicationContext()).load(selectedImage).into(profile_image);
//            Log.d("selected image", "" + selectedImage);
        }
    }

    private void uploadImageToFirebaseStorage(){
//        String[] filePathColumn = {MediaStore.Images.Media.DATA};
//
//        //read position of selected image
//        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//        cursor.moveToFirst();
//
//        //file and path of image
//        int columnindex = cursor.getColumnIndex(filePathColumn[0]);
//        String pathfile = cursor.getString(columnindex);
//        cursor.close();

        Uri file = Uri.fromFile(new File(getRealPathFromURI(getApplicationContext(), selectedImage)));

        StorageReference storageRef = mStorageRef.child("user_profile/" + file.getLastPathSegment());
        UploadTask mUploadTask = storageRef.putFile(file);

        mUploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                userphotoURL_firebase = taskSnapshot.getDownloadUrl();
//                Log.d("path image", "" + userphotoURL_firebase);
                updateUserDetail();
            }
        });
    }

    @SuppressLint("NewApi")
    public static String getRealPathFromURI(Context context, Uri uri){
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = { MediaStore.Images.Media.DATA };

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{ id }, null);

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }

    @SuppressLint("NewApi")
    public static String getRealPathFromURI_API11to18(Context context, Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        String result = null;

        CursorLoader cursorLoader = new CursorLoader(
                context,
                contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        if(cursor != null){
            int column_index =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            result = cursor.getString(column_index);
        }
        return result;
    }

    public static String getRealPathFromURI_BelowAPI11(Context context, Uri contentUri){
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        int column_index
                = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void updateUserDetail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(text_display_name.getText().toString())
                .setPhotoUri(userphotoURL_firebase)
                .build();

        user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if (task.isSuccessful()) {
//                    Log.d("update profile", "success");
                    finish();
                }
            }
        });
    }

    private AlertDialog checkUpdateInfo(final AppCompatActivity act, CharSequence title,
                                         CharSequence message, CharSequence buttonYes){
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title).setMessage(message).setPositiveButton(buttonYes, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return downloadDialog.show();
    }
}
