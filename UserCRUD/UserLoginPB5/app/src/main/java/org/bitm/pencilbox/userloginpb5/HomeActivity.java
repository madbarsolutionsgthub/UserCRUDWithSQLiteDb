package org.bitm.pencilbox.userloginpb5;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.bitm.pencilbox.userloginpb5.Database.EmployeeDataSource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private EditText nameET, idET, designationET, salaryET;
    private List<BaseSalariedEmployee> baseSalariedEmployees;
    private Button regBtn;
    private List<String>skills = new ArrayList<>();
    private String gender = "Male";
    private RadioGroup rg;
    private Spinner citySP;
    private String city;
    private String dob;
    private Calendar calendar;
    private int year, month, dayOfMonth;
    private EmployeeDataSource source;
    private int selectedEmpId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        source = new EmployeeDataSource(this);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        baseSalariedEmployees = new ArrayList<>();
        citySP = findViewById(R.id.citySP);
        rg = findViewById(R.id.radioGroup);
        nameET = findViewById(R.id.empNameET);
        idET = findViewById(R.id.empIdET);
        designationET = findViewById(R.id.empDesignationET);
        salaryET = findViewById(R.id.empSalaryET);
        regBtn = findViewById(R.id.registerBtn);

        skills.add("Bangla");
        final String[]cities = getResources().getStringArray(R.array.cities);
        Intent intent = getIntent();
        selectedEmpId = intent.getIntExtra("empId",0);
        if(selectedEmpId > 0){
            BaseSalariedEmployee bse = source.getEmployeeById(selectedEmpId);
            nameET.setText(bse.getEmpName());
            designationET.setText(bse.getEmpDesg());
            regBtn.setText("Update");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,cities);
        citySP.setAdapter(adapter);
        citySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //city = cities[position];
                city = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = findViewById(checkedId);
                gender = rb.getText().toString();
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString();
                String id = idET.getText().toString();
                String designation = designationET.getText().toString();
                String salary = salaryET.getText().toString();

                if(selectedEmpId > 0){
                    BaseSalariedEmployee bse = new BaseSalariedEmployee(name,selectedEmpId,designation);
                    boolean status = source.updateEmployee(bse);
                    if(status){
                        Toast.makeText(HomeActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(HomeActivity.this,ListActivity.class);
                        startActivity(intent1);
                    }else{
                        Toast.makeText(HomeActivity.this, "could not update", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    //BaseSalariedEmployee bse = new BaseSalariedEmployee(name,id,designation,salary,gender,skills,city,dob);
                    //Intent intent1 = new Intent(HomeActivity.this,DetailsActivity.class);
                    BaseSalariedEmployee bse = new BaseSalariedEmployee(name,designation);
                    boolean status = source.insertNewEmployee(bse);
                    if(status){
                        Toast.makeText(HomeActivity.this, "saved", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(HomeActivity.this,ListActivity.class);
                        startActivity(intent1);
                    }else{
                        Toast.makeText(HomeActivity.this, "could not save", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });

    }

    public void register(View view) {
        /*String name = nameET.getText().toString();
        String id = idET.getText().toString();
        String designation = designationET.getText().toString();
        String salary = salaryET.getText().toString();

        BaseSalariedEmployee bse = new BaseSalariedEmployee(name,id,designation,salary);
        nameET.setText("");
        idET.setText("");
        designationET.setText("");
        salaryET.setText("");
        baseSalariedEmployees.add(bse);

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();*/
    }

    public void showEmployees(View view) {
        for(BaseSalariedEmployee b : baseSalariedEmployees){
            Log.e("emp", "Employee Name: "+b.getEmpName());
            Log.e("emp", "Employee ID: "+b.getEmpId());
            Log.e("emp", "Employee Designation: "+b.getEmpDesg());
            Log.e("emp", "Employee Salary: "+b.getEmpSalary());
            Log.e("emp", "----------------------------");
        }
    }

    public void checkSkill(View view) {
        CheckBox cb = (CheckBox) view;
        boolean status = cb.isChecked();
        String selectedSkill = cb.getText().toString();
        if(status){
            skills.add(selectedSkill);
        }else{
            skills.remove(selectedSkill);
        }
    }

    public void selectDate(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,listener,year,month,dayOfMonth);
        datePickerDialog.show();
    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(year,month,dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dob = sdf.format(calendar.getTime());
            ((Button)findViewById(R.id.dob)).setText(dob);
        }
    };
}
