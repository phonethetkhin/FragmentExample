package com.example.fragmentexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.fragmentexample.Fragments.DepartmentFragment;
import com.example.fragmentexample.Fragments.EmployeeFragment;
import com.example.fragmentexample.Fragments.EmployeeListFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tlMain;
    FrameLayout flMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tlMain=findViewById(R.id.tlMain);
        flMain=findViewById(R.id.flMain);



        tlMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {



            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                Fragment fragment=null;
                String title=null;
                if(tab.getPosition()==0)
                {
                    title="Save Department";
                    fragment=new DepartmentFragment();


                }
                if(tab.getPosition()==1)
                {
                    title="Save Employee";
                    fragment=new EmployeeFragment();

                }
                if(tab.getPosition()==2)
                {
                    title="Employee List";
                    fragment=new EmployeeListFragment();
                }
                ft.replace(R.id.flMain,fragment).commit();
                getSupportActionBar().setTitle(title);

            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }



        });
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flMain,new DepartmentFragment()).commit();
        getSupportActionBar().setTitle("Save Department");

    }
}
