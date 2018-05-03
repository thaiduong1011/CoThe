package com.example.admin.thoikhoabieu.request;

import android.util.Log;

import com.example.admin.thoikhoabieu.InforTimeTable;
import com.example.admin.thoikhoabieu.ScheduleActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadJson {

    public static void readJSON(String json) {

        if (json != null) {
            try {
// Getting JSON Array node
                JSONArray response = new JSONArray(json);

// looping through All Students
                for (int i = 0; i < response.length(); i++) {
                    JSONObject object = response.getJSONObject(i);
                    ScheduleActivity.arrayAllOfData.add(new InforTimeTable(
                            object.getString("MaMH"),
                            object.getString("TenMH"),
                            object.getString("TenPhong"),
                            object.getString("NgayHoc"),
                            object.getInt("TietBatDau"),
                            object.getString("GiangVien"),
                            object.getString("Lop")
                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "No data received from HTTP request");
        }
    }
}
