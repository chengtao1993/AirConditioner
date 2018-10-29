package com.archermind.airconditioner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{
    private ImageButton btnClose,btnAc,btnXunhuan,btnQfdcw,btnHcc,
            btnLeftAcTemperatureUp,btnLeftAcTemperatureDown,
            btnRightAcTemperatureUp,btnRightAcTemperatureDown;
    private LeftPickerView aCLeftPickerView;
    private  RightPickerView aCRightPickerView;
    List<String> leftTemperatureData,rightTemperatureData;
    private Button btnSyncTemperature;



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
        btnSyncTemperature = findViewById(R.id.btn_sync_temperature);

        aCLeftPickerView = findViewById(R.id.left_pv);
        btnLeftAcTemperatureUp = findViewById(R.id.btn_left_ac_temperature_up);
        btnLeftAcTemperatureDown = findViewById(R.id.btn_left_ac_temperature_down);

        aCRightPickerView = findViewById(R.id.right_pv);
        btnRightAcTemperatureUp = findViewById(R.id.btn_right_ac_temperature_up);
        btnRightAcTemperatureDown = findViewById(R.id.btn_right_ac_temperature_down);

        btnClose.setOnClickListener(this);
        btnAc.setOnClickListener(this);
        btnXunhuan.setOnClickListener(this);
        btnQfdcw.setOnClickListener(this);
        btnHcc.setOnClickListener(this);
        btnSyncTemperature.setOnClickListener(this);

        btnLeftAcTemperatureUp.setOnClickListener(this);
        btnLeftAcTemperatureDown.setOnClickListener(this);

        btnRightAcTemperatureUp.setOnClickListener(this);
        btnRightAcTemperatureDown.setOnClickListener(this);


        /**
         * 初始化空调温度
         */
        leftTemperatureData = new ArrayList<>();
        for(int i =31;i>15;i--){
            leftTemperatureData.add(i+"°");
        }
        aCLeftPickerView.setData(leftTemperatureData);

        rightTemperatureData = new ArrayList<>();
        for(int i =31;i>15;i--){
            rightTemperatureData.add(i+"°");
        }
        aCRightPickerView.setData(rightTemperatureData);
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
                if(aCLeftPickerView.getPosition()>0){
                    aCLeftPickerView.setPosition(aCLeftPickerView.getPosition()-1);
                }
                break;
            case R.id.btn_left_ac_temperature_down:
                if(aCLeftPickerView.getPosition()<leftTemperatureData.size()-1){
                    aCLeftPickerView.setPosition(aCLeftPickerView.getPosition()+1);
                }
                break;
            case R.id.btn_right_ac_temperature_up:
                if(aCRightPickerView.getPosition()>0){
                    aCRightPickerView.setPosition(aCRightPickerView.getPosition()-1);
                }
                break;

            case R.id.btn_right_ac_temperature_down:
                if(aCRightPickerView.getPosition()<rightTemperatureData.size()-1){
                    aCRightPickerView.setPosition(aCRightPickerView.getPosition()+1);
                }
                break;
            case R.id.btn_sync_temperature:
                if(btnSyncTemperature.isSelected()){
                    btnSyncTemperature.setSelected(false);
                }else {
                    btnSyncTemperature.setSelected(true);
                }
                break;
        }
    }
}
