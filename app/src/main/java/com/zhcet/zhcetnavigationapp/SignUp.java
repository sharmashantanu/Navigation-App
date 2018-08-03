package com.zhcet.zhcetnavigationapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity {
    private static final String TAG = "Sign Up ";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebase;
    private DatabaseReference mReference,mRefUsername,mRefDepartment;
    // UI references.
    private EditText mEmail,mPassword;
    private Spinner mUsername,mDepartment;
    private String email,passwd;
    private Button btnSignIn,btnSignOut,btnViewDatabase,btncreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Spinner spinner = (Spinner) findViewById(R.id.input_name);
        ArrayAdapter<CharSequence> cadapter = ArrayAdapter.createFromResource(this,R.array.input_name_computers, android.R.layout.simple_spinner_item);
        cadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(cadapter);

        Spinner spinner1 = (Spinner) findViewById(R.id.input_departments);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.departments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        //declare buttons and edit texts in oncreate
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        btnSignIn = (Button) findViewById(R.id.email_sign_in_button);
      //  btnSignOut = (Button) findViewById(R.id.email_sign_out_button);
       // btnViewDatabase = (Button) findViewById(R.id.view_items_screen);
        btncreate=(Button)findViewById(R.id.email_create_account_button);
        mAuth = FirebaseAuth.getInstance();

        mUsername=(Spinner)findViewById(R.id.input_name);
        mDepartment=(Spinner)findViewById(R.id.input_departments);

       mReference=FirebaseDatabase.getInstance().getReference().child("Users").child("Register");
        //mRefUsername=FirebaseDatabase.getInstance().getReference();
        mRefDepartment=FirebaseDatabase.getInstance().getReference();


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoSignin=new Intent(SignUp.this,Login.class);
                startActivity(gotoSignin);

            }
        });


        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                createAccount(mEmail.getText().toString(), mPassword.getText().toString());
            }
        });

    }

    public void createAccount(final String email, final String password){

        if (!validateForm()) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if(task.isSuccessful()){
                        Log.d(TAG,"TASK SUccesfull");
                        //  createAccount(email,password);
                        Toast.makeText(getApplicationContext(), "successfully account is created", Toast.LENGTH_SHORT).show();
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                            String email_id = email;
                            String username_id = mUsername.getSelectedItem().toString();
                            String mDep = mDepartment.getSelectedItem().toString();


                            Coordinate co=new Coordinate(username_id,"23.767679","78.987878");
                            Log.d("hello",co.getLatitude()+co.getLongitude()+co.getName());
                            String ee=co.getLatitude();
                            HashMap<String,Coordinate> users = new HashMap<>();
                            users.put(email_id,new Coordinate(username_id,"23.767679","78.987878"));

                            mReference.child(encoded(email)).setValue(co);


                        Toast.makeText(getApplicationContext(), "CORRECT", Toast.LENGTH_SHORT).show();


                        startActivity(new Intent(SignUp.this,Login.class));

                    }
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUp.this,"auth failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

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

/*
    @Override
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
*/
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }



public String encoded(String email){

    return email.replace(".",",");



}
}