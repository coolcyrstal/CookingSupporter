package com.example.chayen.cookingsupporter.CreateAccount;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import static com.example.chayen.cookingsupporter.LoginRegister.mAuth;

public class RegisterPage extends AppCompatActivity {

    EditText textFirstName, textLastName, textEmailAddress, textPassword;
    Button nextbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.register_actionbar);

        textFirstName = (EditText)findViewById(R.id.textFirstname);
        textLastName = (EditText)findViewById(R.id.textLastname);
        textEmailAddress = (EditText)findViewById(R.id.textEmailAddress);
        textPassword = (EditText)findViewById(R.id.text_createPassword);
        nextbutton = (Button)findViewById(R.id.nextbutton);


        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textFirstName.getText().toString().equals("") || textLastName.getText().toString().equals("")){
                    checkAccountInfo(RegisterPage.this, "Your information not complete", "Please input all field.", "OK");
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
                            Toast.makeText(RegisterPage.this, "Create Account Success", Toast.LENGTH_SHORT).show();
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
