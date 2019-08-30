package com.example.fragmentexample.Fragments;


import android.app.ActionBar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.fragmentexample.Adapters.DepartmentAdapter;
import com.example.fragmentexample.Database.Mydatabase;
import com.example.fragmentexample.Models.DepartmentModel;
import com.example.fragmentexample.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepartmentFragment extends Fragment {
   private  TextInputEditText tietDepartmentName;
    private Button btnSave;
    private RecyclerView rvShowDepartment;
    List<DepartmentModel> departmentModelList;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_department, container, false);

        tietDepartmentName=v.findViewById(R.id.tietDepartmentName);
        btnSave=v.findViewById(R.id.btnSave);
        rvShowDepartment=v.findViewById(R.id.rvShowDepartment);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //user validation
                if (tietDepartmentName.getText().toString().trim().length() <= 0) {
                    tietDepartmentName.setError("Enter Name Here!!");
                    Toast.makeText(getContext(), "Please Fill Department Name", Toast.LENGTH_SHORT).show();
                }
                else {
                    Mydatabase mdb=new Mydatabase(getContext());

                    if (mdb.AddDepartment(tietDepartmentName.getText().toString())) {
                        Toast.makeText(getContext(), "Save Successfully", Toast.LENGTH_LONG).show();
                         mdb=new Mydatabase(getContext());
                        departmentModelList=mdb.GetDepartmentName();
                        rvShowDepartment.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false));
                        rvShowDepartment.setHasFixedSize(true);
                        rvShowDepartment.setAdapter(new DepartmentAdapter(departmentModelList));

                    } else {
                        Toast.makeText(getContext(), "Save Fail", Toast.LENGTH_LONG).show();
                    }


                }
            }
        });



        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        Mydatabase mdb=new Mydatabase(getContext());
        departmentModelList=mdb.GetDepartmentName();
        rvShowDepartment.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false));
        rvShowDepartment.setHasFixedSize(true);
        rvShowDepartment.setAdapter(new DepartmentAdapter(departmentModelList));
    }
}
