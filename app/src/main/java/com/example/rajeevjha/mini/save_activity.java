package com.example.rajeevjha.mini;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class save_activity extends AppCompatActivity {
    EditText editTexttbarcode, etprodname, editTextprice, etcompany, editTextquantity;
    Button btsave, next, scan;
    FirebaseDatabase database;
    private DatabaseReference reference;
    final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_activity);
        editTexttbarcode = findViewById(R.id.etbar);
        etprodname = findViewById(R.id.etpname);
        editTextprice = findViewById(R.id.etprice);
        etcompany = findViewById(R.id.etcname);
        editTextquantity = findViewById(R.id.etquan);
        btsave = findViewById(R.id.button_save);
        next = findViewById(R.id.btn_next);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("All Barcodes");
        scan = findViewById(R.id.scan);

        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String B = editTexttbarcode.getText().toString();
                String pn = etprodname.getText().toString();
                String pr = editTextprice.getText().toString();
                String comp = etcompany.getText().toString();
                String quan = editTextquantity.getText().toString();
                DBHelper db = new DBHelper();
                db.setBarcode(B);
                db.setpname(pn);
                db.setprice(pr);
                db.setcompany(comp);
                db.setquantity(quan);
                Toast.makeText(activity, "Pushed", Toast.LENGTH_SHORT).show();
                reference.child(B).setValue(db);
                Toast.makeText(activity, "Done", Toast.LENGTH_SHORT).show();

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Main2Activity.class));
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
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


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null)

            if (result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                ;
            } else {
                //long res=parseLong(result.getContents());
                String res = result.getContents();
                // num.setText(res);
                editTexttbarcode.setText(res);

                // Toast.makeText(this,String.valueOf(res),Toast.LENGTH_LONG).show();


                Toast.makeText(this, "Scanned", Toast.LENGTH_LONG).show();

            }

    }
}

