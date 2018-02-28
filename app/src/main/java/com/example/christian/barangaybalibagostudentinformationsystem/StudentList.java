package com.example.christian.barangaybalibagostudentinformationsystem;

import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Christian on 26/02/2018.
 */

public class StudentList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Student> list;
    StudentListAdapter adapter = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_list_layout);
        DatabaseHelper databaseHelper = new DatabaseHelper(this,"studentDB.sqlite",null,1);
        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new StudentListAdapter(this,R.layout.student_list, list);
        gridView.setAdapter(adapter);
        //get all data from sqlite
        Cursor cursor = databaseHelper.getData("SELECT * FROM STUDENT");
        list.clear();
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String fullname = cursor.getString(1);
            String dateOfBirth = cursor.getString(2);
            String placeOfBirth = cursor.getString(3);
            String citizenship = cursor.getString(4);
            String comelecNo = cursor.getString(5);
            String dateIssued = cursor.getString(6);
            byte[] image = cursor.getBlob(7);

            list.add(new Student(id,fullname,dateOfBirth,placeOfBirth,citizenship,comelecNo,dateIssued,image));

            adapter.notifyDataSetChanged();

        }
    }
}