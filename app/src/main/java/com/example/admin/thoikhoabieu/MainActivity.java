package com.example.admin.thoikhoabieu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.thoikhoabieu.request.CheckUser;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    ImageView imgView;
    EditText edtTenDangNhap, edtPass;
    Button btnDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = edtTenDangNhap.getText().toString().trim();
                String password = edtPass.getText().toString().trim();
                String passMD5 = convertPassMd5(password);

               if (username.equals("") || password.equals(""))
                    Toast.makeText(MainActivity.this, "Bạn chưa điền đầy đủ thông tin vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                else {

                    new CheckUser(MainActivity.this,username, passMD5).execute();

                }
            }
        });


    }

    public void anhXa() {

        imgView = findViewById(R.id.imgView);
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtPass = findViewById(R.id.edtPass);
        btnDangNhap = findViewById(R.id.btnDangNhap);
    }

    public static String convertPassMd5(String pass) {
        String password = null;
        MessageDigest mdEnc;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(pass.getBytes(), 0, pass.length());
            pass = new BigInteger(1, mdEnc.digest()).toString(16);
            while (pass.length() < 32) {
                pass = "0" + pass;
            }
            password = pass;
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        return password;
    }


}