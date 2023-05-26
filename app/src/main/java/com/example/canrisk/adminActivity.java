package com.example.canrisk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class adminActivity extends AppCompatActivity {
    private TextView tvTotalCount;
    private TextView tvLowRiskMale;
    private TextView tvModerateRiskMale;
    private TextView tvHighRiskMale;
    private TextView tvLowRiskFemale;
    private TextView tvModerateRiskFemale;
    private TextView tvHighRiskFemale;
    private Button logout_button;
    private MyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        db = new MyDB(this);
        logout_button = findViewById(R.id.logout_button);
        tvTotalCount = findViewById(R.id.totalCount);
        tvLowRiskMale = findViewById(R.id.tvLowRiskMale);
        tvModerateRiskMale = findViewById(R.id.tvLowRiskFemale);
        tvHighRiskMale = findViewById(R.id.tvModerateRiskMale);
        tvLowRiskFemale = findViewById(R.id.tvModerateRiskFemale);
        tvModerateRiskFemale = findViewById(R.id.tvHighRiskMale);
        tvHighRiskFemale = findViewById(R.id.tvHighRiskFemale);
        List<Integer> userCounts = db.getUserCountsByRiskGroup();

        int total = db.getTotal();
        tvTotalCount.setText("Total count is: " + total);
        if (userCounts.size() == 6) {
            int lowRiskMaleCount = userCounts.get(0);
            int moderateRiskMaleCount = userCounts.get(1);
            int highRiskMaleCount = userCounts.get(2);
            int lowRiskFemaleCount = userCounts.get(3);
            int moderateRiskFemaleCount = userCounts.get(4);
            int highRiskFemaleCount = userCounts.get(5);

            tvLowRiskMale.setText("Low Risk (Male): " + lowRiskMaleCount);
            tvModerateRiskMale.setText("Moderate Risk (Male): " + moderateRiskMaleCount);
            tvHighRiskMale.setText("High Risk (Male): " + highRiskMaleCount);
            tvLowRiskFemale.setText("Low Risk (Female): " + lowRiskFemaleCount);
            tvModerateRiskFemale.setText("Moderate Risk (Female): " + moderateRiskFemaleCount);
            tvHighRiskFemale.setText("High Risk (Female): " + highRiskFemaleCount);
        }
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
