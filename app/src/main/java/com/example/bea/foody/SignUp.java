package com.example.bea.foody;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    EditText editTextPhone, editTextName, editTextPassword;
    Button buttonSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextName = (EditText) findViewById(R.id.name_EditText);
        editTextPassword = (EditText) findViewById(R.id.passWord_EditText);
        editTextPhone = (EditText) findViewById(R.id.phone_EditText);

        buttonSignUp = (Button) findViewById(R.id.signUp_Button);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tableUser = database.getReference("User");

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please wait");
                mDialog.show();

                tableUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Check if user already exists
                        if (dataSnapshot.child(editTextPhone.getText().toString()).exists()){
                            //Getting user information
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this,"Phone Number is already register", Toast.LENGTH_SHORT).show();
                        }else {
                            mDialog.dismiss();
                            User user = new User(editTextName.getText().toString(),editTextPassword.getText().toString());
                            tableUser.child(editTextPhone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this,"Sign Up successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }


                    }

                    @Override

                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
