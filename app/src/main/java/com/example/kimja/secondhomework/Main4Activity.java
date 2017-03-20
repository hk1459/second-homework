package com.example.kimja.secondhomework;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import static android.R.attr.value;

public class Main4Activity extends AppCompatActivity {
    TextView textview;
    Switch aSwitch;
    Button b1,b2;
    DatePicker datePicker;
    LinearLayout page1,page2,page4;
    GridLayout page3;
    FrameLayout framelayout;
    EditText adult,teenager,kid;
    TimePicker timePicker;
    TextView text1,text2,text3,text4,text5;
    int value = 0;
    int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        setTitle("레스토랑 예약시스템");



        init();
    }
    void init(){
        //선언
        aSwitch = (Switch)findViewById(R.id.switch1);
        textview = (TextView)findViewById(R.id.textView);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);

        page1 = (LinearLayout)findViewById(R.id.page1);
        datePicker =(DatePicker)findViewById(R.id.datePicker);

        page2 = (LinearLayout)findViewById(R.id.page2);
        timePicker = (TimePicker)findViewById(R.id.timePicker);

        page3 = (GridLayout)findViewById(R.id.page3);
        adult = (EditText)findViewById(R.id.editText1);
        teenager = (EditText)findViewById(R.id.editText2);
        kid = (EditText)findViewById(R.id.editText3);

        page4 = (LinearLayout)findViewById(R.id.page4);
        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);
        text4 = (TextView)findViewById(R.id.text4);
        text5 = (TextView)findViewById(R.id.text5);

        framelayout = (FrameLayout)findViewById(R.id.framlayout);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //swich가 체크됬을때
                if(isChecked == true){
                    page1.setVisibility(View.VISIBLE);
                    b1.setVisibility(View.VISIBLE);
                    b2.setVisibility(View.VISIBLE);
                    mHandler.sendEmptyMessage(0);
                    framelayout.setVisibility(View.VISIBLE);
                    b1.setEnabled(false);
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(page == 1){
                                page1.setVisibility(View.INVISIBLE);
                                page2.setVisibility(View.VISIBLE);
                                page++;
                                b1.setEnabled(true);
                            } else if(page ==2) {
                                page2.setVisibility(View.INVISIBLE);
                                page3.setVisibility(View.VISIBLE);
                                page++;
                            } else if(page == 3) {
                                page3.setVisibility(View.INVISIBLE);
                                page4.setVisibility(View.VISIBLE);
                                page++;
                                b2.setEnabled(false);
                                //inputs

                                String inputadult,inputteenager,inputkid;
                                inputadult = adult.getText().toString();
                                inputteenager = teenager.getText().toString();
                                inputkid = kid.getText().toString();


                                //표시
                                text3.setText(Integer.parseInt(inputadult)+"명");
                                text4.setText(Integer.parseInt(inputteenager)+"명");
                                text5.setText(Integer.parseInt(inputkid)+"명");


                            }
                        }
                    });

                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(page == 2){
                                page2.setVisibility(View.INVISIBLE);
                                page1.setVisibility(View.VISIBLE);
                                page--;
                                b1.setEnabled(false);
                            } else if(page ==3) {
                                page3.setVisibility(View.INVISIBLE);
                                page2.setVisibility(View.VISIBLE);
                                page--;
                            } else if(page == 4) {
                                page4.setVisibility(View.INVISIBLE);
                                page3.setVisibility(View.VISIBLE);
                                page--;
                                b2.setEnabled(true);

                            }
                        }
                    });



                }
                else{
                    mHandler.removeMessages(0);
                    value = 0;
                    page = 1;
                    page1.setVisibility(View.INVISIBLE);

                    page2.setVisibility(View.INVISIBLE);
                    page3.setVisibility(View.INVISIBLE);
                    adult.setText(null);
                    teenager.setText(null);
                    kid.setText(null);

                    page4.setVisibility(View.INVISIBLE);
                    framelayout.setVisibility(View.INVISIBLE);
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b1.setEnabled(false);
                    b2.setEnabled(true);
                    textview.setText("예약시작 경과시간 : 00:00");

                }

            }
        });


    }
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            int min = value/60;
            int sec = value%60;
            String min1,sec1;
            min1 = String.format("%02d",min);
            sec1 = String.format("%02d",sec);
            textview.setText("예약시작 경과시간 : "+min1+":"+sec1);
            value = value+1;
            // 메세지를 처리하고 또다시 핸들러에 메세지 전달 (1000ms 지연)
            mHandler.sendEmptyMessageDelayed(0,1000);
        }
    };



}
