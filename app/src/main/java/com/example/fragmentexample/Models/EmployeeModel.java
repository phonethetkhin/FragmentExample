package com.example.fragmentexample.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class EmployeeModel implements Parcelable {
    private int EmployeeID,DeparmentID,Age;
    private String EmployeeName,Address;
    private double Salary;

    public EmployeeModel(int employeeID, int deparmentID, int age, String employeeName, String address, double salary) {
        EmployeeID = employeeID;
        DeparmentID = deparmentID;
        Age = age;
        EmployeeName = employeeName;
        Address = address;
        Salary = salary;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public int getDeparmentID() {
        return DeparmentID;
    }

    public int getAge() {
        return Age;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public String getAddress() {
        return Address;
    }

    public double getSalary() {
        return Salary;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.EmployeeID);
        dest.writeInt(this.DeparmentID);
        dest.writeInt(this.Age);
        dest.writeString(this.EmployeeName);
        dest.writeString(this.Address);
        dest.writeDouble(this.Salary);
    }

    protected EmployeeModel(Parcel in) {
        this.EmployeeID = in.readInt();
        this.DeparmentID = in.readInt();
        this.Age = in.readInt();
        this.EmployeeName = in.readString();
        this.Address = in.readString();
        this.Salary = in.readDouble();
    }

    public static final Parcelable.Creator<EmployeeModel> CREATOR = new Parcelable.Creator<EmployeeModel>() {
        @Override
        public EmployeeModel createFromParcel(Parcel source) {
            return new EmployeeModel(source);
        }

        @Override
        public EmployeeModel[] newArray(int size) {
            return new EmployeeModel[size];
        }
    };
}
