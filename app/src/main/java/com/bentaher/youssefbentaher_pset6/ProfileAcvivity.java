package com.bentaher.youssefbentaher_pset6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileAcvivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    TextView mailAdres;
    EditText city;
    Button citySearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_acvivity);

        mailAdres = (TextView) findViewById(R.id.mailText);
        citySearch = (Button) findViewById(R.id.stadPlus);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        mailAdres.setText(user.getEmail());
        getSignedIn();

        myRef = FirebaseDatabase.getInstance().getReference();




    }

    //onlicklistener om de stad aan te vragen bij de API.
    public class cityListener implements View.OnClickListener {

        public cityListener(){
        }

        @Override
        public void onClick(View view) {
            city = (EditText) findViewById(R.id.stad);
        }
    }

    public void getSignedIn(){
        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, ProfileAcvivity.class));
        }
    }

}
