package com.example.kimja.secondhomework;

import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
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

import static android.R.attr.calendarTextColor;
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
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        setTitle("레스토랑 예약시스템");



        init();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    void init(){
        //선언
        aSwitch = (Switch)findViewById(R.id.switch1);
        textview = (TextView)findViewById(R.id.textView);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);

        page1 = (LinearLayout)findViewById(R.id.page1);
        datePicker =(DatePicker)findViewById(R.id.datePicker);
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        datePicker.init(gregorianCalendar.get(Calendar.YEAR),gregorianCalendar.get(Calendar.MONTH)+1
                ,gregorianCalendar.get(Calendar.DAY_OF_MONTH),null);

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
                        public void onClick(View v) { //다음 버튼이 눌릴때
                            //각각의 페이지 표시
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
                                //inputs
                                String inputadult,inputteenager,inputkid;
                                inputadult = adult.getText().toString();
                                inputteenager = teenager.getText().toString();
                                inputkid = kid.getText().toString();


                                //예외처리
                                if (inputadult.getBytes().length <=0) inputadult = "0";
                                if (inputteenager.getBytes().length<=0) inputteenager = "0";
                                if (inputkid.getBytes().length <=0) inputkid = "0";
                                if(Integer.parseInt(inputadult) == 0 && Integer.parseInt(inputkid)
                                        == 0 && Integer.parseInt(inputteenager) == 0){
                                    Toast.makeText(getApplication(), "0명은 예약할 수 없습니다. 다시입력해 주세요."
                                            ,Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                //페이지 표시
                                page3.setVisibility(View.INVISIBLE);
                                page4.setVisibility(View.VISIBLE);
                                page++;
                                b2.setEnabled(false);

                                //결과 값 출력
                                text1.setText(datePicker.getYear()+"년 "+datePicker.getMonth()+"월 "
                                        +datePicker.getDayOfMonth()+"일");
                                text2.setText(timePicker.getHour()+"시 "+timePicker.getMinute()+"분");
                                text3.setText(Integer.parseInt(inputadult)+"명");
                                text4.setText(Integer.parseInt(inputteenager)+"명");
                                text5.setText(Integer.parseInt(inputkid)+"명");


                            }
                        }
                    });

                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) { //이전버튼이 눌릴 때
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
                else{ //스위치가 헤제됬을때
                    //모든 변수와 핸들러 초기화

                    mHandler.removeMessages(0);
                    value = 0;
                    page = 1;
                    page1.setVisibility(View.INVISIBLE);
                    datePicker.init(gregorianCalendar.get(Calendar.YEAR),gregorianCalendar.get(Calendar.MONTH)+1
                            ,gregorianCalendar.get(Calendar.DAY_OF_MONTH),null);
                    page2.setVisibility(View.INVISIBLE);
                    timePicker.setCurrentHour(gregorianCalendar.get(Calendar.HOUR));
                    timePicker.setCurrentMinute(gregorianCalendar.get(Calendar.MINUTE));
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
