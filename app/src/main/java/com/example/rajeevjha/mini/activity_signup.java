package com.example.rajeevjha.mini;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class activity_signup extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnreg, _btnlogin;
    EditText _txtfname,_txtlname,_txtpass,_txtmail,_txtphone;
    String fname,lname,pass,mail,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        openHelper=new DatabaseHelper(this);
        _btnreg=(Button)findViewById(R.id.btnreg);
        _txtfname=(EditText)findViewById(R.id.txtfname);
        _txtlname=(EditText)findViewById(R.id.txtlname);
        _txtpass=(EditText)findViewById(R.id.txtpass);
        _txtmail=(EditText)findViewById(R.id.txtmail);
        _txtphone=(EditText)findViewById(R.id.txtphone);
        _btnlogin=(Button)findViewById(R.id.btnlogin);
        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                fname=_txtfname.getText().toString();
                lname=_txtlname.getText().toString();
                pass=_txtpass.getText().toString();
                mail=_txtmail.getText().toString();
                phone=_txtphone.getText().toString();




                checkDataEntered();
                // insertdata(fname,lname,pass,mail,phone);



                // Toast.makeText(getApplicationContext(),"registered succesfully",Toast.LENGTH_LONG).show();

            }
        });



        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(activity_signup.this,login.class);
                startActivity(intent);
            }
        });
        // _btnreg.setOnClickListener(new View.OnClickListener() {
        //   @Override
        // public void onClick(View v) {
        //   checkDataEntered();

        // }
        //});




    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    public void checkDataEntered()
    {
        /*if (isEmpty(_txtfname)) {
            //Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            //t.show();
            _txtlname.setError("first name is required!");


        }

        if (isEmpty(_txtlname)) {
            _txtlname.setError("Last name is required!");
        }

        if (isEmail(_txtmail) == false) {
            _txtmail.setError("Enter valid email!");
        }*/
        if(!isEmpty(_txtfname)&&!isEmpty(_txtlname)&&isEmail(_txtmail)){
            insertdata(fname,lname,pass,mail,phone);
            Toast.makeText(getApplicationContext(),"signup succesful",Toast.LENGTH_LONG).show();
        }

        else{
            if(isEmpty(_txtfname)){
                _txtfname.setError("first name is required");
            }
            else if (isEmpty(_txtlname)){
                _txtlname.setError("last name required");
            }
            else if (!isEmail(_txtmail)){
                _txtmail.setError("enter vakid email");
            }
            else{

                Toast.makeText(getApplicationContext(),"signup unsuccesful",Toast.LENGTH_LONG).show();
                Intent intent =new Intent(activity_signup.this, Main2Activity.class);
                startActivity(intent);

            }

        }


    }

    public void insertdata(String fname,String lname,String pass, String mail,String phone)
    {
        long id;
        ContentValues contentValues=new ContentValues();
        //
        //id=db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
        //contentValues.put(DatabaseHelper.COL_1,id);
        contentValues.put(DatabaseHelper.COL_2,fname);


        contentValues.put(DatabaseHelper.COL_3,lname);
        contentValues.put(DatabaseHelper.COL_4,pass);
        contentValues.put(DatabaseHelper.COL_5,mail);
        contentValues.put(DatabaseHelper.COL_6,phone);
        id=db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
    }




}

