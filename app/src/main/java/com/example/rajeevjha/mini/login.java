package com.example.rajeevjha.mini;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Button _btnLogin;
    EditText _txtEmail;
    EditText _txtPass;
    Cursor cursor ;
    Button adminlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper= new DatabaseHelper(this);
        db=openHelper.getReadableDatabase();
        _btnLogin=(Button)findViewById(R.id.btnLogin);
        _txtEmail=(EditText)findViewById(R.id.txtEmail);
        _txtPass=(EditText)findViewById(R.id.txtPass);
       // adminlogin=findViewById(R.id.adminlogin);
        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=_txtEmail.getText().toString();
                String pass=_txtPass.getText().toString();

                if(email=="admin"&&pass=="password")
                {
                    Toast.makeText(getApplicationContext(),"login succesfully",Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(login.this, save_activity.class);
                    startActivity(intent);
                }

               else{
                    cursor=db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME +" WHERE " + DatabaseHelper.COL_5 + "=? AND " + DatabaseHelper.COL_4 + "=?",new String[]{email,pass});


                    if(cursor!=null){
                        if(cursor.getCount()>0){
                            cursor.moveToNext();
                            Toast.makeText(getApplicationContext(),"login succesfully",Toast.LENGTH_LONG).show();
                            Intent intent =new Intent(login.this, Main2Activity.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                        }
                    }
                }


            }
        });


    }



}
