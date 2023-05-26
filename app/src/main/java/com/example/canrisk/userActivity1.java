package com.example.canrisk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class userActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user1);
        EditText et_age = findViewById(R.id.et_age);
        EditText et_height = findViewById(R.id.et_height);
        EditText et_weight = findViewById(R.id.et_weight);
        Spinner spinner_gender = findViewById(R.id.genderSpinner);
        Button submit_button = findViewById(R.id.submit_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = Integer.parseInt(et_age.getText().toString());
                int height = Integer.parseInt(et_height.getText().toString());
                int weight = Integer.parseInt(et_weight.getText().toString());
                String gender = spinner_gender.getSelectedItem().toString();
                double bmi = calculateBMI(weight, height);

                Intent intent = new Intent(userActivity1.this, userActivity2.class);

                intent.putExtra("age", age);
                intent.putExtra("gender", gender);
                intent.putExtra("height", height);
                intent.putExtra("weight", weight);
                intent.putExtra("bmi", bmi);
                startActivity(intent);


                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }
    public double calculateBMI(double weight, double height) {
        return (weight / height / height) * 10000;
    }
}