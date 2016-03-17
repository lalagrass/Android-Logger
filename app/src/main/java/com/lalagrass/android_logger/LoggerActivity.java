package com.lalagrass.android_logger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

public class LoggerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textView;
    private LoggerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger);

        textView = (TextView) findViewById(R.id.recyclerViewInfo);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new LoggerAdapter();
        List<LoggerData> list = LoggerStaticUtils.GetData(this);
        LoggerStaticUtils.PutData(this, "item " + list.size());
        list = LoggerStaticUtils.GetData(this);
        textView.setText("list size: " + String.valueOf(list.size()));
        adapter.setList(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
