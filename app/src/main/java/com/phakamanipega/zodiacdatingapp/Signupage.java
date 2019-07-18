package com.phakamanipega.zodiacdatingapp;


import
android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import org.w3c.dom.Text;


public class Signupage extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    ProgressBar progressBar;
    public EditText password;
    public EditText nickname;
    public EditText dob;
    public EditText email;
    public Button save;
    public Button back;
    public ProgressDialog progressDialog;
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


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        firebaseAuth = FirebaseAuth.getInstance();
        password = (EditText) findViewById(R.id.etpassword);
        nickname = (EditText) findViewById(R.id.etnickname);
        email = (EditText) findViewById(R.id.etemail);
        save = (Button) findViewById(R.id.svbutton);
        back = (Button) findViewById(R.id.bckbutton);



        save.setOnClickListener(this);
        back.setOnClickListener(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

//                Intent loggedin = new Intent(Signupage.this, Loginpage.class);
//                startActivity(loggedin);

            }
        });
    }



       public void Zsigns(){


        String Aquarius = dob.getText().toString().trim();

        if(dob.getText().toString()== "11 January, 30 December"){
            Toast.makeText(this, "Welcome Aquarius", Toast.LENGTH_LONG).show();

        }

        }


        public void onStart(){
            super.onStart();

            firebaseAuth.addAuthStateListener(mAuthListener);

        }



        public void registerUser () {
            String NICKNAME = nickname.getText().toString().trim();
            String PASSWORD = password.getText().toString().trim();
            String EMAIL = email.getText().toString().trim();
            String DOB = dob.getText().toString().trim();

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


                progressBar.setVisibility(View.VISIBLE);
                progressDialog.show();

                firebaseAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {//user entered successfully
                                    progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Signupage.this, "Well done, ure registersted", Toast.LENGTH_LONG).show();


                                } else {
                                    Toast.makeText(getApplicationContext() , "Something went wrong, try again", Toast.LENGTH_LONG).show();
                                }

                            }


                        });

            }


    @Override
    public void onClick(View v) {
        if(v == save){

            registerUser();
        }

        if(v == back) {

            startActivity(new Intent(Signupage.this, MainActivity.class));
            finish();

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
