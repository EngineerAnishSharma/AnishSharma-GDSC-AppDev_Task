package com.example.googlelogin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edEmail,edPassword;
    Button loginBtn;
    TextView tv,loginReg;
    Boolean passwordVisible;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail=findViewById(R.id.editTextLoginUsername);
        edPassword=findViewById(R.id.editTextPassword);
        loginBtn=findViewById(R.id.buttonLogin);
        loginReg=findViewById(R.id.textViewLoginRegister);

        loginReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

//        edPassword.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                final int right=1;
//                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
//                    if(motionEvent.getRawX()>=edPassword.getRight()-edPassword.getCompoundDrawables()[right].getBounds().width()){
//                        int selection=edPassword.getSelectionEnd();
//                        if(passwordVisible){
//                            //set drawable image here
//                            edPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);
//                            // for hide password
//                            edPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                            passwordVisible=false;
//                        }else{
//                            //set drawable image here
//                            edPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);
//                            // for hide password
//                            edPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                            passwordVisible=true;
//                        }
//                        edPassword.setSelection(selection);
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=edEmail.getText().toString();
                String password=edPassword.getText().toString();
//                tv.setText(password.length()+"/20");
                Database db=new Database(getApplicationContext(),"googleUser",null,1);
                if(email.length()==0 || password.length()==0){
                    Toast.makeText(LoginActivity.this, "Fill all required information", Toast.LENGTH_SHORT).show();
                }else{
                    if(db.login(email,password)==1) {
                        Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
                        Toast.makeText(LoginActivity.this, "Welcome to GDSC", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Invalid username and password", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


    }
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(LoginActivity.this);
        alertDialog.setTitle("Exit App");
        alertDialog.setMessage("Do you want to exit app?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }
}