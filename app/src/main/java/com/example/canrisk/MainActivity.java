package com.example.canrisk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    String password = "Password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button adminButton = findViewById(R.id.admin_button);
        Button userButton = findViewById(R.id.user_button);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, userActivity1.class);
                startActivity(intent);
            }
        });
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userButton.setVisibility(View.GONE);
                adminButton.setVisibility(View.GONE);
                LinearLayout mRlayout = (LinearLayout) findViewById(R.id.Linear);
                EditText myEditText = new EditText(MainActivity.this);
                myEditText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                myEditText.setHint("Please Enter your password");
                mRlayout.addView(myEditText);
                Button btnTag = new Button(MainActivity.this);
                btnTag.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                btnTag.setText("Login");


                //add button to the layout
                mRlayout.addView(btnTag);
                Intent intent2 = new Intent(MainActivity.this, adminActivity.class);
                btnTag.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (myEditText.getText().toString().equals("Password")){
                            startActivity(intent2);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            myEditText.setText("");
                        }
                    }
                });
            }
        });
    }
}