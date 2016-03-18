package com.lalagrass.android_logger;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoggerAdapter extends RecyclerView.Adapter<LoggerAdapter.ViewHolder> {

    private List<LoggerData> list = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tMsg;
        private final TextView tDate;

        public ViewHolder(View v) {
            super(v);
            tMsg = (TextView) v.findViewById(R.id.tMsg);
            tDate = (TextView) v.findViewById(R.id.tDate);
        }

        public TextView getTextMsg() {
            return tMsg;
        }

        public TextView getTextDate() {
            return tDate;
        }
    }

    public LoggerAdapter() {
    }

    public void setList(List<LoggerData> l) {
        list = l;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.logger_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        LoggerData data = list.get(position);
        viewHolder.getTextMsg().setText(data.LoggerMsg);
        viewHolder.getTextDate().setText(DateFormat.getDateTimeInstance().format(new Date(data.LoggerDate)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}