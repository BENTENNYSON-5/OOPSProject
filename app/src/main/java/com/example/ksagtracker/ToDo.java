package com.example.ksagtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ToDo extends AppCompatActivity {
    List<String> todo_list;
    ArrayAdapter<String> arrayAdapter;
    ListView listview;
    EditText editText;
    String noteee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        editText = (EditText)findViewById(R.id.id_edit_text);
        todo_list = PrefConfig.readListFromPref(this);
        if(todo_list == null)
               todo_list = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this,R.layout.todolist,todo_list);
        listview = findViewById(R.id.id_list_view);
        PrefConfig.writeListInPref(getApplicationContext(),todo_list);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView cut = (TextView) view;
                cut.setPaintFlags(cut.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            }
        });
    }
    public void AddItemtolist(View v){
       todo_list.add(editText.getText().toString());
       arrayAdapter.notifyDataSetChanged();
       editText.setText("");
    }

}