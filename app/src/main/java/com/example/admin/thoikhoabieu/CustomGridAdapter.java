package com.example.admin.thoikhoabieu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 4/25/2018.
 */

public class CustomGridAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private List<InforTimeTable> listData;

    public CustomGridAdapter(Context context, int layout, List<InforTimeTable> listData) {
        this.context = context;
        this.layout = layout;
        this.listData = listData;
    }



    @Override
    public int getCount() {

        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtSubjects;
        TextView txtRomNumber;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.txtSubjects = view.findViewById(R.id.txtMonhoc);
            holder.txtRomNumber = view.findViewById(R.id.txtSoPhong);
            view.setTag(holder);
        }
        else
            holder = (ViewHolder) view.getTag();

        InforTimeTable inforTimeTable = listData.get(i);
        holder.txtSubjects.setText(inforTimeTable.getTenMH());
        if (inforTimeTable.getTenPhong() == null)
            holder.txtRomNumber.setText("");
        else if (!inforTimeTable.getTenPhong().equals(""))
            holder.txtRomNumber.setText("Phòng: " + inforTimeTable.getTenPhong());

        return view;

    }
}
