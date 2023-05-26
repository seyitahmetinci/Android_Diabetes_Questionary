package com.example.canrisk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDB extends SQLiteOpenHelper {

    private  static final String DBName="UserDatabase";
    private  static final String DBTablename="Users";
    private  static final int DBversion=1;

    private  static final String USER_ID="userID";
    private  static final String USER_SCORE="score";
    private  static final String USER_GENDER="gender";


    private  static final String CREATE_TABLE="CREATE TABLE " + DBTablename + " (" +
            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USER_SCORE + " INTEGER, " +
            USER_GENDER + " TEXT); ";

    private  static final String DROP_TABLE="DROP TABLE IF EXISTS " + DBTablename;

    private Context context;
    public MyDB(@Nullable Context context) {
        super(context, DBName, null, DBversion);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    long addUser(int score, String gender){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(USER_SCORE,score);
        cv.put(USER_GENDER,gender);


        long result = -1;
        try {
            result = db.insert(DBTablename,null,cv);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int getTotal() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + DBTablename;
        Cursor cursor = db.rawQuery(query, null);
        int totalCount = 0;

        if (cursor.moveToFirst()) {
            totalCount = cursor.getInt(0);
        }

        cursor.close();
        return totalCount;
    }

    public List<Integer> getUserCountsByRiskGroup() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + USER_GENDER + ", " +
                "SUM(CASE WHEN " + USER_SCORE + " <= 21 THEN 1 ELSE 0 END) AS LowRiskCount, " +
                "SUM(CASE WHEN " + USER_SCORE + " >= 22 AND " + USER_SCORE + " <= 32 THEN 1 ELSE 0 END) AS ModerateRiskCount, " +
                "SUM(CASE WHEN " + USER_SCORE + " >= 33 THEN 1 ELSE 0 END) AS HighRiskCount " +
                "FROM " + DBTablename +
                " GROUP BY " + USER_GENDER;

        Cursor cursor = db.rawQuery(query, null);
        List<Integer> userCounts = new ArrayList<>();

        int columnIndexLowRisk = cursor.getColumnIndex("LowRiskCount");
        int columnIndexModerateRisk = cursor.getColumnIndex("ModerateRiskCount");
        int columnIndexHighRisk = cursor.getColumnIndex("HighRiskCount");

        while (cursor.moveToNext()) {
            int lowRiskCount = cursor.getInt(columnIndexLowRisk);
            int moderateRiskCount = cursor.getInt(columnIndexModerateRisk);
            int highRiskCount = cursor.getInt(columnIndexHighRisk);

            userCounts.add(lowRiskCount);
            userCounts.add(moderateRiskCount);
            userCounts.add(highRiskCount);
        }

        cursor.close();
        return userCounts;
    }


}
