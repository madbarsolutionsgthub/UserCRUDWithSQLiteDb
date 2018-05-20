package org.bitm.pencilbox.userloginpb5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mobile App on 5/9/2018.
 */

public class BaseSalariedEmployee implements Serializable{
    private String empName;
    private int empId;
    private String empDesg;
    private String empSalary;
    private String gender;
    private List<String>skills;
    private String city;
    private String dob;

    private static List<BaseSalariedEmployee>empList = new ArrayList<>();

    public BaseSalariedEmployee(String empName, int empId, String empDesg, String empSalary, String gender, List<String> skills, String city, String dob) {
        this.empName = empName;
        this.empId = empId;
        this.empDesg = empDesg;
        this.empSalary = empSalary;
        this.gender = gender;
        this.skills = skills;
        this.city = city;
        this.dob = dob;
    }

    public BaseSalariedEmployee(String empName, String empDesg) {
        this.empName = empName;
        this.empDesg = empDesg;
    }

    public BaseSalariedEmployee(String empName, int empId, String empDesg) {
        this.empName = empName;
        this.empId = empId;
        this.empDesg = empDesg;
    }

    public String getEmpName() {
        return empName;
    }

    public int getEmpId() {
        return empId;
    }

    public String getEmpDesg() {
        return empDesg;
    }

    public String getEmpSalary() {
        return empSalary;
    }

    public String getGender() {
        return gender;
    }

    public List<String> getSkills() {
        return skills;
    }

    public String getCity() {
        return city;
    }

    public String getDob() {
        return dob;
    }

    public static void addEmpToList(BaseSalariedEmployee bse){
        empList.add(bse);
    }

    public static List<BaseSalariedEmployee>getEmpList(){
        return empList;
    }

}
