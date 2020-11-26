package com.example.ksagtracker;

import android.text.Editable;

import java.util.LinkedHashMap;

public class SearchObjects {
    Editable now;
    Editable date;
    Editable des;
    SearchObjects(Editable name, Editable dt, Editable desc){
                        now = name;
                        date = dt;
                        des = desc;
    }
}
