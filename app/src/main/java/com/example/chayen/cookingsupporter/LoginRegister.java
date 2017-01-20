package com.example.chayen.cookingsupporter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.Authenticator;

public class LoginRegister extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    EditText text_email, text_password;
    Button login_button;
    View login_with_google;
    TextView create_account;
    private ProgressDialog mProgressDialog;
    public static GoogleApiClient mGoogleApiClient;

    public static FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        create_account = (TextView)findViewById(R.id.create_new_account);
        login_button = (Button)findViewById(R.id.login_button);
        login_with_google = findViewById(R.id.sign_in_button_google);
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
                    checkLoginInfo(LoginRegister.this, "Please type again", "User_Id or Password incorrect.", "OK");
                } else{
                    loginWithEmailPass();
                }
            }
        });

        login_with_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWithGooglePlus();
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
                            checkLoginInfo(LoginRegister.this, "Authentication failed", "Please make sure you have created account or internet connected.", "OK");
                        }
                        else{
                            intentToHomePage();
                        }
                    }
                });
    }

    protected void loginWithGooglePlus(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signIn();
        intentToHomePage();
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void intentToHomePage(){
        showProgressDialog();
        Intent intent = new Intent(LoginRegister.this, HomePage.class);
        startActivity(intent);
        hideProgressDialog();
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

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
