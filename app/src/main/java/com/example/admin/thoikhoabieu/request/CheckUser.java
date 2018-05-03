package com.example.admin.thoikhoabieu.request;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.thoikhoabieu.Common;
import com.example.admin.thoikhoabieu.ScheduleActivity;

public class CheckUser extends AsyncTask<Void, Void, String> {
    Context context;
    String url;
    String username, pass;

    public CheckUser(Context context, String username, String pass) {
        this.context = context;
        this.username = username;
        this.pass = pass;
    }

    @Override
    protected String doInBackground(Void... voids) {
        url = Common.UrlGetData + username + "?" + "password" + "=" + pass;
        Log.d("AAAA", url);

        WebRequest webreq = new WebRequest();
        String jsonStr = webreq.makeWebServiceCall(url, WebRequest.GETRequest);

        return jsonStr;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d("AAAA", "result = " + result);
        if (result.equals("null")) {
            Toast.makeText(context, "Kiểm tra đăng nhập!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(context, ScheduleActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("password", pass);
        context.startActivity(intent);

    }
}

