package com.example.rajeevjha.mini;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class Main2Activity extends AppCompatActivity  {
    private ZXingScannerView zXingScannerView;
    Button btn_save,btn_return;
    EditText etbarcode;
    FirebaseDatabase database;
    ListView lv;
    TextView tvsum;
    List<DBHelper> ProductList;
    ArrayAdapter arrayAdapter;
    ArrayList<String> details = new ArrayList<>();
    private DatabaseReference reference;
    final Activity activity = this;
    SimpleCursorAdapter sca;
    TextView num;
    Button buttonscan;

    TextView totPrice;
    Button buttonpay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvsum=findViewById(R.id.sumsum);
        btn_return=findViewById(R.id.btn_return);
        btn_save = findViewById(R.id.btn_save);
        etbarcode = findViewById(R.id.etbarcode);
        lv = findViewById(R.id.listView);
        buttonscan=findViewById(R.id.buttonscan);
       // num=findViewById(R.id.num);
        totPrice = findViewById(R.id.totPrice);
        buttonpay=findViewById(R.id.buttonpay);
        //num=findViewById(R.id.num);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("All Barcodes");

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, details);
        lv.setAdapter(arrayAdapter);
        //code to implement barcode
        buttonscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator in = new IntentIntegrator(activity);
                in.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                in.setPrompt("scan");
                in.setCameraId(0);
                in.setBeepEnabled(false);
                //in.setBarcodeImageEnabled(false);
                in.initiateScan();


            }
        });
        buttonpay.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Main2Activity.this, MerchantActivity.class);
                startActivity(intent);

            }
        });

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = etbarcode.getText().toString();



                    Toast.makeText(activity, etbarcode.getText().toString(), Toast.LENGTH_SHORT).show();
                    getProductFromDb(etbarcode.getText().toString());





                //Toast.makeText(activity,num.getText().toString(), Toast.LENGTH_SHORT).show();
                //getProductFromDb(num.getText().toString());



            }

            public void getProductFromDb(final String Barcode) {

                /*reference.orderByChild(Barcode).startAt(00).on("child_added", function(snapshot) {
                    console.log(snapshot.key);
                });*/
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        DBHelper p = dataSnapshot.child(Barcode).getValue(DBHelper.class);
                        //Toast.makeText(activity,"HI", Toast.LENGTH_SHORT).show();

                           int price = Integer.parseInt(tvsum.getText().toString()) + Integer.parseInt(p.getprice());
                           Toast.makeText(Main2Activity.this,p.getprice(),Toast.LENGTH_SHORT).show();
                           tvsum.setText(Integer.toString(price));



                        //sum.setText(price);



                        //Toast.makeText(activity,p.toString(), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(scanner.this, "Barcode is : " + p, Toast.LENGTH_SHORT).show();

                              /*for (DataSnapshot prodSnapshot : dataSnapshot.getChildren()) {
                                  DBHelper product = prodSnapshot.child(Barcode).getValue(DBHelper.class);
                                  Toast.makeText(scanner.this, "Barcode is : " + product, Toast.LENGTH_SHORT).show();
                              }*/




                        //To Display the details
                        details.add(p.toString());
                        arrayAdapter.notifyDataSetChanged();





                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Log.d(Tag, "Error trying to get classified ads for " + Barcode +
                        //" " + databaseError);
                        Toast.makeText(getActivity(),
                                "Error trying to get classified ads for " + Barcode, Toast.LENGTH_SHORT).show();
                    }


                });
            }
        });
   /* btn_save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String B = etbarcode.getText().toString();
            DBHelper db= new DBHelper();
            db.setBarcode(B);
            Toast.makeText(activity, "Pushed", Toast.LENGTH_SHORT).show();
            reference.child(B).setValue(db);
            Toast.makeText(activity, "Done", Toast.LENGTH_SHORT).show();
        }
    });*/
    }
//zxing scanner
   /* public void scan(View view) {
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }



    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {


        //String res=result.getContents();
       etbarcode.setText(result.getText());
        Toast.makeText(getApplicationContext(), result.getText(), Toast.LENGTH_SHORT).show();


        zXingScannerView.resumeCameraPreview(this);
        Intent intent= new Intent(Main2Activity.this,Main2Activity.class);

    }*/




    private Context getActivity() {return activity;
    }
    //getting output to barcode
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null)

            if(result.getContents()==null)
            {
                Log.d("MainActivity","Cancelled scan");
                Toast.makeText(this,"Cancelled",Toast.LENGTH_LONG).show();;
            }
            else{
                //long res=parseLong(result.getContents());
                String res=result.getContents();
               // num.setText(res);
                etbarcode.setText(res);

                // Toast.makeText(this,String.valueOf(res),Toast.LENGTH_LONG).show();


                Toast.makeText(this,"Scanned",Toast.LENGTH_LONG).show();

            }



    }










}