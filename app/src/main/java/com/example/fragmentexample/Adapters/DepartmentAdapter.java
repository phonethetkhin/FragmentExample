package com.example.fragmentexample.Adapters;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentexample.Database.Mydatabase;
import com.example.fragmentexample.Models.DepartmentModel;
import com.example.fragmentexample.R;
import com.example.fragmentexample.UpdateDeparment;

import java.util.List;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder> {

    List<DepartmentModel> departmentModelList;

    public DepartmentAdapter(List<DepartmentModel> departmentModelList) {
        this.departmentModelList = departmentModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.departmentlistitem,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, final int position) {
        h.tvDepartmentName.setText(departmentModelList.get(position).getDepartmentName());
        h.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(), UpdateDeparment.class);
                Bundle b=new Bundle();
                b.putParcelable("OldValue",departmentModelList.get(position));
                i.putExtras(b);
                view.getContext().startActivity(i);
            }
        });
        h.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mydatabase mdb=new Mydatabase(view.getContext());
                mdb.RemoveDepartment(departmentModelList.get(position).getDepartmentID());
                departmentModelList.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return departmentModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvDepartmentName;
        Button btnEdit,btnRemove;
        public ViewHolder(@NonNull View v) {
            super(v);
            tvDepartmentName=v.findViewById(R.id.tvDepartmentName);
            btnEdit=v.findViewById(R.id.btnEdit);
            btnRemove=v.findViewById(R.id.btnRemove);
        }
    }
}
