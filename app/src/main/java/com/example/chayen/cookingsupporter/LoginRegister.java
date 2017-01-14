package com.example.chayen.cookingsupporter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.CreateAccount.RegisterPage;

public class LoginRegister extends AppCompatActivity {

    EditText text_user_id, text_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        TextView create_account = (TextView)findViewById(R.id.create_new_account);
        Button login_button = (Button)findViewById(R.id.login_button);
        text_user_id = (EditText)findViewById(R.id.textUserId);
        text_password = (EditText)findViewById(R.id.textPassword);

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
                if(text_user_id.getText().toString().equals("") || text_password.getText().toString().equals("")){
                    checkLoginInfo(LoginRegister.this, "User_Id or Password incorrect", "Please type again.", "OK");
                } else{

                }
            }
        });
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
