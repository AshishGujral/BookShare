package com.example.bookshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Adminlogin extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    boolean isInserted;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        databaseHelper = new DatabaseHelper(this);
        Button LoginBtn= findViewById(R.id.adminLoginbtn);
        EditText adminId= findViewById(R.id.adminId);
        EditText password= findViewById(R.id.adminPassword);

        if(i==0){
            isInserted = databaseHelper.addAdmin();
            if(isInserted) {
                Toast.makeText(Adminlogin.this,
                        "Data is added Successfully", Toast.LENGTH_SHORT).show();
                i=i+1;
            }
            else{
                Toast.makeText(Adminlogin.this,
                        "Data is not added", Toast.LENGTH_SHORT).show();
                i=0;
            }
        }

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtId = adminId.getText().toString();
                String txtpassword= password.getText().toString();

                if(txtId.equals("")|| txtpassword.equals(""))
                    Toast.makeText(Adminlogin.this,"Admin Id or Password should not be blanked"
                            , Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUserPassword= databaseHelper.checkAdmin(txtId,txtpassword);
                    if(checkUserPassword == true){
                        Toast.makeText(Adminlogin.this,"Welcome Back!"
                                , Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Adminlogin.this,Admin.class));
                    }else{
                        Toast.makeText(Adminlogin.this,"User name or Password Doesn't match"
                                , Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}