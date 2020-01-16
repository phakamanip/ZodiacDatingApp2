package com.phakamanipega.zodiacdatingapp;

import android.app.ProgressDialog;
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

public class Loginpage extends AppCompatActivity implements View.OnClickListener{

    public EditText EMAIL;
    public EditText PASSWORD;
    public Button Gotoprofile;
    public Button gotorestistration;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.loginPage );


                try {
            if(firebaseAuth.getCurrentUser() != null){
                startActivity(new Intent(getApplicationContext(),Profilepage.class));
                finish();

            }  } catch (Exception e){
                    Toast.makeText(Loginpage.this, "check firebaseAuth", Toast.LENGTH_LONG).show();
                }

            EMAIL = (EditText) findViewById(R.id.enteremail);
            PASSWORD = (EditText) findViewById(R.id.enterpassword);
            Gotoprofile = (Button) findViewById(R.id.enterbtn);
            gotorestistration= (Button) findViewById(R.id.registerbtn);

            Gotoprofile.setOnClickListener(this);
            gotorestistration.setOnClickListener(this);


            firebaseAuth = FirebaseAuth.getInstance();
        ProgressDialog progressDialog = new ProgressDialog(this);


    }






    private void userLogin(){


        String email = EMAIL.getText().toString().trim();
        String password = PASSWORD.getText().toString().trim();

        if (TextUtils.isEmpty(password)) {//stops further action
            Toast.makeText(this, "please enter PASSWORD", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "please enter EMAIL", Toast.LENGTH_LONG).show();
            return;
        }
//
//        progressDialog.setMessage("Logging you in.......");
//        progressDialog.show();




        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), Profilepage.class));
                        }
                        else {
                            Toast.makeText(Loginpage.this,"please try logging in again", Toast.LENGTH_LONG).show();

                        }
//                        progressDialog.dismiss();


                    }

                });





    }

    @Override
    public void onClick(View view) {
            try{
        if(view == Gotoprofile){
            userLogin();
        }

        if(view == gotorestistration){
            startActivity(new Intent(Loginpage.this, MainActivity.class));
            finish();


        }
            } catch (Exception e){
                Toast.makeText(Loginpage.this, "Something about click to activity button", Toast.LENGTH_LONG).show();

            }
    }
}
