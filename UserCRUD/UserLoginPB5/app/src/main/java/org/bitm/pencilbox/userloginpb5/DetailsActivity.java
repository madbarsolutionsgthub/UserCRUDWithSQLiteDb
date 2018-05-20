package org.bitm.pencilbox.userloginpb5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private TextView nameTV, idTV, desgTV, salaryTV, skillTV, genderTV, cityTV;
    private int position;
    private BaseSalariedEmployee bse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nameTV = findViewById(R.id.empNameTV);
        idTV = findViewById(R.id.empIdTV);
        desgTV = findViewById(R.id.empDesignationTV);
        salaryTV = findViewById(R.id.empSalaryTV);
        skillTV = findViewById(R.id.empSkillTV);
        genderTV = findViewById(R.id.empGenderTV);
        cityTV = findViewById(R.id.empCityTV);
        Intent intent = getIntent();
        position = intent.getIntExtra("emp",-1);
        if(position >= 0){
            bse = BaseSalariedEmployee.getEmpList().get(position);
            nameTV.setText(bse.getEmpName());
            idTV.setText(bse.getEmpId());
            desgTV.setText(bse.getEmpDesg());
            salaryTV.setText(bse.getEmpSalary());
            List<String>skills = new ArrayList<>();
            skills = bse.getSkills();
            String skillString = TextUtils.join(", ",skills);
            skillTV.setText(skillString);
            genderTV.setText(bse.getGender());
            cityTV.setText(bse.getCity());
        }

    }
}
