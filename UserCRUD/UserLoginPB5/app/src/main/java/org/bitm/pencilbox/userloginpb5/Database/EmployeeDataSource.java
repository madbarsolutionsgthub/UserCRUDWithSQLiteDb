package org.bitm.pencilbox.userloginpb5.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.bitm.pencilbox.userloginpb5.BaseSalariedEmployee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mobile App on 5/19/2018.
 */

public class EmployeeDataSource {
    private EmployeeDatabaseHelper helper;
    private SQLiteDatabase db;

    public EmployeeDataSource(Context context){
        helper = new EmployeeDatabaseHelper(context);
    }
    public void open(){
        db = helper.getWritableDatabase();
    }
    public void close(){
        db.close();
    }

    public boolean insertNewEmployee(BaseSalariedEmployee bse){
        this.open();
        ContentValues values = new ContentValues();
        values.put(EmployeeDatabaseHelper.TBL_EMP_COL_NAME,bse.getEmpName());
        values.put(EmployeeDatabaseHelper.TBL_EMP_COL_DESG,bse.getEmpDesg());
        long insertedRow = db.insert(EmployeeDatabaseHelper.TABLE_EMPLOYEE,null,values);
        this.close();
        if(insertedRow > 0){
            return true;
        }else{
            return false;
        }
    }

    public List<BaseSalariedEmployee>getAllEmployees(){
        this.open();
        List<BaseSalariedEmployee>employees = new ArrayList<>();
        Cursor cursor = db.query(EmployeeDatabaseHelper.TABLE_EMPLOYEE,null,null,null,null,null,null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                int id = cursor.getInt(cursor.getColumnIndex(EmployeeDatabaseHelper.TBL_EMP_COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseHelper.TBL_EMP_COL_NAME));
                String desg = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseHelper.TBL_EMP_COL_DESG));
                BaseSalariedEmployee bse = new BaseSalariedEmployee(name,id,desg);
                employees.add(bse);
            }while (cursor.moveToNext());
        }
        cursor.close();
        this.close();
        return employees;
    }

    public boolean deleteEmployee(int empId){
        this.open();
        int deletedRow = db.delete(EmployeeDatabaseHelper.TABLE_EMPLOYEE,EmployeeDatabaseHelper.TBL_EMP_COL_ID+ " = "+empId,null);
        this.close();
        if(deletedRow > 0){
            return true;
        }else{
            return false;
        }
    }

    public BaseSalariedEmployee getEmployeeById(int empId){
        this.open();
        BaseSalariedEmployee bse = null;
        Cursor cursor = db.query(EmployeeDatabaseHelper.TABLE_EMPLOYEE,null,EmployeeDatabaseHelper.TBL_EMP_COL_ID+" = "+empId,null,null,null,null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            String name = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseHelper.TBL_EMP_COL_NAME));
            String desg = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseHelper.TBL_EMP_COL_DESG));
            bse = new BaseSalariedEmployee(name,empId,desg);
        }
        this.close();
        return bse;
    }

    public boolean updateEmployee(BaseSalariedEmployee bse){
        this.open();
        ContentValues values = new ContentValues();
        values.put(EmployeeDatabaseHelper.TBL_EMP_COL_NAME,bse.getEmpName());
        values.put(EmployeeDatabaseHelper.TBL_EMP_COL_DESG,bse.getEmpDesg());
        int updatedRow = db.update(EmployeeDatabaseHelper.TABLE_EMPLOYEE,values,EmployeeDatabaseHelper.TBL_EMP_COL_ID+" = "+bse.getEmpId(),null);

        this.close();
        if(updatedRow > 0){
            return true;
        }else{
            return false;
        }
    }
}
