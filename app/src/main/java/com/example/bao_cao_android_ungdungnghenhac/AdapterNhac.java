package com.example.bao_cao_android_ungdungnghenhac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterNhac extends BaseAdapter {
    private Context context;
    private int layout;
    private List<classBaiHat> listBaiHat;

    public AdapterNhac(Context context, int layout, List<classBaiHat> danhSachNhacList) {
        this.context = context;
        this.listBaiHat = danhSachNhacList;
        this.layout = layout;
    }


    @Override
    public int getCount() {
        return listBaiHat.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView Tenbh;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.Tenbh = (TextView) view.findViewById(R.id.dongnhac);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        classBaiHat danhSachNhac = listBaiHat.get(i);

        holder.Tenbh.setText(danhSachNhac.getTenbh());
        return view;
    }
}
