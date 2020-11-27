package com.example.ksagtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Search extends Listing {

    SearchView mySearchView;
    ListView myList;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mySearchView=(SearchView)findViewById(R.id.searchView);
        myList=(ListView)findViewById(R.id.myList);

        list=new ArrayList<String>();

        list.add("Karthik");
        list.add("akhil");
        list.add("shiva");
        list.add("gagan");
        list.add("Karthi");
        list.add("akil");
        list.add("shiv");
        list.add("Gagan");

        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        myList.setAdapter(adapter);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);

                return false;
            }
        });
    }
}