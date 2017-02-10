package com.example.chayen.cookingsupporter.NavigationAndSearch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class AddFoodMenu extends AppCompatActivity {

    LinearLayout ingredient_layout, cookingmethod_layout;
    EditText ingredient_edittext, cookingmethod_edittext;
    FloatingActionButton addingredient, addcookingmethod;
    Button upload_foodrecipe_image_button, add_foodrecipe_button;
    ImageView foodrecipe_image;
    Spinner text_addfood_type;

    ArrayList<String> ingredient_newrecipe, cookingmethod_newrecipe;
    FoodDatabaseClass food_newrecipe;
    String[] foodtype_list;
    String[] foodtype_list_upFirebase = getResources().getStringArray(R.array.foodType_Firebase);

    int select_image = 1;
    Uri selectedImage, food_photoURL_firebase;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_menu);

        initialize();
    }

    private void initialize(){
        ingredient_layout = (LinearLayout) findViewById(R.id.layout_addedittext_ingredient);
        cookingmethod_layout = (LinearLayout) findViewById(R.id.layout_addedittext_cookingmethod);
        ingredient_edittext = (EditText)findViewById(R.id.text_addfood_ingredient);
        cookingmethod_edittext = (EditText)findViewById(R.id.text_addfood_cookingmethod);
        upload_foodrecipe_image_button = (Button)findViewById(R.id.upload_foodrecipe_image_button);
        add_foodrecipe_button = (Button) findViewById(R.id.add_foodrecipe_button);
        foodrecipe_image = (ImageView)findViewById(R.id.foodrecipe_image);
        text_addfood_type = (Spinner)findViewById(R.id.text_addfood_type);

        addingredient = (FloatingActionButton)findViewById(R.id.ingredient_plus_edittext_button);
        addcookingmethod = (FloatingActionButton) findViewById(R.id.cookingmethod_plus_edittext_button);

        addingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEditText(ingredient_layout, "ส่วนผสม");
            }
        });

        addcookingmethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEditText(cookingmethod_layout, "ขั้นตอนการทำ");
            }
        });

        upload_foodrecipe_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImageFromGallery();
            }
        });

        add_foodrecipe_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mStorageRef = FirebaseStorage.getInstance().getReference();

        foodtype_list = getResources().getStringArray(R.array.foodType);
        ArrayAdapter<String> adapterFoodType = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, foodtype_list);
        text_addfood_type.setAdapter(adapterFoodType);

        text_addfood_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                food_newrecipe.setFood_type(foodtype_list_upFirebase[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                food_newrecipe.setFood_type(foodtype_list_upFirebase[0]);
            }
        });
    }

    private void createEditText(LinearLayout layout, String text){
        EditText editTextOne = new EditText(this);
        editTextOne.setHint(text);
        editTextOne.setHintTextColor(Color.CYAN);
        layout.addView(editTextOne);
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
            Picasso.with(getApplicationContext()).load(selectedImage).into(foodrecipe_image);
//            Log.d("selected image_addfood", "" + selectedImage);
        }
    }

    private void uploadImageToFirebaseStorage(){
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
                food_photoURL_firebase = taskSnapshot.getDownloadUrl();
                Log.d("path image", "" + food_photoURL_firebase);
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
}
