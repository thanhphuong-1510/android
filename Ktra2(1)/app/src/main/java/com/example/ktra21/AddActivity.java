package com.example.ktra21;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ktra21.database.SQLite;
import com.example.ktra21.model.CongViec;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    EditText addTen, addND, addNgayHT;
    Spinner addTT;

    SQLite sqLite;
    RadioButton btn1,btn2;
    Button btnCancel, btnLuu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        addTen = findViewById(R.id.addTen);
        addND = findViewById(R.id.addND);
        addNgayHT = findViewById(R.id.addNgayHT);
        addTT = findViewById(R.id.addTT);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btnLuu = findViewById(R.id.btnLuu);
        btnCancel = findViewById(R.id.btnCancel);
        addTT.setAdapter(new ArrayAdapter<>(this,R.layout.item_spinner, getResources().getStringArray(R.array.tinh_trang)));

        addNgayHT.setOnClickListener(this);
        sqLite = new SQLite(this);
        btnLuu.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == addNgayHT) {
            Calendar calendar = Calendar.getInstance();
            Integer year = calendar.get(Calendar.YEAR);
            Integer month = calendar.get(Calendar.MONTH);
            Integer day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                    addNgayHT.setText(s);
                }
            }, year, month, day);
            datePickerDialog.show();
        }
        if(view == btnLuu){
            CongViec congViec = new CongViec();
            if(addTen.getText().toString().isEmpty()){
                Toast.makeText(this, "Không bỏ trống tên", Toast.LENGTH_SHORT).show();
            }else {
                congViec.setTen(addTen.getText().toString());
                congViec.setNoidung(addND.getText().toString());
                congViec.setNgayHT(addNgayHT.getText().toString());
                congViec.setTinhtrang(addTT.getSelectedItem().toString());
                if(btn1.isChecked()){
                    congViec.setCongtac(true);
                }else {
                    congViec.setCongtac(false);
                }
                sqLite.add(congViec);
                finish();
            }
        }
        if(view == btnCancel){
            finish();
        }
    }
}