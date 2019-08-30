package com.example.fragmentexample.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.fragmentexample.Adapters.EmployeeAdapter;
import com.example.fragmentexample.Adapters.SpinnerAdapter;
import com.example.fragmentexample.Database.Mydatabase;
import com.example.fragmentexample.Models.DepartmentModel;
import com.example.fragmentexample.Models.EmployeeModel;
import com.example.fragmentexample.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeListFragment extends Fragment {
    Spinner spnDepartmentName;
    RecyclerView rvEmployeelist;
    List<DepartmentModel> departmentModelList;
    List<EmployeeModel> employeeModelList;


    public EmployeeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_employee_list, container, false);
        spnDepartmentName=v.findViewById(R.id.spnDepartmentID);
        rvEmployeelist=v.findViewById(R.id.rvEmployeelist);
        //spinner
        Mydatabase mdb=new Mydatabase(getContext());
            departmentModelList=mdb.GetDepartmentName();
        spnDepartmentName.setAdapter(new SpinnerAdapter(getContext(),R.layout.spinnerlistitem,R.id.tvDepartmentName,departmentModelList));
        //show data
        spnDepartmentName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
onResume();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        Mydatabase mdb=new Mydatabase(getContext());
        rvEmployeelist.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false));
        rvEmployeelist.setHasFixedSize(true);
        employeeModelList=mdb.GetEmployee(departmentModelList.get(spnDepartmentName.getSelectedItemPosition()).getDepartmentID());
        rvEmployeelist.setAdapter(new EmployeeAdapter(employeeModelList));
    }
}
