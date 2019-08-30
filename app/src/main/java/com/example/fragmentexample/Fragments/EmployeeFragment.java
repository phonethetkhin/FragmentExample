package com.example.fragmentexample.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fragmentexample.Adapters.SpinnerAdapter;
import com.example.fragmentexample.Database.Mydatabase;
import com.example.fragmentexample.Models.DepartmentModel;
import com.example.fragmentexample.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeFragment extends Fragment {
    Spinner spnDepartmentID;
TextInputEditText tietEmployeeName,tietAge,tietAddress,tietSalary;
Button btnSave,btnCancel;
List<DepartmentModel> departmentModelList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_employee, container, false);
        spnDepartmentID=v.findViewById(R.id.spnDepartmentID);
        tietEmployeeName=v.findViewById(R.id.tietEmployeeName);
        tietAge=v.findViewById(R.id.tietAge);
        tietAddress=v.findViewById(R.id.tietAddress);
        tietSalary=v.findViewById(R.id.tietSalary);
        btnSave=v.findViewById(R.id.btnSave);
        btnCancel=v.findViewById(R.id.btnCancel);

        //spinner
        Mydatabase mdb=new Mydatabase(getContext());
        departmentModelList=mdb.GetDepartmentName();
        spnDepartmentID.setAdapter(new SpinnerAdapter(getContext(),R.layout.spinnerlistitem,R.id.tvDepartmentName,departmentModelList));

        //save buutton
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Mydatabase mdb=new Mydatabase(getContext());
            if(tietEmployeeName.getText().toString().isEmpty() ||
            tietAge.getText().toString().isEmpty() ||
            tietAddress.getText().toString().isEmpty() || tietSalary.getText().toString().isEmpty())
            {
                Toast.makeText(getContext(), "Please Fill All Information", Toast.LENGTH_LONG).show();
            }
            else
            {
                if(mdb.InserEmployee(departmentModelList.get(spnDepartmentID.getSelectedItemPosition()).getDepartmentID(),
                        Integer.parseInt(tietAge.getText().toString()),
                        tietEmployeeName.getText().toString(),
                        tietAddress.getText().toString(),
                        Double.parseDouble(tietSalary.getText().toString())))
                {
                    Toast.makeText(getContext(), "Save Successfully", Toast.LENGTH_LONG).show();
                    tietEmployeeName.setText("");
                    tietAge.setText("");
                    tietAddress.setText("");
                    tietSalary.setText("");
                    tietEmployeeName.requestFocus();
                }
                else
                {
                    Toast.makeText(getContext(), "Save Fail", Toast.LENGTH_SHORT).show();
                }
            }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tietEmployeeName.setText("");
                tietAge.setText("");
                tietAddress.setText("");
                tietSalary.setText("");
                tietEmployeeName.requestFocus();
            }
        });








return v;






    }

}
