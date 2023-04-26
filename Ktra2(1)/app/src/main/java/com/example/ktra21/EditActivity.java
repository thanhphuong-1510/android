package com.example.ktra21;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ktra21.database.SQLite;
import com.example.ktra21.model.CongViec;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTen, editND, editNgayHT;
    Spinner editTT;

    SQLite sqLite;
    RadioButton btn1, btn2;
    Button btnDelete, btnLuu;

    CongViec congViec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editTen = findViewById(R.id.editTen);
        editND = findViewById(R.id.editND);
        editNgayHT = findViewById(R.id.editNgayHT);
        editTT = findViewById(R.id.editTT);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btnLuu = findViewById(R.id.btnLuu);
        btnDelete = findViewById(R.id.btnDelete);
        sqLite = new SQLite(this);
        btnLuu.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        editNgayHT.setOnClickListener(this);
        editTT.setAdapter(new ArrayAdapter<>(this, R.layout.item_spinner, getResources().getStringArray(R.array.tinh_trang)));

        congViec = (CongViec) getIntent().getSerializableExtra("congviec");
        editTen.setText(congViec.getTen());
        editND.setText(congViec.getNoidung());
        editNgayHT.setText(congViec.getNgayHT());
        for (int i = 0; i < editTT.getCount(); i++) {
            if (congViec.getTinhtrang().equals(editTT.getItemAtPosition(i).toString())) {
                editTT.setSelection(i);
                break;
            }
        }
        if (congViec.getCongtac() == true) {
            btn2.setChecked(true);
        } else {
            btn1.setChecked(true);
        }


    }

    @Override
    public void onClick(View view) {
        if (view == editNgayHT) {
            Calendar calendar = Calendar.getInstance();
            Integer year = calendar.get(Calendar.YEAR);
            Integer month = calendar.get(Calendar.MONTH);
            Integer day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(EditActivity.this, new DatePickerDialog.OnDateSetListener() {
                String s = "";

                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    if (month < 10) {
                        if (day < 10) {
                            s = "0" + day + "/0" + month + "/" + year;
                        } else {
                            s = day + "/0" + month + "/" + year;
                        }
                    } else {
                        if (day < 10) {
                            s = "0" + day + "/" + month + "/" + year;
                        } else {
                            s = day + "/" + month + "/" + year;
                        }
                    }
                    editNgayHT.setText(s);
                }
            }, year, month, day);
            datePickerDialog.show();
        }
        if (view == btnLuu) {
            CongViec congViec = new CongViec();
            if (editTen.getText().toString().isEmpty()) {
                Toast.makeText(this, "Không bỏ trống tên", Toast.LENGTH_SHORT).show();
            } else {
                congViec.setMa(this.congViec.getMa());
                congViec.setTen(editTen.getText().toString());
                congViec.setNoidung(editND.getText().toString());
                congViec.setNgayHT(editNgayHT.getText().toString());
                congViec.setTinhtrang(editTT.getSelectedItem().toString());
                if (btn1.isChecked()) {
                    congViec.setCongtac(false);
                } else {
                    congViec.setCongtac(true);
                }
                sqLite.update(congViec);
                finish();
            }
        }
        if (view == btnDelete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có muốn xóa công việc này không?");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    sqLite.delete(congViec.getMa());
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