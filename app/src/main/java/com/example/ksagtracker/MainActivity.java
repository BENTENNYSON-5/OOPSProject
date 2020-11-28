package com.example.ksagtracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends NewUser {

    private Button login,toRegister;
    private EditText Username;
    private String username;
    private CallbackManager mCallbackManager;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private AccessTokenTracker accessTokenTracker;
    private LoginButton loginButton;
    private TextView textViewUser;
    DatabaseReference rref;
    private static final String TAG = "FacebookAuthentication";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         rref = FirebaseDatabase.getInstance().getReference().child("User");
        mFirebaseAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());
        textViewUser = findViewById(R.id.text_user);
        textViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoogle_login();
            }
        });
        loginButton =findViewById(R.id.login_button);
        login = (Button)findViewById(R.id.login);
        toRegister = (Button)findViewById(R.id.registering);

        Username = (EditText)findViewById(R.id.username);
        loginButton.setReadPermissions("email","public_profile");
        mCallbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG,"onSuccess"+ loginResult);
                handleFacebookToken(loginResult.getAccessToken());
                openDivision();

            }

            @Override
            public void onCancel() {
                Log.d(TAG,"onCancel");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG,"onError"+error);

            }
        });

        authStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    updateUI(user);
                }else {
                    updateUI(null);
                }
            }
        };

        accessTokenTracker= new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken==null){
                    mFirebaseAuth.signOut();
                }
            }
        };

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               username = Username.getText().toString();
               rref.child(username).child("mainlist");
               rref.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if(snapshot.exists()){
                           for(DataSnapshot dss: snapshot.getChildren())
                               mainlist.add(dss.getValue(String.class));
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
            }
        });
        toRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openRegistering();
            }
        });



    }

    private void openGoogle_login() {
        Intent i =new Intent(this,Google_login.class);
        i.putExtra("user",username);
        startActivity(i);
    }


    private void openDivision() {
        Intent i =new Intent(this,Divisions.class);
        i.putExtra("user",username);
        startActivity(i);
    }

    private void handleFacebookToken(AccessToken token) {
        Log.d(TAG,"handleFacebookToken"+ token);

        AuthCredential credential= FacebookAuthProvider.getCredential((token.getToken()));
        Task<AuthResult> authentication_failed = mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "sign in with credential : successful");
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    Log.d(TAG, "sign in with credential : failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                    updateUI(null);

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateUI(FirebaseUser user) {
        if(user!=null){
            textViewUser.setText(user.getDisplayName());
            if(user.getPhotoUrl()!=null) {
                String photoUrl = user.getPhotoUrl().toString();
                photoUrl = photoUrl + "?type=large";
            }else {
                textViewUser.setText("");
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null){
            mFirebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    public void openRegistering(){
             Intent i =new Intent(this,Registering.class);
             i.putExtra("user",username);
             startActivity(i);
    }

}
