package com.example.fragmentexample.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class DepartmentModel implements Parcelable {
    private int DepartmentID;
    private String DepartmentName;

    public DepartmentModel(int departmentID, String departmentName) {
        DepartmentID = departmentID;
        DepartmentName = departmentName;
    }

    public int getDepartmentID() {
        return DepartmentID;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.DepartmentID);
        dest.writeString(this.DepartmentName);
    }

    protected DepartmentModel(Parcel in) {
        this.DepartmentID = in.readInt();
        this.DepartmentName = in.readString();
    }

    public static final Parcelable.Creator<DepartmentModel> CREATOR = new Parcelable.Creator<DepartmentModel>() {
        @Override
        public DepartmentModel createFromParcel(Parcel source) {
            return new DepartmentModel(source);
        }

        @Override
        public DepartmentModel[] newArray(int size) {
            return new DepartmentModel[size];
        }
    };
}
