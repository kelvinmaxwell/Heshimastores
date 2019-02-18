package com.example.root.heshimastores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    public static final String Database_Name="Heshima.db";
    public static final String Table_updates="wharehouse_table";
    public static final String Table_sales="sales_table";

    public static final String Table_updates_chuka="wharehouse_table_chuka";
    public static final String Table_sales_chuka="sales_table_chuka";
    public static final String Table_updates_chukaT="wharehouse_table_chukat";

    public static final String col_1="id";
    public static final String col_2="goodid";
    public static final String col_3="codinator";
    public static final String col_4="quantity";
    public static final String col_5="date";
    public static final String col_6="vehicleid";


    public static final String col_1_sales="id";
    public static final String col_2_sales="goodsold";
    public static final String col_3_sales="amount";
    public static final String col_4_sales="date";
    public static final String col_5_sales="cost_sh";

    public static final String col_1_chuka="id";
    public static final String col_2_chuka="goodid";
    public static final String col_3_chuka="codinator";
    public static final String col_4_chuka="quantity";
    public static final String col_5_chuka="date";
    public static final String col_6_chuka="vehicleid";


    public static final String col_1_sales_chuka="id";
    public static final String col_2_sales_chuka="goodsold";
    public static final String col_3_sales_chuka="amount";
    public static final String col_4_sales_chuka="date";
    public static final String col_5_sales_chuka="cost_sh";


    public static final String col_1_chukat="id";
    public static final String col_2_chukat="goodid";
    public static final String col_3_chukat="codinator";
    public static final String col_4_chukat="quantity";
    public static final String col_5_chukat="date";
    public static final String col_6_chukat="vehicleid";


    public database( Context context) {
        super(context, Database_Name,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists "+Table_updates+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,goodid VARCHAR,codinator VARCHAR,quantity INT,date VARCHAR,vehicleid VARCHAR)");
        db.execSQL("create table if not EXISTS "+Table_sales+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,goodsold VARCHAR,amount int,date VARCHAR,cost_sh int)");

        db.execSQL("create table if not exists "+Table_updates_chuka+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,goodid VARCHAR,codinator VARCHAR,quantity INT,date VARCHAR,vehicleid VARCHAR)");
        db.execSQL("create table if not exists "+Table_updates_chukaT+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,goodid VARCHAR,codinator VARCHAR,quantity INT,date VARCHAR,vehicleid VARCHAR)");
        db.execSQL("create table if not EXISTS "+Table_sales_chuka+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,goodsold VARCHAR,amount int,date VARCHAR,cost_sh int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+Table_updates);
        db.execSQL("DROP TABLE IF EXISTS "+Table_sales);
        db.execSQL("DROP TABLE IF EXISTS "+Table_updates_chuka);
        db.execSQL("DROP TABLE IF EXISTS "+Table_updates_chukaT);
        db.execSQL("DROP TABLE IF EXISTS "+Table_sales_chuka);


        onCreate(db);
    }
    public boolean insertData(String goodid,String codinator,int quantity,String date,String vehicleid) {
        SQLiteDatabase db=this.getWritableDatabase();


        ContentValues contentValues=new ContentValues();


        contentValues.put(col_2,goodid);
        contentValues.put(col_3,codinator);
        contentValues.put(col_4,quantity);
        contentValues.put(col_5,date);
        contentValues.put(col_6,vehicleid);

        long result=db.insert(Table_updates,null,contentValues);
        if(result==-1 )
            return false;
        else
            return true;

    }
    public boolean insertdata2(String goodsold,int  amount,String date2,int cost_sh){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues2=new ContentValues();
        contentValues2.put(col_2_sales,goodsold);
        contentValues2.put(col_3_sales,amount);
        contentValues2.put(col_4_sales,date2);
        contentValues2.put(col_5_sales,cost_sh);
        long result2=db.insert(Table_sales,null,contentValues2);
        if(result2==-1 )
            return false;
        else
            return true;
    }

    public boolean insertDataupdateschuka(String goodid,String codinator,int quantity,String date,String vehicleid) {
        SQLiteDatabase db=this.getWritableDatabase();


        ContentValues contentValues=new ContentValues();


        contentValues.put(col_2_chuka,goodid);
        contentValues.put(col_3_chuka,codinator);
        contentValues.put(col_4_chuka,quantity);
        contentValues.put(col_5_chuka,date);
        contentValues.put(col_6_chuka,vehicleid);

        long result=db.insert(Table_updates_chuka,null,contentValues);
        if(result==-1 )
            return false;
        else
            return true;

    }

    public boolean insertDataupdateschukatransfer(String goodid,String codinator,int quantity,String date,String vehicleid) {
        SQLiteDatabase db=this.getWritableDatabase();


        ContentValues contentValues=new ContentValues();


        contentValues.put(col_2_chukat,goodid);
        contentValues.put(col_3_chukat,codinator);
        contentValues.put(col_4_chukat,quantity);
        contentValues.put(col_5_chukat,date);
        contentValues.put(col_6_chukat,vehicleid);

        long result=db.insert(Table_updates_chukaT,null,contentValues);
        if(result==-1 )
            return false;
        else
            return true;

    }
    public boolean insertdatasaleschuka(String goodsold,int  amount,String date2,int cost_sh){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues2=new ContentValues();
        contentValues2.put(col_2_sales_chuka,goodsold);
        contentValues2.put(col_3_sales_chuka,amount);
        contentValues2.put(col_4_sales_chuka,date2);
        contentValues2.put(col_5_sales_chuka,cost_sh);
        long result2=db.insert(Table_sales_chuka,null,contentValues2);
        if(result2==-1 )
            return false;
        else
            return true;
    }


    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+Table_updates,null);
        return res;






    }
    public Cursor getAllDatasales(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res2=db.rawQuery("select * from "+Table_sales,null);
        return res2;






    }

    public Cursor getAllDataupdateschuka(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res3=db.rawQuery("select * from "+Table_updates_chuka,null);
        return res3;






    }
    public Cursor getAllDataupdateschukatransfers(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res4=db.rawQuery("select * from "+Table_updates_chukaT,null);
        return res4;






    }
    public Cursor getAllDatasaleschuka(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res4=db.rawQuery("select * from "+Table_sales_chuka,null);
        return res4;






    }

    public Cursor  data(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor totals1=db.rawQuery("select sum(quantity) as total  from "+
                Table_updates+" where goodid like '"+id+"%'",null);
        return totals1;


    }
    public  Cursor datasales(String id2){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor totals2=db.rawQuery("select sum(amount) as total  from "+Table_sales+" where goodsold like '"+id2+"%'",null);
        return totals2;

    }

    public Cursor  datachuka(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor totals1=db.rawQuery("select sum(quantity) as total  from "+
                Table_updates_chuka+" where goodid like '"+id+"%'",null);
        return totals1;


    }
    public Cursor  datachukatransfer(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor totals1=db.rawQuery("select sum(quantity) as total  from "+
                Table_updates_chukaT+" where goodid like '"+id+"%'",null);
        return totals1;}
    public  Cursor datasaleschuka(String id2){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor totals2=db.rawQuery("select sum(amount) as total  from "+Table_sales_chuka+" where goodsold like '"+id2+"%'",null);
        return totals2;

    }


}


