package com.example.fragmentexample.Adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentexample.Database.Mydatabase;
import com.example.fragmentexample.Models.EmployeeModel;
import com.example.fragmentexample.R;
import com.example.fragmentexample.UpdateEmployee;

import java.net.ProtocolFamily;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {
    List<EmployeeModel> employeeModelList;

    public EmployeeAdapter(List<EmployeeModel> employeeModelList) {
        this.employeeModelList = employeeModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.employeelistitem,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, final int position) {
h.tvEmployeeName.setText(employeeModelList.get(position).getEmployeeName());
h.tvAge.setText(employeeModelList.get(position).getAge()+" years old");
h.tvAddress.setText(employeeModelList.get(position).getAddress());
h.tvSalary.setText(employeeModelList.get(position).getSalary()+" $/month");
h.btnEdit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(view.getContext(), UpdateEmployee.class);
        Bundle b=new Bundle();
        b.putParcelable("OldValue",employeeModelList.get(position));
        i.putExtras(b);
        view.getContext().startActivity(i);
    }
});
h.btnRemove.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(final View view) {
        final AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
        builder.setMessage("Are You Sure You Want To Delete This Employee?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Mydatabase mdb=new Mydatabase(view.getContext());

                mdb.RemoveEmployee(employeeModelList.get(position).getEmployeeID());

                Toast.makeText(view.getContext(), "Remove Successfully", Toast.LENGTH_SHORT).show();
                employeeModelList.remove(position);

                notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();


    }
});
    }

    @Override
    public int getItemCount() {
        return employeeModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvEmployeeName,tvAge,tvAddress,tvSalary;
        Button btnEdit,btnRemove;

        public ViewHolder(@NonNull View v) {
            super(v);
            tvEmployeeName=v.findViewById(R.id.tvEmployeeName);
            tvAge=v.findViewById(R.id.tvAge);
            tvAddress=v.findViewById(R.id.tvAddress);
            tvSalary=v.findViewById(R.id.tvSalary);
            btnEdit=v.findViewById(R.id.btnEdit);
            btnRemove=v.findViewById(R.id.btnRemove);
        }
    }
}
