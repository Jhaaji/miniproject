package com.example.rajeevjha.mini;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class adminlogin extends AppCompatActivity {

    EditText email;
    EditText pass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        login=findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=email.getText().toString();
                String pass1=pass.getText().toString();

                //if(email.getText().toString()== "123" && pass.getText().toString()=="123"){
                   if(mail.equalsIgnoreCase("admin")&&pass1.equalsIgnoreCase("password")){
                       Toast.makeText(getApplicationContext(),"login succesfully",Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(adminlogin.this, save_activity.class);
                    startActivity(intent);
                }

                else{
                    Toast.makeText(getApplicationContext(),"errorrrrrrrr",Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
