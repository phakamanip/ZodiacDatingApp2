package com.phakamanipega.zodiacdatingapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Signupage extends Activity  {

    public EditText email, nickname, dateofbirth, password, confirmpassword;
    public Button save;
    public Button back;
    public FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()== null ){
                    Toast.makeText(Signupage.this, "please register", Toast.LENGTH_LONG).show();
                  //  startActivity(new Intent(Signupage.this, Loginpage.class));
                    }
                    }
        };





        firebaseAuth = FirebaseAuth.getInstance();
        password = (EditText) findViewById(R.id.etpassword);
        nickname = (EditText) findViewById(R.id.etnickname);
        email = (EditText) findViewById(R.id.etemail);
        save = (Button) findViewById(R.id.svbutton);
        back = (Button) findViewById(R.id.bckbutton);



        save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();

                Intent gotoProfile = new Intent( Signupage.this, Profilepage.class );
                startActivity( gotoProfile );

            }
        } );

        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoMain = new Intent( Signupage.this, MainActivity.class );
                startActivity( gotoMain );
            }
        } );






    }








        public void onStart(){
            super.onStart();

            firebaseAuth.addAuthStateListener(mAuthListener);

        }



        public void registerUser () {
            String NICKNAME = nickname.getText().toString().trim();
            String PASSWORD = password.getText().toString().trim();
            String EMAIL = email.getText().toString().trim();
            String DOB = dateofbirth.getText().toString().trim();
            String CNFPASSWORD = confirmpassword.getText().toString().trim();

            if (TextUtils.isEmpty(PASSWORD)) {//stops further action
                Toast.makeText(this, "please enter PASSWORD", Toast.LENGTH_LONG).show();
                return;
            }
            if (TextUtils.isEmpty(NICKNAME)) {
                Toast.makeText(this, "please enter NICKNAME", Toast.LENGTH_LONG).show();
                return;
            }
            if (TextUtils.isEmpty(DOB)) {
                Toast.makeText(this, "please enter DOB", Toast.LENGTH_LONG).show();
                return;
            }
            if (TextUtils.isEmpty(EMAIL)) {
                Toast.makeText(this, "please enter EMAIL", Toast.LENGTH_LONG).show();
                return;
            }
            if (TextUtils.isEmpty( CNFPASSWORD )){
                Toast.makeText( this, "please confirm your password", Toast.LENGTH_LONG).show();
            }

;

                firebaseAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {//user entered successfully
                                        Toast.makeText(Signupage.this, "Well done, ure registersted", Toast.LENGTH_LONG).show();


                                } else {
                                    Toast.makeText(getApplicationContext() , "Something went wrong, try again", Toast.LENGTH_LONG).show();
                                }

                            }


                        });

            }





    }





