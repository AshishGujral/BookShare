package com.example.bookshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Forgetpassword extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        databaseHelper = new DatabaseHelper(this);
        Button LoginBtn= findViewById(R.id.forgetBtn);
        EditText forgetAge= findViewById(R.id.forgetAge);
        EditText forgetAddress= findViewById(R.id.forgetAddress);
        EditText forgetEmail= findViewById(R.id.forgetEmail);
        SharedPreferences sharedPreferences=
                PreferenceManager.getDefaultSharedPreferences(this);
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String txtage = forgetAge.getText().toString();
                String txtaddress= forgetAddress.getText().toString();
                String txtemail= forgetEmail.getText().toString();
                if(forgetAge.equals("")|| forgetAddress.equals("")||forgetEmail.equals(""))
                    Toast.makeText(Forgetpassword.this,"Please fill all the fields"
                            , Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkforget= databaseHelper.checkForget(txtage,txtaddress,txtemail);

                    if(checkforget == true){
                        Toast.makeText(Forgetpassword.this,"Welcome Back!"
                                , Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        editor.putString("key1",txtemail);
                        startActivity(new Intent(Forgetpassword.this,forgetEnterPassword.class));
                        // use intent to pass email address in the forget page to update the data

                    }else{
                        Toast.makeText(Forgetpassword.this,"Above Fields are not matched with the current records"
                                , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}