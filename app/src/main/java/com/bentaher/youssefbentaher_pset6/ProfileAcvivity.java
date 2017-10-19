package com.bentaher.youssefbentaher_pset6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileAcvivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    FirebaseUser user;

    TextView mailAdres, cityText;
    EditText city;
    Button citySearch,logOut;
    ListView lstView;
    String cityString;

    private ArrayList<Weer> weerArray;
    private listAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_acvivity);

        city = (EditText) findViewById(R.id.stad);
        mailAdres = (TextView) findViewById(R.id.mailText);
        citySearch = (Button) findViewById(R.id.stadPlus);
        logOut = (Button) findViewById(R.id.uitloggen);
        cityText = (TextView) findViewById(R.id.uwstad);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mailAdres.setText(user.getEmail());
        getSignedIn();

        myRef = FirebaseDatabase.getInstance().getReference();

        getCity();
        citySearch.setOnClickListener(new ProfileAcvivity.cityListener());
        logOut.setOnClickListener(new ProfileAcvivity.cityListener());





    }

    //onlicklistener om de stad aan te vragen bij de API.
    public class cityListener implements View.OnClickListener {

        public cityListener(){
        }

        @Override
        public void onClick(View view) {

            if(view == citySearch){
                String cityName = city.getText().toString();
                myRef.child(user.getUid()).setValue(cityName);


                ArrayList<Weer> weer = new ArrayList<>();
                String adam = cityName;

                if(adam.isEmpty() && cityString.isEmpty()){
                    Toast.makeText(ProfileAcvivity.this, "Voer een stad in",
                            Toast.LENGTH_SHORT).show();
                }
                else if(adam.isEmpty()&& !cityString.isEmpty()){
                    TrackAsyncTask asyncTask = new TrackAsyncTask(ProfileAcvivity.this);
                    asyncTask.execute(cityString);
                }
                else{
                    TrackAsyncTask asyncTask = new TrackAsyncTask(ProfileAcvivity.this);
                    asyncTask.execute(adam);
                }
            }

            if(view == logOut){
                mAuth.signOut();
                startActivity(new Intent(ProfileAcvivity.this, MainActivity.class));

            }




        }
    }

    public  void getCity(){
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child(user.getUid()).getValue(String.class);

                if(!value.isEmpty()){
                    cityText.setText("uw gekozen stad is: " + value);
                }
                cityString = value;

                Log.i("TAG", "Value is: " + cityString);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });



    }

    public void getSignedIn(){
        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, ProfileAcvivity.class));
        }
    }

}
