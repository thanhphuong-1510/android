package com.example.ktra21.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ktra21.model.CongViec;

import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper {
    public SQLite(@Nullable Context context) {
        super(context, "cong_viec.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table cong_viec(" +
                "ma integer primary key autoincrement," +
                "ten text," +
                "noidung text," +
                "ngayHT text," +
                "tinhtrang text," +
                "congtac boolean" +
                ")";
        sqLiteDatabase.execSQL(sql);
    }

    public List<CongViec> getAll() {
        List<CongViec> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("cong_viec", null, null, null, null, null, "ma desc");
        while (cursor.moveToNext()) {
            Integer ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String noidung = cursor.getString(2);
            String ngayHT = cursor.getString(3);
            String tinhtrang = cursor.getString(4);
            Boolean congtac = cursor.getInt(5) == 1;
            CongViec congViec = new CongViec(ma, ten, noidung, ngayHT, tinhtrang, congtac);
            list.add(congViec);
        }
        return list;
    }

    public long add(CongViec congViec) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten", congViec.getTen());
        contentValues.put("noidung", congViec.getNoidung());
        contentValues.put("ngayHT", congViec.getNgayHT());
        contentValues.put("tinhtrang", congViec.getTinhtrang());
        contentValues.put("congtac", congViec.getCongtac());

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("cong_viec", null, contentValues);

    }

    public long update(CongViec congViec) {
        ContentValues contentValues = new ContentValues();
        String[] whereArgs = {congViec.getMa().toString()};
        String whereClause = "ma = ?";
        contentValues.put("ten", congViec.getTen());
        contentValues.put("noidung", congViec.getNoidung());
        contentValues.put("ngayHT", congViec.getNgayHT());
        contentValues.put("tinhtrang", congViec.getTinhtrang());
        contentValues.put("congtac", congViec.getCongtac());

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update("cong_viec", contentValues, whereClause, whereArgs);

    }

    public long delete(int id) {
        String[] whereArgs = {String.valueOf(id)};
        String whereClause = "ma = ?";


        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("cong_viec", whereClause, whereArgs);

    }

    public List<CongViec> search(String name) {
        List<CongViec> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String order = "ngayHT ASC";
        String where = "ten like ?";
        String[] whereValue = {'%' + name + '%'};
        Cursor cursor = sqLiteDatabase.query("cong_viec", null, where, whereValue, null, null, order);
        while (cursor.moveToNext()) {
            Integer ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String noidung = cursor.getString(2);
            String ngayHT = cursor.getString(3);
            String tinhtrang = cursor.getString(4);
            Boolean congtac = cursor.getInt(5) == 1;
            CongViec congViec = new CongViec(ma, ten, noidung, ngayHT, tinhtrang, congtac);
            list.add(congViec);
        }
        return list;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
