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

public class SignIn extends AppCompatActivity {

    EditText editTextName, editTextPassword, editTextPhone;
    Button buttonSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextPassword = (EditText) findViewById(R.id.passWord_EditText);
        editTextName = (EditText) findViewById(R.id.name_EditText);
        editTextPhone = (EditText) findViewById(R.id.phone_EditText);
        buttonSignIn = (Button) findViewById(R.id.signIn_Button);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tableUser = database.getReference("User");

        final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
        mDialog.setMessage("Please wait");
        mDialog.show();

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Check if user already exists
                        if (dataSnapshot.child(editTextPhone.getText().toString()).exists()){
                            //Getting user information
                            mDialog.dismiss();
                            User user = dataSnapshot.child(editTextPhone.getText().toString()).getValue(User.class);
                            if (user.getPassWord().equals(editTextPassword.getText().toString())){
                                Toast.makeText(SignIn.this,"Sign In successfully", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(SignIn.this,"Sign In failed", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this,"User doesn't exist", Toast.LENGTH_SHORT).show();
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
