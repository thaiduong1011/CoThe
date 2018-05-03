package com.example.admin.thoikhoabieu;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.thoikhoabieu.request.GetList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleActivity extends AppCompatActivity {

    String url;
    TextView txtTKb, txtTuan;

    public static GridView gvData;
    public static ArrayList<InforTimeTable> arrayAllOfData;
    public static ArrayList<InforTimeTable> arrayData;
    public static  CustomGridAdapter adapter;

    Spinner spinner;
    ArrayAdapter<String> adapterSpinnerDanhMuc;
    Map<String, Week> WeekList = new HashMap<String, Week>();

    String stringStartHK = "23-04-2018";
    String stringEndHK = "12-05-2018";
    Date dateStartHK;
    Date dateEndHK;
    public static SimpleDateFormat formatter;
    List<String> keys = new ArrayList<>();

    String sdtCode;
    String sdtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        anhXa();
        Intent intent = getIntent();
        sdtCode = intent.getStringExtra("username");
        sdtPass = intent.getStringExtra("password");
        url = Common.UrlGetData + sdtCode + "?" + "password" + "=" + sdtPass;


        formatter = new SimpleDateFormat("dd-MM-yyyy");
        initSpinner();
        spinner.setOnItemSelectedListener(new MyEvent());

        gvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                dialogDetail(arrayData.get(i));
            }
        });

    }

    private void initSpinner() {

        try {

            dateStartHK = formatter.parse(stringStartHK);
            dateEndHK = formatter.parse(stringEndHK);

            getWeeksBetween(dateStartHK, dateEndHK);

        } catch (ParseException e) {
            e.printStackTrace();
        }

//        List<String> keys = new ArrayList<>();
//        for (Map.Entry me : WeekList.entrySet()) {
//            keys.add(me.getKey().toString());
//        }

        adapterSpinnerDanhMuc = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, keys);

        //xu ly SpinnerDanhMuc
        adapterSpinnerDanhMuc.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        //thiết lập adapter cho Spinner
        spinner.setAdapter(adapterSpinnerDanhMuc);
    }


    //hop thoai dialog hien ra
    private void dialogDetail(InforTimeTable inforTimeTable) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_detail);

        TextView txtMonHoc = dialog.findViewById(R.id.txtTitle);
        TextView txtMaMH = dialog.findViewById(R.id.txtMaMonHoc);
        TextView txtTenMH = dialog.findViewById(R.id.txtTenMH);
        TextView txtTenPhong = dialog.findViewById(R.id.txtTenPhong);
        TextView txtTenThu = dialog.findViewById(R.id.txtTenThu);
        TextView txtTiet = dialog.findViewById(R.id.txtTiet);
        TextView txtGiangVien = dialog.findViewById(R.id.txtTenGV);
        TextView txtLop = dialog.findViewById(R.id.txtTenLop);

        txtMonHoc.setText(inforTimeTable.getTenMH());
        txtMaMH.setText(inforTimeTable.getMaMH());
        txtTenMH.setText(inforTimeTable.getTenMH());
        txtTenPhong.setText(inforTimeTable.getTenPhong());
        txtTenThu.setText(inforTimeTable.getTenMH());
        txtTiet.setText(inforTimeTable.getTietBD() + "");
        txtGiangVien.setText(inforTimeTable.getGiangVien());
        txtLop.setText(inforTimeTable.getLop());


        dialog.setTitle("Thông tin chi tiết");
        dialog.show();
    }

    private void anhXa() {
        arrayAllOfData = new ArrayList<>();
        arrayData = new ArrayList<>();

        gvData = (GridView) findViewById(R.id.gridView);



        spinner = findViewById(R.id.spinner);

        txtTKb = findViewById(R.id.txtTKb);
        txtTuan = findViewById(R.id.txtTuanDetail);
    }

    public ArrayList<InforTimeTable> twoDArrayToList(InforTimeTable[][] twoDArray) {
        ArrayList<InforTimeTable> list = new ArrayList<InforTimeTable>();
        for (InforTimeTable[] array : twoDArray) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }

    public int getWeeksBetween(Date a, Date b) {

        if (b.before(a)) {
            return -getWeeksBetween(b, a);
        }
        a = resetTime(a);
        b = resetTime(b);

        Calendar cal = new GregorianCalendar();
        cal.setTime(a);
        int weeks = 0;

        String start = null;
        String end = null;
        while (cal.getTime().before(b)) {
            // add another week
            start = formatter.format(cal.getTime());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(cal.getTime());
            calendar.add(Calendar.DATE, + 6);
            end = formatter.format(calendar.getTime());

            WeekList.put("Tuần " + (weeks + 1), new Week(start, end));
            keys.add("Tuần " + (weeks + 1));

            cal.add(Calendar.WEEK_OF_YEAR, 1);
            weeks++;

        }
        return weeks;
    }

    public static Date resetTime(Date d) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private class MyEvent implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            txtTuan.setText("Tuần " + (i + 1) + ": " + WeekList.get(keys.get(i)).toString());
            arrayData = new ArrayList<>();
            new GetList(ScheduleActivity.this, url, WeekList.get(keys.get(i)).getStartDate(), WeekList.get(keys.get(i)).getEndDate()).execute();

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


}


