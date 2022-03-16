package com.example.bookshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        Button LoginBtn= findViewById(R.id.MainLoginBtn);
        Button CreateBtn = findViewById(R.id.CreateBtn);
        Button adminBtn = findViewById(R.id.adminBtn);
        Button forgetABtn = findViewById(R.id.forgetABtn);
        EditText email= findViewById(R.id.mainLoginEmail);
        EditText password= findViewById(R.id.mainLoginPassword);
        forgetABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Forgetpassword.class));
            }
        });
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // return the fetched email
                String txtemail = email.getText().toString();
                String txtpassword= password.getText().toString();
                if(txtemail.equals("")|| txtpassword.equals(""))
                    Toast.makeText(MainActivity.this,"Email Id or Password should not be blanked"
                            , Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUserPassword= databaseHelper.checkEmailPassword(txtemail,txtpassword);
                    if(checkUserPassword == true){
                        Toast.makeText(MainActivity.this,"Welcome Back!"
                                , Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,Login.class));
                    }else{
                        Toast.makeText(MainActivity.this,"User name or Password Doesn't match"
                                , Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        CreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,register.class));
            }
        });
        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Adminlogin.class));
            }
        });
    }
}