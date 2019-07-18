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

public class Profilepage extends AppCompatActivity {

    TextView SuntxtView, MoontxtView, RisingtxtView;
    public Button button_Matches, button_Messages, button_Logout;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);


        SuntxtView = findViewById(R.id.SuntxtView);
        MoontxtView = findViewById( R.id.MoontxtView );
        RisingtxtView = findViewById( R.id.RisingtxtView );
        button_Logout = findViewById( R.id.button_Logout );

        firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(Profilepage.this, Loginpage.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
      //  textViewemail.setText("Welcome " + user.getEmail());



        button_Matches.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent matchesActivity = new Intent( Profilepage.this, MatchesActivity.class );
                startActivity( matchesActivity );
            }
        } );



        button_Messages.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chattingActivity = new Intent(Profilepage.this, ChattingActivity.class );
                startActivity( chattingActivity );

            }
        } );




        button_Logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(Profilepage.this, Loginpage.class));

            }
        } );

    }




}
