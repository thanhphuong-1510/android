package com.example.ktra22.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ktra22.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper {
    public SQLite(@Nullable Context context) {
        super(context, "nhan_vien.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table nhan_vien(" +
                "ma integer primary key autoincrement," +
                "ten text," +
                "sdt text," +
                "dob integer," +
                "gt boolean," +
                "kynang text" +
                ")";
        sqLiteDatabase.execSQL(sql);

    }
    public List<NhanVien> getAll(){
        List<NhanVien> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("nhan_vien", null, null, null, null, null, "ma desc");
        while (cursor.moveToNext()) {
            Integer ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String sdt = cursor.getString(2);
            Integer dob = cursor.getInt(3);
            Boolean gt = cursor.getInt(4) == 1;
            String kynang = cursor.getString(5);
            NhanVien nhanVien = new NhanVien(ma, ten, sdt,dob, gt,kynang);
            list.add(nhanVien);
        }
        return list;

    }
    public long add(NhanVien nhanVien) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten", nhanVien.getTen());
        contentValues.put("sdt", nhanVien.getSdt());
        contentValues.put("dob", nhanVien.getDob());
        contentValues.put("gt", nhanVien.getGt());
        contentValues.put("kynang", nhanVien.getKynang());

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("nhan_vien", null, contentValues);

    }
    public long update(NhanVien nhanVien) {
        ContentValues contentValues = new ContentValues();
        String[] whereArgs = {nhanVien.getMa().toString()};
        String whereClause = "ma = ?";
        contentValues.put("ten", nhanVien.getTen());
        contentValues.put("sdt", nhanVien.getSdt());
        contentValues.put("dob", nhanVien.getDob());
        contentValues.put("gt", nhanVien.getGt());
        contentValues.put("kynang", nhanVien.getKynang());

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update("nhan_vien", contentValues, whereClause, whereArgs);

    }

    public long delete(int id) {
        String[] whereArgs = {String.valueOf(id)};
        String whereClause = "ma = ?";


        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("nhan_vien", whereClause, whereArgs);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<NhanVien> search(String name) {
        List<NhanVien> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String where = "kynang like ?";
        String[] whereValue = {'%' + name + '%'};
        Cursor cursor = sqLiteDatabase.query("nhan_vien", null, where, whereValue, null, null, null);
        while (cursor.moveToNext()) {
            Integer ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String sdt = cursor.getString(2);
            Integer dob = Integer.parseInt(cursor.getString(3));
            Boolean gt = cursor.getInt(4) == 1;
            String kynang = cursor.getString(5);
            NhanVien nhanVien = new NhanVien(ma, ten, sdt, dob, gt, kynang);
            list.add(nhanVien);
        }
        return list;
    }
}
