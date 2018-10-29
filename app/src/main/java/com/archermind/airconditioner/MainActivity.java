package com.archermind.airconditioner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{
    private ImageButton btnClose,btnAc,btnXunhuan,btnQfdcw,btnHcc,btnLeftAcTemperatureUp,btnLeftAcTemperatureDown;
    private PickerView aCpickerView;
    List<String> temperatureData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    private void initViews(){
        btnClose = findViewById(R.id.btn_close);
        btnAc = findViewById(R.id.btn_ac);
        btnXunhuan = findViewById(R.id.btn_xunhuan);
        btnQfdcw = findViewById(R.id.btn_qfdcw);
        btnHcc = findViewById(R.id.btn_hcc);
        aCpickerView = findViewById(R.id.pv);
        btnLeftAcTemperatureUp = findViewById(R.id.btn_left_ac_temperature_up);
        btnLeftAcTemperatureDown = findViewById(R.id.btn_left_ac_temperature_down);

        btnClose.setOnClickListener(this);
        btnAc.setOnClickListener(this);
        btnXunhuan.setOnClickListener(this);
        btnQfdcw.setOnClickListener(this);
        btnHcc.setOnClickListener(this);
        btnLeftAcTemperatureUp.setOnClickListener(this);
        btnLeftAcTemperatureDown.setOnClickListener(this);


        /**
         * 初始化空调温度
         */
        temperatureData = new ArrayList<>();
        for(int i =31;i>15;i--){
            temperatureData.add(i+"°");
        }
        aCpickerView.setData(temperatureData);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_close:
                if(btnClose.isSelected()){
                    btnClose.setSelected(false);
                }else {
                    btnClose.setSelected(true);
                }
                break;
            case R.id.btn_ac:
                if(btnAc.isSelected()){
                    btnAc.setSelected(false);
                }else {
                    btnAc.setSelected(true);
                }
                break;
            case R.id.btn_xunhuan:
                if(btnXunhuan.isSelected()){
                    btnXunhuan.setSelected(false);
                }else {
                    btnXunhuan.setSelected(true);
                }
                break;
            case R.id.btn_qfdcw:
                if(btnQfdcw.isSelected()){
                    btnQfdcw.setSelected(false);
                }else {
                    btnQfdcw.setSelected(true);
                }
                break;
            case R.id.btn_hcc:
                if(btnHcc.isSelected()){
                    btnHcc.setSelected(false);
                }else {
                    btnHcc.setSelected(true);
                }
                break;
            case R.id.btn_left_ac_temperature_up:
                if(aCpickerView.getPosition()>0){
                    aCpickerView.setPosition(aCpickerView.getPosition()-1);
                }
                break;
            case R.id.btn_left_ac_temperature_down:
                if(aCpickerView.getPosition()<temperatureData.size()-1){
                    aCpickerView.setPosition(aCpickerView.getPosition()+1);
                }
                break;
        }
    }
}
