package com.bentaher.youssefbentaher_pset6;

/**
 * Created by Youssef on 21/09/2017.
 * In de LoginActivity kan men inloggen. Mocht de gebruiker al ingelogd zijn, dan word hij direct
 * naar de profielpagina gestuurd.
 */

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText mail, password;
    TextView toregister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.inloggen);
        toregister = (TextView) findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();
        getSignedIn();

        login.setOnClickListener(new LoginActivity.Login());
        toregister.setOnClickListener(new LoginActivity.Login());
    }

    //onlicklistener om te checken of je registreert of gaat inloggem
    public class Login implements View.OnClickListener {

        public Login(){
        }

        @Override
        public void onClick(View view) {
            if(view == login){
                Log.i("check", "inloggen");
                loginUser();

            }
            if(view == toregister){
                Log.i("check", "registreren");
                Intent jumppage = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(jumppage);
            }

        }
    }

    //Deze functie word aangeroepen om de user in te loggen.
    public void loginUser(){

        mail = (EditText) findViewById(R.id.mailadres);
        password = (EditText) findViewById(R.id.wachtwoord);

        String email = mail.getText().toString().trim();
        String pass = password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("check", "signInWithEmail:onComplete:" + task.isSuccessful());
                        Toast.makeText(LoginActivity.this, "inloggen gelukt",
                                Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), ProfileAcvivity.class));

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("check", "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, "inloggen mislukt",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    //Deze functie controleert of de gebruiker al ingelogd is.
    public void getSignedIn(){
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileAcvivity.class));
            Toast.makeText(LoginActivity.this, "je bent al ingelogd",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
