package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OlcegBirlikDb extends SQLiteOpenHelper {
    public static final String DATA_BASE_NAME="olcegbirlik.db";
    public static final String Table_name="olcegbirliktb";


    public OlcegBirlikDb(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          db.execSQL("CREATE TABLE "+Table_name+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,olceg_birlik TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("DROP TABLE IF EXISTS "+Table_name);
          onCreate(db);
    }

    public boolean insertData(String olceg_birlik){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("olceg_birlik",olceg_birlik);
       long result= db.insert(Table_name,null,contentValues);
       if(result==-1){
           return false;
       }else{
           return true;
       }
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+Table_name,null);
        return res;
    }
    public boolean updateData(String olceg_birlik,String id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("olceg_birlik",olceg_birlik);
        db.update(Table_name,contentValues,"ID=?",new String[]{id});
        return true;
    }

    public Integer deleteData(String olceg_birlik){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(Table_name,"olceg_birlik=?",new String[]{olceg_birlik});

    }

    public void all(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+Table_name);
        onCreate(db);
    }



}
