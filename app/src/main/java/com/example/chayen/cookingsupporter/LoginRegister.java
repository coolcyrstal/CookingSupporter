package com.example.chayen.cookingsupporter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.CreateAccount.RegisterPage;

public class LoginRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        TextView create_account = (TextView)findViewById(R.id.create_new_account);
        Button login_button = (Button)findViewById(R.id.login_button);
        EditText text_user_id = (EditText)findViewById(R.id.textUserId);
        EditText text_password = (EditText)findViewById(R.id.textPassword);

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginRegister.this, RegisterPage.class);
                startActivity(intent);
            }
        });
    }
}
