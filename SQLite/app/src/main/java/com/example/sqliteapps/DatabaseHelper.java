package com.example.sqliteapps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASENAME = "sqlitedb";
    public static String TABLENAME = "tbiodata";

    public static String ID = "id";
    public static String NAMA = "nama";
    public static String JKEL = "jkel";
    public static String ALAMAT = "alamat";

    public ArrayList<Biodata> listData = new ArrayList<>();

    Context context;

    public DatabaseHelper(Context ctx){
        super(ctx, DATABASENAME, null, 1);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE IF NOT EXISTS "+TABLENAME+" ("+ID+" TEXT PRIMARI KEY, "+
                NAMA+" TEXT, "+JKEL+" TEXT,"+ALAMAT+" TEXT)";
        sqLiteDatabase.execSQL(createTable);
        System.out.println("Tabel berhasil dibuat");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS "+TABLENAME;
        sqLiteDatabase.execSQL(sql);
        System.out.println("Update table");
    }

    public boolean tambahData(Biodata bioItem){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ID, bioItem.getId());
        cv.put(NAMA, bioItem.getNama());
        cv.put(JKEL, bioItem.getJKel());
        cv.put(ALAMAT, bioItem.getAlamat());
        db.insert(TABLENAME, null, cv);

        return true;
    }

    public ArrayList<Biodata> getBiodata(){
        listData.clear();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLENAME, null);
        if (cur.getCount() !=0){
            if (cur.moveToFirst()){
                do{
                    Biodata bioItem = new Biodata(
                            cur.getString(cur.getColumnIndex(ID)),
                            cur.getString(cur.getColumnIndex(NAMA)),
                            cur.getString(cur.getColumnIndex(JKEL)),
                            cur.getString(cur.getColumnIndex(ALAMAT))
                    );

                    listData.add(bioItem);
                }while (cur.moveToNext());
            }
        }
        cur.close();
        db.close();
        return listData;
    }
}
