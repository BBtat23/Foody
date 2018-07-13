package com.example.bea.foody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button buttonSignIn,buttonSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSignIn = (Button) findViewById(R.id.signIn_Button);
        buttonSignUp = (Button) findViewById(R.id.signUp_Button);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signUpIntent = new Intent(MainActivity.this,SignUp.class);
                startActivity(signUpIntent);
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = new Intent(MainActivity.this,SignIn.class);
                startActivity(signInIntent);
            }
        });
    }
}
