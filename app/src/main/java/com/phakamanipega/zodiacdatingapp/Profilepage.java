package com.phakamanipega.zodiacdatingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profilepage extends AppCompatActivity implements View.OnClickListener {

    RadioGroup radiogroup;
    RadioButton radioButton;
    TextView textViewemail;
    Button button;
    public Button loggingout;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(Profilepage.this, Loginpage.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewemail.setText("Welcome " + user.getEmail());

        radiogroup = findViewById(R.id.Firesigns);
        radiogroup = findViewById(R.id.Watersigns);
        radiogroup = findViewById(R.id.Airsigns);
        radiogroup = findViewById(R.id.Earthsigns);
              textViewemail = findViewById(R.id.SuntxtView );
       // button = findViewById(R.id.look);
            //  loggingout = findViewById(R.id.logoutbtn);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radiogroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                 textViewemail.setText("Your choice: " + radioButton.getText());

            }
        });





    }


    public void checkbutton(View view){
        int radioId = radiogroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected Radio Button:" + radioButton.getText(), Toast.LENGTH_LONG).show();

    }


    @Override
    public void onClick(View view) {

        if(view == loggingout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(Profilepage.this, Loginpage.class));
        }

    }
}
