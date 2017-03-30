package com.example.chayen.cookingsupporter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chayen.cookingsupporter.CreateAccount.RegisterPage;
import com.example.chayen.cookingsupporter.MainPage.HomePage;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginRegister extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    EditText text_email, text_password;
    Button login_button;
    View login_with_google;
    TextView create_account;
    private ProgressDialog mProgressDialog;
    public static GoogleApiClient mGoogleApiClient;

    public static FirebaseAuth mAuth;
    public static FirebaseAuth.AuthStateListener mAuthListener;
    AuthCredential credential;
    private static final int RC_SIGN_IN = 1;
    private static String googleClientID = "681253333339-8rvlgar1lb7qniodbgvbcd0a5vunok2a.apps.googleusercontent.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        create_account = (TextView)findViewById(R.id.create_new_account);
        login_button = (Button)findViewById(R.id.login_button);
        login_with_google = findViewById(R.id.sign_in_button_google);
        text_email = (EditText)findViewById(R.id.userEmail);
        text_password = (EditText)findViewById(R.id.userPassword);

        mAuth = FirebaseAuth.getInstance();

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
                .requestIdToken(this.getResources().getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(LoginRegister.this, "Connection failed",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signIn();

    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
            Toast.makeText(LoginRegister.this, "result:" + result.isSuccess() + result.getSignInAccount(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            showProgressDialog();
            GoogleSignInAccount acct = result.getSignInAccount();
            firebaseAuthWithGoogle(acct);
        } else {
            // Signed out, show unauthenticated UI.

        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginRegister.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            intentToHomePage();
                        }
                    }
                });
    }

    private void intentToHomePage(){
        Intent intent = new Intent(LoginRegister.this, HomePage.class);
        startActivity(intent);
        hideProgressDialog();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
//            mGoogleApiClient.stopAutoManage(this);
//            mGoogleApiClient.disconnect();
//        }
//    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mGoogleApiClient.stopAutoManage(this);
//        mGoogleApiClient.disconnect();
//    }

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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
