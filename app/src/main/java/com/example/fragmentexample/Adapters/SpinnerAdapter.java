package com.example.fragmentexample.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fragmentexample.Models.DepartmentModel;
import com.example.fragmentexample.R;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter {
    List<DepartmentModel> departmentModelList;

    public SpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId, List<DepartmentModel> departmentModelList) {
        super(context, resource, textViewResourceId);
        this.departmentModelList = departmentModelList;
    }

    @Override
    public int getCount() {
        return departmentModelList.size();
    }

   public View getCustomView(int i, View v, ViewGroup parent)
   {
       TextView tvDepartmentName;
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.spinnerlistitem,parent,false);
        tvDepartmentName=v.findViewById(R.id.tvDepartmentName);
        tvDepartmentName.setText(departmentModelList.get(i).getDepartmentName());
       return v;
   }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return getCustomView(i,view,viewGroup);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position,convertView,parent);
    }
}
