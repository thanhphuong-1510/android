package com.example.ktra22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.ktra22.database.SQLite;
import com.example.ktra22.model.NhanVien;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    EditText addTen, addSdt, addDob;
    RadioButton btnNam, btnNu;
    CheckBox addKynang1, addKynang2, addKynang3;
    Button btnCancel, btnLuu;
    SQLite sqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        addTen = findViewById(R.id.addTen);
        addSdt = findViewById(R.id.addSdt);
        addDob = findViewById(R.id.addDob);
        btnNam = findViewById(R.id.btnNam);
        btnNu = findViewById(R.id.btnNu);
        addKynang1 = findViewById(R.id.addKynang1);
        addKynang2 = findViewById(R.id.addKynang2);
        addKynang3 = findViewById(R.id.addKynang3);
        btnCancel = findViewById(R.id.btnCancel);
        btnLuu = findViewById(R.id.btnLuu);
        btnLuu.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        sqLite = new SQLite(this);
    }


    @Override
    public void onClick(View view) {
        if (view == btnLuu) {
            NhanVien nhanVien = new NhanVien();
            if (addTen.getText().toString().isEmpty()) {
                Toast.makeText(this, "Không bỏ trống tên", Toast.LENGTH_SHORT).show();
            } else if (!addSdt.getText().toString().matches("\\d+")) {
                Toast.makeText(this, "Số điện thoại chỉ nhập số", Toast.LENGTH_SHORT).show();

            } else if (Integer.parseInt(addDob.getText().toString()) <1980 || Integer.parseInt(addDob.getText().toString()) > 1995) {
                Toast.makeText(this, "Nhập năm sinh trong khoảng 1980-1995", Toast.LENGTH_SHORT).show();

            } else {
                nhanVien.setTen(addTen.getText().toString());
                nhanVien.setSdt(addSdt.getText().toString());
                nhanVien.setDob(Integer.parseInt(addDob.getText().toString()));
                if (btnNam.isChecked()) {
                    nhanVien.setGt(true);
                } else {
                    nhanVien.setGt(false);

                }
                String kyNang = "";
                if (addKynang1.isChecked()) {
                    kyNang += "web;";
                }
                if (addKynang2.isChecked()) {
                    kyNang += "android;";
                }
                if (addKynang3.isChecked()) {
                    kyNang += "ios;";
                }
                if (kyNang.endsWith(";")) {
                    kyNang = kyNang.substring(0, kyNang.length() - 1);
                }
                nhanVien.setKynang(kyNang);
                sqLite.add(nhanVien);
                finish();
            }
        }
        if (view == btnCancel) {
            finish();
        }
    }
}