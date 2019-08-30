package com.example.fragmentexample.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.fragmentexample.Models.DepartmentModel;
import com.example.fragmentexample.Models.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

public class Mydatabase extends SQLiteOpenHelper {

    private static final String DB_NAME="Employee";
    private static final int DB_VERSION=1;

    private final String DEPARTMENT_TABLE="Department";
    private final String EMPLOYEE_TABLE="Employee";

    public Mydatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+DEPARTMENT_TABLE+"(Department_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,Department_Name TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE "+EMPLOYEE_TABLE+"(Employee_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,Department_ID INTEGER,Age INTEGER,Employee_Name TEXT,Address TEXT,Salary REAL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean AddDepartment(String DepartmentName)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Department_Name",DepartmentName);

        try {
            db.insert(DEPARTMENT_TABLE, null, cv);

            db.close();
            return true;
        }
        catch (Exception e)
        {
            db.close();
        return false;
        }
    }
    public List<DepartmentModel> GetDepartmentName()
    {
        List<DepartmentModel> departmentModelList=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cs=db.rawQuery("SELECT * FROM "+DEPARTMENT_TABLE,null);
        if(cs.moveToFirst())
        {
            while(!cs.isAfterLast())
            {
               departmentModelList.add(new DepartmentModel(cs.getInt(0),cs.getString(1)));
               cs.moveToNext();
            }
        }
        cs.close();
        db.close();
        return departmentModelList;
    }
    public void UpdateDepartmentName(int DepartmentID,String DepartmentName)
    {
    SQLiteDatabase db=this.getWritableDatabase();
    db.execSQL("UPDATE "+DEPARTMENT_TABLE+" SET Department_Name='"+DepartmentName+"' WHERE Department_ID="+DepartmentID);
    db.close();

    }
    public void RemoveDepartment(int DepartmentID)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+DEPARTMENT_TABLE+" WHERE Department_ID="+DepartmentID);
        db.close();
    }
    public boolean InserEmployee(int DepartmentID,int Age,String EmployeeName,String Address,double Salary)
    {
SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Department_ID",DepartmentID);
        cv.put("Age",Age);
        cv.put("Employee_Name",EmployeeName);
        cv.put("Address",Address);
        cv.put("Salary",Salary);
        try {
            db.insert(EMPLOYEE_TABLE, null, cv);
            db.close();
            return true;
        }
        catch (Exception e)
        {
            db.close();
            return false;
        }
    }
    public List<EmployeeModel> GetEmployee(int DepartmentID)
    {
        List<EmployeeModel> employeeModelList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT * FROM "+EMPLOYEE_TABLE+" WHERE Department_ID="+DepartmentID,null);

        if(cs.moveToFirst())
        {
            while(!cs.isAfterLast())
            {
                employeeModelList.add(new EmployeeModel(cs.getInt(0),cs.getInt(cs.getColumnIndex("Department_ID")),
                        cs.getInt(cs.getColumnIndex("Age")),
                        cs.getString(cs.getColumnIndex("Employee_Name")),
                        cs.getString(cs.getColumnIndex("Address")),
                        cs.getDouble(cs.getColumnIndex("Salary"))));
                cs.moveToNext();
            }
        }
cs.close();
        db.close();
        return employeeModelList;

    }
    public void UpdateEmployee(int EmployeeID,int DepartmentID,int Age,String EmployeeName,String Address,double Salary)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("UPDATE "+EMPLOYEE_TABLE+" SET Department_ID="+DepartmentID+",Age="+Age+",Employee_Name='"+EmployeeName+"',Address='"+Address+"',Salary="+Salary+" WHERE Employee_ID="+EmployeeID);
        db.close();
    }
    public void RemoveEmployee(int EmployeeID)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+EMPLOYEE_TABLE+" WHERE Employee_ID="+EmployeeID);
    }

}
