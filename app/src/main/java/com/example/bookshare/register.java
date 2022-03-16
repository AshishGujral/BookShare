package com.example.bookshare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Database object
        databaseHelper = new DatabaseHelper(this);
        // creating fields for user --Ashish
        EditText txtEmail = findViewById(R.id.forgetEmail);
        EditText txtName = findViewById(R.id.RegisterUsername);
        EditText txtPassword = findViewById(R.id.ResetPassword);
        EditText txtAge = findViewById(R.id.forgetAge);
        EditText txtAddress = findViewById(R.id.forgetAddress);
        EditText txtReading = findViewById(R.id.RegisterReading);
        Button RegisterUserBtn = findViewById(R.id.RegisterLoginBtn);

        // Event handler for Register new user -- Ashish Gujral
        RegisterUserBtn.setOnClickListener(new View.OnClickListener() {
            String email,name,password,age,address,reading;
            boolean isInserted;
            @Override
            public void onClick(View v) {
                email= txtEmail.getText().toString();
                name= txtName.getText().toString();
                password= txtPassword.getText().toString();
                age= txtAge.getText().toString();
                address= txtAddress.getText().toString();
                reading= txtReading.getText().toString();
                if(email.equals("")|| name.equals("")||password.equals("")||age.equals("")
                        ||address.equals("")||reading.equals(""))
                    Toast.makeText(register.this,"Please fill all the fields"
                            , Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUser= databaseHelper.checkEmail(email);
                    if(checkUser == false){
                        isInserted = databaseHelper.addUser(email,name,password,age,address,reading);
                        if(isInserted) {
                            Toast.makeText(register.this,
                                    "Data is added Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(register.this,
                                    "Data is not added", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(register.this,"User already Exists"
                                , Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

}