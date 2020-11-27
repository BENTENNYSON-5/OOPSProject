package com.example.ksagtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class Google_profile extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    TextView profile_name;
    TextView profile_email;
    TextView profile_id;
    Button sign_out;
    GoogleApiClient googleApiClient;
    GoogleSignInOptions gso;
     Object GoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInClient = GoogleSignIn.getClient(this,gso);
        setContentView(R.layout.activity_google_profile);
        profile_name = findViewById(R.id.profile_name);
        profile_email = findViewById(R.id.profile_email);
        profile_id = findViewById(R.id.profile_id);
        sign_out = findViewById(R.id.sign_out);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this , this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
         sign_out.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                     @Override
                     public void onResult(@NonNull Status status) {
                         if(status.isSuccess())
                             gotoGoogle_login();
                         else
                             Toast.makeText(Google_profile.this, "Log out failed", Toast.LENGTH_SHORT).show();
                     }
                 });
             }
         });


    }

    private void gotoGoogle_login() {
        startActivity(new Intent(Google_profile.this , Google_login.class));
        finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    public void handleSignInResult (GoogleSignInResult result) {
        if(result.isSuccess()){
            GoogleSignInAccount profile_account = result.getSignInAccount();
            profile_name.setText(profile_account.getDisplayName());
            profile_email.setText(profile_account.getEmail());
            profile_id.setText(profile_account.getId());
        }
        else
        {gotoGoogle_login();}
    }

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);

        if(opr.isDone()){
             GoogleSignInResult result = opr.get();
             handleSignInResult(result);
        }else {
             opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                 @Override
                 public void onResult(@NonNull GoogleSignInResult result) {
                     handleSignInResult(result);
                 }
             });
        }
    }
}