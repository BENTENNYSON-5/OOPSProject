package com.example.ksagtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class Register_mobile extends AppCompatActivity {

    public static final String TAG = "TAG";
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    EditText phoneNumber,codeEnter;
    Button nextBtn;
    ProgressBar progressBar;
    TextView state;
    CountryCodePicker codePicker;
    String verificationId;
    PhoneAuthProvider.ForceResendingToken token;
    Boolean verficationInProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_mobile);

        fAuth = FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        phoneNumber = findViewById(R.id.phone);
        codeEnter = findViewById(R.id.codeEnter);
        progressBar =  findViewById(R.id.progressBar);
        nextBtn = findViewById(R.id.nextBtn);
        state = findViewById(R.id.state);
        codePicker = findViewById(R.id.ccp);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verficationInProgress){
                    if(!phoneNumber.getText().toString().isEmpty() && phoneNumber.getText().toString().length() == 10){

                        String phoneNum = "+"+codePicker.getSelectedCountryCode()+phoneNumber.getText().toString();
                        Log.d(TAG, "onClick: Phone no - "+ phoneNum);
                        progressBar.setVisibility(View.VISIBLE);
                        state.setText("Sending OTP....");
                        state.setVisibility(View.VISIBLE);
                        requestOTP(phoneNum);

                    }
                    else {
                        phoneNumber.setError("Please Enter Valid Phone Number");
                    }
                }else{
                    String userOTP= codeEnter.getText().toString();
                    if(!userOTP.isEmpty() && userOTP.length() == 6){
                        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationId,userOTP);
                        verifyAuth(credential);
                    }else{
                        codeEnter.setError("Please enter valid OTP");
                    }

                }
            }
        });
    }

    private void verifyAuth(PhoneAuthCredential credential) {
        fAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register_mobile.this, "Authentication Successful", Toast.LENGTH_SHORT).show();

                } else{
                    Toast.makeText(Register_mobile.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void requestOTP(String phoneNum) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNum, 60L, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                progressBar.setVisibility(View.GONE);
                state.setVisibility(View.GONE);
                codeEnter.setVisibility(View.VISIBLE);
                verificationId = s;
                token = forceResendingToken;
                nextBtn.setText("Verify");
                verficationInProgress = true;
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
            }

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

                Toast.makeText(Register_mobile.this, "Sorry cannot create message"+ e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}