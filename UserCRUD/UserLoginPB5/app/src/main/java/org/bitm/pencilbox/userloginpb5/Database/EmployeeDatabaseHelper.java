package org.bitm.pencilbox.userloginpb5.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mobile App on 5/19/2018.
 */

public class EmployeeDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "employee_db";
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_EMPLOYEE = "tbl_employee";
    public static final String TBL_EMP_COL_ID = "_id";
    public static final String TBL_EMP_COL_NAME = "emp_name";
    public static final String TBL_EMP_COL_DESG = "emp_desg";

    // create table table_name(id integer primary key, name text, desg text)

    public static final String CREATE_TABLE_EMPLOYEE = "create table "+TABLE_EMPLOYEE+"("
            +TBL_EMP_COL_ID+" integer primary key, "
            +TBL_EMP_COL_NAME+" text, "
            +TBL_EMP_COL_DESG+" text);";

    public EmployeeDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EMPLOYEE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_EMPLOYEE);
        onCreate(db);
    }
}
