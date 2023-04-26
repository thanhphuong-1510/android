package com.example.ktra22;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTen, editSdt, editDob;
    RadioButton btnNam, btnNu;
    CheckBox editKynang1, editKynang2, editKynang3;
    Button btnDelete, btnLuu;
    SQLite sqLite;
    NhanVien nhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editTen = findViewById(R.id.editTen);
        editSdt = findViewById(R.id.editSdt);
        editDob = findViewById(R.id.editDob);
        btnNam = findViewById(R.id.btnNam);
        btnNu = findViewById(R.id.btnNu);
        editKynang1 = findViewById(R.id.editKynang1);
        editKynang2 = findViewById(R.id.editKynang2);
        editKynang3 = findViewById(R.id.editKynang3);
        btnDelete = findViewById(R.id.btnDelete);
        btnLuu = findViewById(R.id.btnLuu);
        btnLuu.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        sqLite = new SQLite(this);
        nhanVien = (NhanVien) getIntent().getSerializableExtra("nhanvien");
        editTen.setText(nhanVien.getTen());
        editSdt.setText(nhanVien.getSdt());
        editDob.setText(String.valueOf(nhanVien.getDob()));
        if (nhanVien.getGt() == true) {
            btnNam.setChecked(true);
        } else {
            btnNu.setChecked(true);
        }
        String[] kynang = nhanVien.getKynang().split(";");
        for (int i = 0; i < kynang.length; i++) {
            if (editKynang1.getText().toString().equalsIgnoreCase(kynang[i])) {
                editKynang1.setChecked(true);
            }
            if (editKynang2.getText().toString().equalsIgnoreCase(kynang[i])) {
                editKynang2.setChecked(true);
            }
            if (editKynang3.getText().toString().equalsIgnoreCase(kynang[i])) {
                editKynang3.setChecked(true);
            }
        }
    }


    @Override
    public void onClick(View view) {
        if (view == btnLuu) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setMa(this.nhanVien.getMa());
            if (editTen.getText().toString().isEmpty()) {
                Toast.makeText(this, "Không bỏ trống tên", Toast.LENGTH_SHORT).show();
            } else if (!editSdt.getText().toString().matches("\\d+")) {
                Toast.makeText(this, "Số điện thoại chỉ nhập số", Toast.LENGTH_SHORT).show();

            } else if (Integer.parseInt(editDob.getText().toString()) <1980 || Integer.parseInt(editDob.getText().toString()) > 1995) {
                Toast.makeText(this, "Nhập năm sinh trong khoảng 1980-1995", Toast.LENGTH_SHORT).show();

            }
            else {
                nhanVien.setTen(editTen.getText().toString());
                nhanVien.setSdt(editSdt.getText().toString());
                nhanVien.setDob(Integer.parseInt(editDob.getText().toString()));
                if (btnNam.isChecked()) {
                    nhanVien.setGt(true);
                } else {
                    nhanVien.setGt(false);

                }
                String kyNang = "";
                if (editKynang1.isChecked()) {
                    kyNang += "web;";
                }
                if (editKynang2.isChecked()) {
                    kyNang += "android;";
                }
                if (editKynang3.isChecked()) {
                    kyNang += "ios;";
                }
                if (kyNang.endsWith(";")) {
                    kyNang = kyNang.substring(0, kyNang.length() - 1);
                }
                nhanVien.setKynang(kyNang);
                sqLite.update(nhanVien);
                finish();
            }
        }
        if (view == btnDelete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có muốn xóa nhân viên này không?");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    sqLite.delete(nhanVien.getMa());
                    finish();
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}
