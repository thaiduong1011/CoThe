package com.example.admin.thoikhoabieu.request;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.admin.thoikhoabieu.CustomGridAdapter;
import com.example.admin.thoikhoabieu.InforTimeTable;
import com.example.admin.thoikhoabieu.R;
import com.example.admin.thoikhoabieu.ScheduleActivity;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GetList extends AsyncTask<Void, Void, Void> {
    String url;
    Context context;
    String start, end;

    public GetList(Context context, String url, String start, String end) {
        this.context = context;
        this.url = url;
        this.start = start;
        this.end = end;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        // Creating service handler class instance
        WebRequest webreq = new WebRequest();

// Making a request to url and getting response
        String jsonStr = webreq.makeWebServiceCall(url, WebRequest.GETRequest);

        ReadJson.readJSON(jsonStr);

        try {
            addDataToMainList(ScheduleActivity.arrayAllOfData, start, end);
        } catch (ParseException e) {
            e.printStackTrace();
            // Toast.makeText(context, "Đăng nhập thất bại, kiểm tra username và password", Toast.LENGTH_SHORT).show();
        }

        return null;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (ScheduleActivity.arrayAllOfData.size() > 0)
            ScheduleActivity.arrayAllOfData = new ArrayList<>();
    }

    @Override
    protected void onPostExecute(Void v) {
        super.onPostExecute(v);

        ScheduleActivity.adapter = new CustomGridAdapter(context, R.layout.list_item_layput, ScheduleActivity.arrayData);
        ScheduleActivity.gvData.setAdapter(ScheduleActivity.adapter);

    }

    public void addDataToMainList(ArrayList<InforTimeTable> dataList, String dateStart, String dateEnd) throws ParseException {
        ArrayList<InforTimeTable> tempArray = new ArrayList<>();
        Date start = ScheduleActivity.formatter.parse(dateStart);
        Date end = ScheduleActivity.formatter.parse(dateEnd);

        String dateOfItem;
        Date date;

        for (int i = 0; i < dataList.size(); i++) {
            dateOfItem = splitDate(dataList.get(i).getNgayHoc());
            date = parseToDate(dateOfItem);

            if (date.after(start) && date.before(end)) {
                tempArray.add(dataList.get(i));
            }
        }

        InforTimeTable[][] mang2chieu = new InforTimeTable[6][2];
        DateFormat format2 = new SimpleDateFormat("EEEE");
        String finalDay;


        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] weekdays = dfs.getWeekdays();

        for (int i = 0; i < tempArray.size(); i++) {
            int col = 0, row = 0;
            dateOfItem = splitDate(dataList.get(i).getNgayHoc());
            date = parseToDate(dateOfItem);
            finalDay = format2.format(date);

            for (int j = 0; j < weekdays.length; j++) {
                if (weekdays[j].equals(finalDay) && !weekdays[j].equals("Chủ nhật")) {
                    row = j - 2;
                    break;
                }

            }

            if (tempArray.get(i).getTietBD() == 1) {
                col = 0;
            } else if (tempArray.get(i).getTietBD() == 5) {
                col = 1;
            }

            mang2chieu[row][col] = tempArray.get(i);
            mang2chieu[row][col].setThu(finalDay);
            Log.d("hihi", "dong " + row + " cot " + col);
        }

        //thêm cac doi tuong vao mang 2 chieu
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                if (mang2chieu[i][j] == null)
                    mang2chieu[i][j] = new InforTimeTable();
            }
        }

        if (ScheduleActivity.arrayData.size() != 0)
            ScheduleActivity.arrayData = new ArrayList<>();

        //do nguoc du lieu tu 2 chieu vao arrayData
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                ScheduleActivity.arrayData.add(mang2chieu[i][j]);
            }
        }

        for (int i = 0; i < ScheduleActivity.arrayData.size(); i++) {
            Log.d("data", ScheduleActivity.arrayData.get(i).toString());
        }

    }


    String splitDate(String date) {
        String[] temp = date.split("T");
        return temp[0];
    }

    Date parseToDate(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
