package com.imaleex.esportapp;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DbException;

public class Main {

    public static Db db;

    public static void main(String[] args) {
        initDbConnection();

    }
    private static void initDbConnection() {
        try {
        Db.setDbParams("esports", "ijG4ZHcn81QlxfOA", "esports", "server.imaleex.com");
        db = Db.getInstance(1);
        } catch (DbException e) {
            e.printStackTrace();
        }

    }


}
