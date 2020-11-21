package com.example.rajeevjha.mini;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.razorpay.PaymentResultListener;

public class MerchantActivity extends Activity implements PaymentResultListener {

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);
    }
    @Override
    public void onPaymentSuccess(String s) {
        
    }

    @Override
    public void onPaymentError(int i, String s) {

    }




}
