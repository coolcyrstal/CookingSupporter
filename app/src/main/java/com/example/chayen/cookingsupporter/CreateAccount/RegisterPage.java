package com.example.chayen.cookingsupporter.CreateAccount;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chayen.cookingsupporter.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import static com.example.chayen.cookingsupporter.LoginRegister.mAuth;

public class RegisterPage extends AppCompatActivity {

    EditText textRegisDisplayName, textEmailAddress, textPassword, textRePassword;
    Button nextbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.register_actionbar);

        textRegisDisplayName = (EditText)findViewById(R.id.text_register_displayname);
        textEmailAddress = (EditText)findViewById(R.id.textEmailAddress);
        textPassword = (EditText)findViewById(R.id.text_createPassword);
        textRePassword = (EditText)findViewById(R.id.text_createPassword_confirm);
        nextbutton = (Button)findViewById(R.id.nextbutton);


        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textRegisDisplayName.getText().toString().equals("") || textEmailAddress.getText().toString().equals("")
                        || textPassword.getText().toString().equals("") || textRePassword.getText().toString().equals("")){
                    checkAccountInfo(RegisterPage.this, "Your information not complete", "Please fill all information.", "OK");
                } else if(textPassword.getText().toString().length() < 6 || textPassword.getText().toString().length() > 12){
                    checkAccountInfo(RegisterPage.this, "Your password not correct", "Your password length should be 6-12 character.", "OK");
                } else if(textRePassword.getText().toString() != textPassword.getText().toString()){
                    checkAccountInfo(RegisterPage.this, "Your password not correct", "Please check your password matched.", "OK");
                } else{
                    goCreateAccount();
                }
            }
        });
    }

    private void goCreateAccount(){
        mAuth.createUserWithEmailAndPassword(textEmailAddress.getText().toString(), textPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            checkAccountInfo(RegisterPage.this, "Please check your filled", "Email or password not correct.", "OK");
                        }
                        else{
                            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.register_info);
                            if (fragment instanceof RegisterSuccess == false) {
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.register_info, RegisterSuccess.newInstance(), "Register Success")
                                        .addToBackStack(null)
                                        .commit();
                            }else Toast.makeText(RegisterPage.this, "Error", Toast.LENGTH_SHORT).show();
                            updateUserDetail();
                            Toast.makeText(RegisterPage.this, "Create Account Success", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateUserDetail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(textRegisDisplayName.getText().toString())
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

    private AlertDialog checkAccountInfo(final AppCompatActivity act, CharSequence title,
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
