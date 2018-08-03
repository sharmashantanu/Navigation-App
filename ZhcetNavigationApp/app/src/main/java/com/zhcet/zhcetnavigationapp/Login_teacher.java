package com.zhcet.zhcetnavigationapp;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class Login_teacher extends AppCompatActivity {

    private EditText editemail,mpassword;
    private DatabaseReference mFirebasedatabasereference;
    private FirebaseDatabase mFirebaseinstance;
    private Button bregister;
    private String userid;
    String setchild;
    static int setnamer;
    String str="";
    String str2="";
    FirebaseDatabase firedata;
    private  FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TextView mPasswordField;
    private TextView mStatusTextView;
    private TextView mDetailTextView;

    private Button btnSignIn,btnSignOut,btnViewDatabase;

    //firedata=FirebaseDatabase.getInstance();

    //firedata=FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teacher);
        bregister=(Button)findViewById(R.id.email_create_account_button);
        editemail=(EditText)findViewById(R.id.email);

        mpassword=(EditText)findViewById(R.id.password);

/*
        btnSignIn = (Button) findViewById(R.id.email_sign_in_button);
        btnSignOut = (Button) findViewById(R.id.email_sign_out_button);
        btnViewDatabase = (Button) findViewById(R.id.view_items_screen);
*/


        mAuth =FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                  //  Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                   // toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                   // Log.d(TAG, "onAuthStateChanged:signed_out");
                   // toastMessage("Successfully signed out.");
                }
            }
        };

        mFirebasedatabasereference=FirebaseDatabase.getInstance().getReference().child("Users").child("Register");

        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d("problem","current");
                str=editemail.getText().toString().trim();
                str2=mpassword.getText().toString().trim();
                HashMap<String,String> adder=new HashMap<String, String>();
                HashMap<String,EditText>addon=new HashMap<String,EditText>();
                adder.put("Username",str);
                adder.put("Password",str2);
               // String key="database1";
               // fire_reference.setValue(key);
               // fire_reference.child("cse").setValue("nsnsh");
               setnamer+=1;
                setchild=String.valueOf(setnamer);
                mFirebasedatabasereference.child(setchild).setValue(adder).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"succesfully registered",Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

               // fire_reference.addValueEventListener(new )
                Bundle bn=new Bundle();
                bn.putString("name",String.valueOf(setnamer));

               // bn.putString("name",set.getText().toString());
               Intent in=new Intent(getApplicationContext(),Login.class);
               in.putExtras(bn);
               startActivity(in);


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void createAccount(String email, String password) {
        //Log.d(Tag,"createAccount:" + email);
        if (!validateForm()) {
            return;
        }

       // showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                          //  Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                           // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    //        Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                      //              Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
            //            hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }




    private void signIn(String email, String password) {
        //Log.d(Tag, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

     //   showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                          //  Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                          //  Log.w(TAG, "signInWithEmail:failure", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                              //      Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            mStatusTextView.setText("failed");
                        }
            //            hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    private void sendEmailVerification() {
        // Disable button
        findViewById(R.id.verify_email_button).setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        findViewById(R.id.verify_email_button).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(Login_teacher.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                           // Log.e(Tag, "sendEmailVerification", task.getException());
                            Toast.makeText(getApplicationContext(),
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }

    private boolean validateForm() {
        boolean valid = true;

        TextView mEmailField = null;
        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
       // hideProgressDialog();
        if (user != null) {
           // mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
            //        user.getEmail(), user.isEmailVerified()));
           // mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.email_password_buttons).setVisibility(View.GONE);
            findViewById(R.id.email_password_fields).setVisibility(View.GONE);
            findViewById(R.id.signed_in_buttons).setVisibility(View.VISIBLE);

            findViewById(R.id.verify_email_button).setEnabled(!user.isEmailVerified());
        } else {
            mStatusTextView.setText("signed_out");
            mDetailTextView.setText(null);

            findViewById(R.id.email_password_buttons).setVisibility(View.VISIBLE);
            findViewById(R.id.email_password_fields).setVisibility(View.VISIBLE);
            findViewById(R.id.signed_in_buttons).setVisibility(View.GONE);
        }
    }





    
    
    
    
    
/*
    mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

            // If sign in fails, display a message to the user. If sign in succeeds
            // the auth state listener will be notified and logic to handle the
            // signed in user can be handled in the listener.
            if (!task.isSuccessful()) {
                Toast.makeText(EmailPasswordActivity.this, R.string.auth_failed,
                        Toast.LENGTH_SHORT).show();
            }

            // ...
        }
    });


    mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

            // If sign in fails, display a message to the user. If sign in succeeds
            // the auth state listener will be notified and logic to handle the
            // signed in user can be handled in the listener.
            if (!task.isSuccessful()) {
                Log.w(TAG, "signInWithEmail:failed", task.getException());
                Toast.makeText(EmailPasswordActivity.this, R.string.auth_failed,
                        Toast.LENGTH_SHORT).show();
            }

            // ...
        }
    });



    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
if (user != null) {
        // Name, email address, and profile photo Url
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        // The user's ID, unique to the Firebase project. Do NOT use this value to
        // authenticate with your backend server, if you have one. Use
        // FirebaseUser.getToken() instead.
        String uid = user.getUid();
    }
*/
}