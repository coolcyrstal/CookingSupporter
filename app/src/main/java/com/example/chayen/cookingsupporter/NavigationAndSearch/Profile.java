package com.example.chayen.cookingsupporter.NavigationAndSearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chayen.cookingsupporter.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Profile extends AppCompatActivity {

    Button upload_image, update_user_profile;
    EditText text_display_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initialize();
    }

    private void initialize(){
        upload_image = (Button)findViewById(R.id.user_profile_update_button);
        update_user_profile = (Button)findViewById(R.id.user_profile_update_button);
        text_display_name = (EditText)findViewById(R.id.text_updateDisplatName);

        update_user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserDetail();
            }
        });

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void updateUserDetail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(text_display_name.getText().toString())
                .build();

        user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("update profile", "success");
                }
            }
        });
    }
}
