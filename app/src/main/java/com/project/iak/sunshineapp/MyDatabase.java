package com.project.iak.sunshineapp;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by denail on 17/11/25.
 */

@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {
    public static final String NAME = "MyDataBase";
    public static final int VERSION = 1;
}
