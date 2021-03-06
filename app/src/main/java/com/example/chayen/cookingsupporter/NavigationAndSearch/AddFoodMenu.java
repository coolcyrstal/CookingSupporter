package com.example.chayen.cookingsupporter.NavigationAndSearch;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.format.DateFormat;
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
import android.widget.Toast;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

public class AddFoodMenu extends AppCompatActivity {

    private LinearLayout ingredient_layout, cookingmethod_layout;
    private EditText ingredient_edittext, cookingmethod_edittext, foodname_edittext;
    private FloatingActionButton addingredient, addcookingmethod;
    private Button upload_foodrecipe_image_button, add_foodrecipe_button;
    private ImageView foodrecipe_image;
    private Spinner text_addfood_type;
    private ProgressDialog mProgressDialog;

    ArrayList<String> ingredient_newrecipe = new ArrayList<>(),
            cookingmethod_newrecipe = new ArrayList<>();
    FoodDatabaseClass food_newrecipe;
    String[] foodtype_list;
    String[] foodtype_list_upFirebase;
    int ingredient_id = 0, cookingmethod_id = 0;
    ArrayList<EditText> ingredient_newrecipe_edittext = new ArrayList<>(),
            cookingmethod_newrecipe_edittext = new ArrayList<>();

    int select_image = 1;
    Uri selectedImage, food_photoURL_firebase;
    private StorageReference mStorageRef;

    FirebaseDatabase database;
    DatabaseReference myRef;

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
        foodname_edittext = (EditText)findViewById(R.id.text_addfood_name);

        upload_foodrecipe_image_button = (Button)findViewById(R.id.upload_foodrecipe_image_button);
        add_foodrecipe_button = (Button) findViewById(R.id.add_foodrecipe_button);
        foodrecipe_image = (ImageView)findViewById(R.id.foodrecipe_image);
        text_addfood_type = (Spinner)findViewById(R.id.text_addfood_type);

        addingredient = (FloatingActionButton)findViewById(R.id.ingredient_plus_edittext_button);
        addcookingmethod = (FloatingActionButton) findViewById(R.id.cookingmethod_plus_edittext_button);

        addingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ingredient_edittext.getText().toString().equals("")){
                    checkUpdateInfo(AddFoodMenu.this, "Your information not complete", "Please fill information before add.", "OK");
                } else{
                    if(ingredient_id == 0){
                        createEditText(ingredient_layout, "ส่วนผสม", ingredient_id, "ingredient");
                    } else if(ingredient_id > 0 &&
                            !ingredient_newrecipe_edittext.get(ingredient_id-1).getText().toString().equals("")){
                        createEditText(ingredient_layout, "ส่วนผสม", ingredient_id, "ingredient");
                    } else{
                        checkUpdateInfo(AddFoodMenu.this, "Your information not complete", "Please fill information before add.", "OK");
                    }
                }
            }
        });

        addcookingmethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cookingmethod_edittext.getText().toString().equals("")){
                    checkUpdateInfo(AddFoodMenu.this, "Your information not complete", "Please fill information before add.", "OK");
                } else{
                    if(cookingmethod_id == 0){
                        createEditText(cookingmethod_layout, "ขั้นตอนการทำ", cookingmethod_id, "cookingmethod");
                    } else if(cookingmethod_id > 0 &&
                            !cookingmethod_newrecipe_edittext.get(cookingmethod_id-1).getText().toString().equals("")){
                        createEditText(cookingmethod_layout, "ขั้นตอนการทำ", cookingmethod_id, "cookingmethod");
                    }
                    else{
                        checkUpdateInfo(AddFoodMenu.this, "Your information not complete", "Please fill information before add.", "OK");
                    }
                }

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
                uploadImageToFirebaseStorage();
            }
        });

        mStorageRef = FirebaseStorage.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        food_newrecipe = new FoodDatabaseClass();

        foodtype_list = getResources().getStringArray(R.array.foodType);
        foodtype_list_upFirebase = getResources().getStringArray(R.array.foodType_Firebase);
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

    private void createEditText(LinearLayout layout, String text, int id, String tag){
        EditText editTextOne = new EditText(this);
        editTextOne.setHint(text);
        editTextOne.setHintTextColor(Color.CYAN);
        layout.addView(editTextOne);
        if(tag == "ingredient"){
            ingredient_newrecipe_edittext.add(editTextOne);
            ingredient_id += 1;
        }else{
            cookingmethod_newrecipe_edittext.add(editTextOne);
            cookingmethod_id += 1;
        }
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
        Bitmap resized = null;
        try {
            resized = Bitmap.createScaledBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), file),
                    400, 300, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        showProgressDialog();

        Uri fileresized = getImageUri(resized);

        StorageReference storageRef = mStorageRef.child(food_newrecipe.getFood_type() + "/" + file.getLastPathSegment());
        UploadTask mUploadTask = storageRef.putFile(fileresized);

        mUploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                food_photoURL_firebase = taskSnapshot.getDownloadUrl();
//                Log.d("path image", "" + food_photoURL_firebase);
                if(food_newrecipe.getFood_type().equals("Frying_food")){
                    food_newrecipe.setFood_type("Frying");
                }else if(food_newrecipe.getFood_type().equals("Deep_Frying_food")){
                    food_newrecipe.setFood_type("Deep Frying");
                }
                uploadDataToFirebase();
            }
        });
    }

    private Uri getImageUri(Bitmap inImage) {
        File ftemp = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "/cookingsupporter/");
        if(!ftemp.exists())
            ftemp.mkdirs();
        FileOutputStream outStream = null;
        Date d = new Date();
        String filename  = (String) DateFormat.format("kkmmss-MMddyyyy", d.getTime());
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "/cookingsupporter/" + filename + ".jpg");
        try {
            outStream = new FileOutputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            inImage.compress(Bitmap.CompressFormat.JPEG, 80, outStream);
            outStream.write(bos.toByteArray());
            updateImage(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Uri.fromFile(file);
    }

    public void updateImage(File f) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(f));
        getApplicationContext().sendBroadcast(intent);
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

    private void uploadDataToFirebase(){
        myRef = database.getReference().child("food");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            food_newrecipe.setAuthor(user.getUid());
        }
        food_newrecipe.setFood_name(foodname_edittext.getText().toString());
        setIngredientEdittext(ingredient_id);
        setCookingMethodEdittext(cookingmethod_id);
        food_newrecipe.setIngredient(ingredient_newrecipe);
        food_newrecipe.setCooking_method(cookingmethod_newrecipe);
        food_newrecipe.setFood_image(food_photoURL_firebase.toString());
        food_newrecipe.setStar_count(Long.valueOf(0));
        food_newrecipe.setUser_count(Long.valueOf(0));
        myRef.push().setValue(food_newrecipe);

        Toast.makeText(this, "upload success", Toast.LENGTH_SHORT).show();
        hideProgressDialog();
        finish();
    }

    private void setIngredientEdittext(int id){
        ingredient_newrecipe.add(ingredient_edittext.getText().toString());
        for(int i = 0; i < id; i++){
            ingredient_newrecipe.add(ingredient_newrecipe_edittext.get(i).getText().toString());
        }
    }

    private void setCookingMethodEdittext(int id){
        cookingmethod_newrecipe.add(cookingmethod_edittext.getText().toString());
        for(int i = 0; i < id; i++){
            cookingmethod_newrecipe.add(cookingmethod_newrecipe_edittext.get(i).getText().toString());
        }
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

    private void showProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }
}
