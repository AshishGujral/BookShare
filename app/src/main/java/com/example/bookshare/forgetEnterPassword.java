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

public class forgetEnterPassword extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    Boolean isUpdated;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_enter_password);
        databaseHelper = new DatabaseHelper(this);
        Button PasswordResetBtn= findViewById(R.id.PasswordResetBtn);
        EditText ResetPassword= findViewById(R.id.ResetPassword);
        EditText ResetPasswordConfirm= findViewById(R.id.ResetPasswordConfirm);
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = getIntent();
         email = sharedPreferences.getString("key1","");

        PasswordResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtPassword = ResetPassword.getText().toString();
                String txtConfirmPassword= ResetPasswordConfirm.getText().toString();

                if(txtPassword.equals("")|| txtConfirmPassword.equals(""))
                    Toast.makeText(forgetEnterPassword.this,"Please fill all the fields"
                            , Toast.LENGTH_SHORT).show();
                else{
                    if(txtPassword.equals(txtConfirmPassword)){
                        isUpdated = databaseHelper.UpdatePassword(email,txtPassword);
                        // use if else to check condition
                        if(isUpdated) {
                            Toast.makeText(forgetEnterPassword.this,
                                    "Data is Updated Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(forgetEnterPassword.this,
                                    "Data is not Updated", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(forgetEnterPassword.this,"Both password not matched"
                                , Toast.LENGTH_SHORT).show();
                    }
                }
                Toast.makeText(forgetEnterPassword.this,email, Toast.LENGTH_SHORT).show();
            }

        });
    }

}