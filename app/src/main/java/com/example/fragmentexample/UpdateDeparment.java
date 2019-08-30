package com.example.fragmentexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fragmentexample.Database.Mydatabase;
import com.example.fragmentexample.Models.DepartmentModel;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateDeparment extends AppCompatActivity {
TextInputEditText tietDepartmentName;
Button btnSave;
Mydatabase mdb=new Mydatabase(UpdateDeparment.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_deparment);
        tietDepartmentName=findViewById(R.id.tietDepartmentName);
        btnSave=findViewById(R.id.btnSave);
        final DepartmentModel dmodel=getIntent().getParcelableExtra("OldValue");
        tietDepartmentName.setText(dmodel.getDepartmentName());
        getSupportActionBar().setTitle("Update Department");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tietDepartmentName.getText().toString().trim().length()<=0)
                {
                    tietDepartmentName.setError("Fill Here");
                    Toast.makeText(UpdateDeparment.this, "Pleas Fill Department Name", Toast.LENGTH_SHORT).show();
                }
                else {
                    mdb.UpdateDepartmentName(dmodel.getDepartmentID(), tietDepartmentName.getText().toString());
                    Toast.makeText(UpdateDeparment.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
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
