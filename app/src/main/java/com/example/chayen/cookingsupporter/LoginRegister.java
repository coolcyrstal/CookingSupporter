package com.example.chayen.cookingsupporter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chayen.cookingsupporter.CreateAccount.RegisterPage;
import com.example.chayen.cookingsupporter.MainPage.HomePage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRegister extends AppCompatActivity {

    EditText text_email, text_password;

    public static FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        TextView create_account = (TextView)findViewById(R.id.create_new_account);
        Button login_button = (Button)findViewById(R.id.login_button);
        text_email = (EditText)findViewById(R.id.userEmail);
        text_password = (EditText)findViewById(R.id.userPassword);

        mAuth = FirebaseAuth.getInstance();

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginRegister.this, RegisterPage.class);
                startActivity(intent);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text_email.getText().toString().equals("") || text_password.getText().toString().equals("")){
                    checkLoginInfo(LoginRegister.this, "User_Id or Password incorrect", "Please type again.", "OK");
                } else{
                    loginWithEmailPass();
                }
            }
        });
    }

    protected void loginWithEmailPass(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d("signed in", "onAuthStateChanged:signed_in:" + user.getUid());
                }
                else{
                    Log.d("signed out", "onAuthStateChanged:signed_out");
                }
            }
        };

        mAuth.signInWithEmailAndPassword(text_email.getText().toString(), text_password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginRegister.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent intent = new Intent(LoginRegister.this, HomePage.class);
                            startActivity(intent);
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        //mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private AlertDialog checkLoginInfo(final AppCompatActivity act, CharSequence title,
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
