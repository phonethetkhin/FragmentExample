package com.example.fragmentexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fragmentexample.Adapters.SpinnerAdapter;
import com.example.fragmentexample.Database.Mydatabase;
import com.example.fragmentexample.Models.DepartmentModel;
import com.example.fragmentexample.Models.EmployeeModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class UpdateEmployee extends AppCompatActivity {
Spinner spnDepartmentName;
TextInputEditText tietEmployeeName,tietAge,tietAddress,tietSalary;
Button btnSave,btnCancel;
Mydatabase mdb=new Mydatabase(UpdateEmployee.this);
List<DepartmentModel> departmentModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);
        spnDepartmentName=findViewById(R.id.spnDepartmentID);
        tietEmployeeName=findViewById(R.id.tietEmployeeName);
        tietAddress=findViewById(R.id.tietAddress);
        tietSalary=findViewById(R.id.tietSalary);
        tietAge=findViewById(R.id.tietAge);
        btnSave=findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);
        getSupportActionBar().setTitle("Update Employee");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //spinner
            departmentModelList=mdb.GetDepartmentName();
        spnDepartmentName.setAdapter(new SpinnerAdapter(UpdateEmployee.this,R.layout.spinnerlistitem,R.id.tvDepartmentName,departmentModelList));



        //setting old data;

        final EmployeeModel emodel=getIntent().getParcelableExtra("OldValue");
        tietEmployeeName.setText(emodel.getEmployeeName());
        tietAge.setText(emodel.getAge()+"");
        tietAddress.setText(emodel.getAddress());
        tietSalary.setText(emodel.getSalary()+"");

        //updating new data
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tietEmployeeName.getText().toString().isEmpty() ||tietAge.getText().toString().isEmpty() ||tietAddress.getText().toString().isEmpty() ||tietSalary.getText().toString().isEmpty())
                {
                    Toast.makeText(UpdateEmployee.this, "Please Fill All Information!!!", Toast.LENGTH_LONG).show();
                }
                else{
                mdb.UpdateEmployee(emodel.getEmployeeID(),departmentModelList.get(spnDepartmentName.getSelectedItemPosition()).getDepartmentID(),
                        Integer.parseInt(tietAge.getText().toString()),
                        tietEmployeeName.getText().toString(),
                        tietAddress.getText().toString(),
                        Double.parseDouble(tietSalary.getText().toString()));
                    Toast.makeText(UpdateEmployee.this, "Updated Sucessfully", Toast.LENGTH_LONG).show();
                    finish();
            }}
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
