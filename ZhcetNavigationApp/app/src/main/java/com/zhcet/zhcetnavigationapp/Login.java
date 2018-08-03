package com.zhcet.zhcetnavigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button btnsign;
    private DatabaseReference fire_reference;
   // private FirebaseDatabase mFirebaseinstance;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnsign=(Button)findViewById(R.id.signBtn);
        mEmail=(EditText)findViewById(R.id.email);
        mPassword=(EditText)findViewById(R.id.password);
        fire_reference= FirebaseDatabase.getInstance().getReference().child("Users").child("Register");
        mAuth = FirebaseAuth.getInstance();

        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String str=mEmail.getText().toString();
                final String str2=mPassword.getText().toString();
                computer sc=new computer();
                sc.setemail(str);
              //  final String mUsername=Username.getText().toString();

                if(str.equals("")||str2.equals(""))//mUsername.............
                    Toast.makeText(getApplicationContext(), "fill up all details....", Toast.LENGTH_SHORT).show();
                else
                {
                    signIn(mEmail.getText().toString(), mPassword.getText().toString());
                    //Toast.makeText(getApplicationContext(), "correct hai....", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    String uid = user.getUid();
                    Toast.makeText(getApplicationContext(),"id="+uid,Toast.LENGTH_SHORT).show();
                    String password = user.getDisplayName();
                    String email = user.getEmail();
                    Toast.makeText(getApplicationContext(),"email:"+email+password,Toast.LENGTH_SHORT).show();
                   // Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(getApplicationContext(),"Successfully authentized",Toast.LENGTH_SHORT).show();

                } else {
                    // User is signed out
                   // Log.d(TAG, "onAuthStateChanged:signed_out");
                   // toastMessage("Successfully signed out.");
                }
                // ...
            }
        };
    }

    private void signIn(String email, String password) {
       // Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }
        // showProgressDialog();
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                          //  Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(getApplicationContext(),"successfully signed in",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user !=null){
                                String password = user.getDisplayName();
                                String email = user.getEmail();
                                Toast.makeText(getApplicationContext(),"email:"+email+password,Toast.LENGTH_SHORT).show();
                              //  Uri photoUrl = user.getPhotoUrl();

                                // The user's ID, unique to the Firebase project. Do NOT use this value to
                                // authenticate with your backend server, if you have one. Use
                                // FirebaseUser.getToken() instead.
                                String uid = user.getUid();
                                Toast.makeText(getApplicationContext(),"id="+uid,Toast.LENGTH_SHORT).show();
                                Intent gototrack=new Intent();
                                gototrack.setClass(Login.this,GpsTeacherTracking.class);
                                startActivity(gototrack);
                            }
                            //    updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                           // Log.w(, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //      updateUI(null);
                        }

                        // [START_EXCLUDE]
                       /* if (!task.isSuccessful()) {
                            //     mStatusTextView.setText("auth failed");
                        }*/
                        // hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]........................................
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Required.");
            valid = false;
        } else {
            mEmail.setError(null);
        }

        String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Required.");
            valid = false;
        } else {
            mPassword.setError(null);
        }

        return valid;
    }

    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
