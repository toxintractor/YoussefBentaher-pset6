package com.bentaher.youssefbentaher_pset6;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button register;
    EditText mail, password, hpassword;
    TextView tosignin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(getApplicationContext(), ForecastActivity.class));

        register = (Button) findViewById(R.id.maakaccount);
        tosignin = (TextView) findViewById(R.id.inlog);

        register.setOnClickListener(new MainActivity.Register());
        tosignin.setOnClickListener(new MainActivity.Register());

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("logedin", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("logedout", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

    }

    //onlicklistener om te checken of je registreert of gaat inloggem
    public class Register implements View.OnClickListener {

        public Register(){
        }

        @Override
        public void onClick(View view) {
            if(view == register){
                Log.i("check", "registreren");
                registerUser();

            }
            if(view == tosignin){
                Log.i("check", "inloggen");
                Intent jumppage = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(jumppage);
            }

        }
    }

    //Deze methode registreert de gebruiker.
    public void registerUser(){

        mail = (EditText) findViewById(R.id.mailadres);
        password = (EditText) findViewById(R.id.wachtwoord);
        hpassword = (EditText) findViewById(R.id.hwachtwoord);

        String email = mail.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String hpass = hpassword.getText().toString().trim();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(hpass)){
            Toast.makeText(this, "Email of wachtwoord veld leeg", Toast.LENGTH_LONG).show();
            return;
        }
        else if(!pass.equals(hpass)){
            Toast.makeText(this, "Wachtwoorden komen niet overeen", Toast.LENGTH_LONG).show();
            return;
        }

        Log.i("check", email + " " + pass);

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("succes", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "registeren mislukt",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

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
}
