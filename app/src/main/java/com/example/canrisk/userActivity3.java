package com.example.canrisk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class userActivity3 extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user3);

        TextView tv_risk = findViewById(R.id.tv_risk);
        TextView tv_text = findViewById(R.id.tv_text);
        Button goHome = findViewById(R.id.goHome);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int score = extras.getInt("risk");
            tv_risk.setText("Your risk score is " + score);

            if (score <= 21) {
                tv_text.setText("Lower than 21 -> low risk\n" +
                        "Your risk of having pre-diabetes or type 2 diabetes is fairly low, " +
                        "though it always pays to maintain a healthy lifestyle.");
            } else if (score >= 22 && score <= 32) {
                tv_text.setText("21-32 -> moderate risk\n" +
                        "Based on your identified risk factors, your risk of having pre-diabetes or type 2 diabetes " +
                        "is moderate. You may wish to consult with a health care practitioner about your risk of developing diabetes.");
            } else if (score >= 33) {
                tv_text.setText("33 and over -> high risk\n" +
                        "Based on your identified risk factors, your risk of having pre-diabetes or type 2 diabetes is high. " +
                        "You may wish to consult with a health care practitioner to discuss getting your blood sugar tested.");
            }
        }
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
