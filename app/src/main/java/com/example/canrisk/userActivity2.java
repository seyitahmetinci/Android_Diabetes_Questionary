package com.example.canrisk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class userActivity2 extends AppCompatActivity {
    int waistCircumference;
    Boolean doesPhysicalActivity, eatsVegetablesOrFruits, hasHighBloodPressure, hasHighBloodSugar, gaveBirthToBabies=false;
    Boolean mother=false, father=false, sibling=false, children=false;
    int white = 0, aboriginal = 3, black = 5, east_asian = 10, west_asian = 11, other = 3, ethnic_m = 0,ethnic_f = 0, ethnic = 0;
    int edu = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2);
        RadioGroup radioGroup = findViewById(R.id.radioGroup_circum);
        RadioGroup radioGroup_q5 = findViewById(R.id.radioGroup_q5);
        RadioGroup radioGroup_q6 = findViewById(R.id.radioGroup_q6);
        RadioGroup radioGroup_q7 = findViewById(R.id.radioGroup_q7);
        RadioGroup radioGroup_q8 = findViewById(R.id.radioGroup_q8);
        TextView tv_q9 = findViewById(R.id.tv_q9);
        RadioGroup radioGroup_q9 = findViewById(R.id.radioGroup_q9);
        View view1 = findViewById(R.id.view1);
        RadioButton q10_father = findViewById(R.id.radioButton_q10father);
        RadioButton q10_mother = findViewById(R.id.radioButton_q10mother);
        RadioButton q10_sibling = findViewById(R.id.radioButton_q10siblings);
        RadioButton q10_children = findViewById(R.id.radioButton_q10children);
        RadioGroup radioGroup_q11m = findViewById(R.id.radioGroup_q11m);
        RadioGroup radioGroup_q11f = findViewById(R.id.radioGroup_q11f);
        RadioGroup radioGroup_q12 = findViewById(R.id.radioGroup_q12);
        Button back_button = findViewById(R.id.back_button);

        MyDB db = new MyDB(this);


        Bundle extra = getIntent().getExtras();
        int age = extra.getInt("age");
        double bmi = extra.getDouble("bmi");
        String gender = extra.getString("gender");
        String[] choices;
        if(gender.equals("Male")) {
            choices = new String[]{"Less than 94 cm", "Between 94-102 cm", "Over 102 cm"};
        } else {
            choices = new String[]{"Less than 80 cm", "Between 80-88 cm", "Over 88 cm"};
        }



        for(int i = 0; i < choices.length; i++){
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(choices[i]);
            radioButton.setId(i);
            radioGroup.addView(radioButton);
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case 0:
                        waistCircumference = 0;
                        break;
                    case 1:
                        waistCircumference = 1;
                        break;
                    case 2:
                        waistCircumference = 2;
                        break;
                }
            }
        });

        radioGroup_q5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.findViewById(checkedId)==findViewById(R.id.radioButton_q5y)){
                    doesPhysicalActivity = true;

                }
                else {
                    doesPhysicalActivity = false;
                }
            }
        });
        radioGroup_q6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.findViewById(checkedId)==findViewById(R.id.radioButton_q6y)){
                    eatsVegetablesOrFruits = true;

                }
                else {
                    eatsVegetablesOrFruits = false;
                }
            }
        });
        radioGroup_q7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.findViewById(checkedId)==findViewById(R.id.radioButton_q7y)){
                    hasHighBloodPressure = true;

                }
                else {
                    hasHighBloodPressure = false;
                }
            }
        });
        radioGroup_q8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.findViewById(checkedId)==findViewById(R.id.radioButton_q8y)){
                    hasHighBloodSugar = true;

                }
                else {
                    hasHighBloodSugar = false;
                }
            }
        });

        if (gender.equals("Male")){
            tv_q9.setVisibility(View.GONE);
            radioGroup_q9.setVisibility(View.GONE);
            view1.setVisibility(View.GONE);
            gaveBirthToBabies = false;
        }
        else {
            radioGroup_q9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (group.findViewById(checkedId) == findViewById(R.id.radioButton_q9y)){
                        gaveBirthToBabies = true;
                    }
                    else {
                        gaveBirthToBabies = false;
                    }
                }
            });
        }

        radioGroup_q11f.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                if (radioButton.getText().equals("White")){
                    ethnic_f = white;

                }
                else if (radioButton.getText().equals("Aboriginal")){
                    ethnic_f = aboriginal;

                }
                else if (radioButton.getText().equals("Black (Afro-Caribbean)")){
                    ethnic_f = black;

                }
                else if (radioButton.getText().equals("East Asian (Chinese, Vietnamese, Filipino, Korean, etc.)")){
                    ethnic_f = east_asian;

                }
                else if (radioButton.getText().equals("South Asian (East Indian, Pakistani, Sri Lankan, etc.)")){
                    ethnic_f = west_asian;

                }
                else if (radioButton.getText().equals("Other non-white (Latin American, Arab, West Asian)")){
                    ethnic_f = other;

                }

                if (ethnic_f>ethnic_m){
                    ethnic = ethnic_f;
                }

            }
        });
        radioGroup_q11m.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton2 = group.findViewById(checkedId);
                if (group.findViewById(checkedId) == findViewById(R.id.radioButton_q11a)){
                    ethnic_m = white;

                }
                else if (group.findViewById(checkedId) == findViewById(R.id.radioButton_q11b)){
                    ethnic_m = aboriginal;

                }
                else if (group.findViewById(checkedId) == findViewById(R.id.radioButton_q11c)){
                    ethnic_m = black;

                }
                else if (group.findViewById(checkedId) == findViewById(R.id.radioButton_q11d)){
                    ethnic_m = east_asian;

                }
                else if (group.findViewById(checkedId) == findViewById(R.id.radioButton_q11e)){
                    ethnic_m = west_asian;

                }
                else if (group.findViewById(checkedId) == findViewById(R.id.radioButton_q11f)){
                    ethnic_m = other;

                }
                if (ethnic_m>ethnic_f){
                    ethnic = ethnic_m;
                }

            }
        });
        radioGroup_q12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.findViewById(checkedId)==findViewById(R.id.radioButton_q12a)){
                    edu = 5;

                }
                else if (group.findViewById(checkedId)==findViewById(R.id.radioButton_q12b)){
                    edu = 1;

                }
                else {
                    edu = 0;

                }
            }
        });



        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q10_father.isChecked()){
                    father = true;
                }
                if (q10_mother.isChecked()){
                    mother = true;
                } if (q10_children.isChecked()) {
                    children = true;
                }
                if (q10_sibling.isChecked()){
                    sibling = true;
                }
                int final_risk = calculateRisk(age, gender, bmi, waistCircumference, doesPhysicalActivity, eatsVegetablesOrFruits, hasHighBloodPressure, hasHighBloodSugar, gaveBirthToBabies, father, mother, children, sibling, ethnic, edu);
                db.addUser(final_risk, gender);
                Intent intent = new Intent(userActivity2.this, userActivity3.class);
                intent.putExtra("risk", final_risk);
                startActivity(intent);
            }
        });


    }

    int calculateRisk(int age, String gender, double bmi, int waistCircumference, boolean phys, boolean eats, boolean pressure, boolean sugar, boolean baby, boolean father, boolean mother, boolean children, boolean sibling, int ethnic, int edu){
        int risk = 0;
        if (age >=40 && age <= 44){
            risk +=0;

        }
        else if (age >=45 && age <= 54){
            risk +=7;

        }
        else if (age >=55 && age <= 64){
            risk +=13;

        }
        else if (age >=65 && age <= 100){
            risk +=15;

        }

        if (gender.equals("Male")){
            risk +=6;

        }
        else {
            risk +=0;
        }

        if (bmi <= 25){
            risk +=0;

        }
        else if (bmi >=25.1 && bmi <= 29){
            risk +=4;

        }
        else if (bmi >=29.1 && bmi <= 34){
            risk +=9;

        }
        else if (bmi >=34.1){
            risk +=14;

        }

        switch (waistCircumference){
            case 0:
                risk += 0;

                break;
            case 1:
                risk += 4;

                break;
            case 2:
                risk +=6;

                break;
        }

        if(phys==true){
            risk += 0;
        }
        else if (phys==false){
            risk += 1;
        }
        if(eats==true){
            risk += 0;
        }
        else if (eats == false){
            risk += 2;
        }

        if(pressure==true){
            risk += 4;
        }
        else if (pressure==false){
            risk += 0;
        }

        if(sugar==true){
            risk += 14;
        }
        else if (sugar == false){
            risk += 0;
        }

        if(baby==true){
            risk += 1;
        }
        else if (baby == false){
            risk += 0;
        }

        if(father==true){
            risk += 2;
        }
        if (mother==true){
            risk += 2;
        }
        if (children==true){
            risk += 2;
        }
        if (sibling==true){
            risk += 2;
        }
        risk += ethnic;
        risk += edu;
        return risk;
    }
}