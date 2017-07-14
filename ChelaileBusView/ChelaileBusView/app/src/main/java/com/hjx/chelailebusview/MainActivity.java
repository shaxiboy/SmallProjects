package com.hjx.chelailebusview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.hjx.chelailebusview.bean.BusDetail;
import com.hjx.chelailebusview.bean.LineDetail;

import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private ChelaileBusView busView;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        busView = (ChelaileBusView) findViewById(R.id.bus_view);
    }

    public void clickButton(View view) {
        switch (view.getId()) {
            case R.id.line122:
                loadLine("linedetail_122_1.txt");
                break;
            case R.id.line204:
                loadLine("linedetail_204_0.txt");
                break;
            case R.id.line337:
                loadLine("linedetail_337_0.txt");
                break;
            case R.id.bus204:
                loadBusData();
                break;
        }
    }

    private void loadBusData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = readString("linedetail_204_0.txt");
                LineDetail lineDetail = gson.fromJson(json, LineDetail.class);
                busView.setLineDetail(lineDetail);
                while (true) {
                    for (int i = 1; i <= 15; i++) {
                        String file = "busdetail_" + i + ".txt";
                        json = readString(file);
                        BusDetail busDetail = gson.fromJson(json, BusDetail.class);
                        busView.setBusDetail(busDetail);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    private void loadLine(final String file) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = readString(file);
                LineDetail lineDetail = gson.fromJson(json, LineDetail.class);
                busView.setLineDetail(lineDetail);
            }
        }).start();
    }

    private String readString(String file) {
        String result = "";
        try {
            Scanner scanner = new Scanner(getAssets().open(file));
            while (scanner.hasNextLine()) result += scanner.next();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
