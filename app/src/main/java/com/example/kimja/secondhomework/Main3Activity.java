package com.example.kimja.secondhomework;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    Button b1,b2;
    EditText editText1,editText2,editText3;
    TextView txt1,txt2;
    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTitle("학점 계산기");

        init();
    }

    void init(){
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        txt1 = (TextView)findViewById(R.id.txt1);
        txt2 = (TextView)findViewById(R.id.txt2);
        img1 = (ImageView)findViewById(R.id.imageView1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inPutText1 = editText1.getText().toString();
                String inPutText2 = editText2.getText().toString();
                String inPutText3 = editText3.getText().toString();
                if(inPutText1.length()==0){
                    editText1.setText("0");
                    inPutText1= "0";
                }
                if(inPutText2.length()==0){
                    editText2.setText("0");
                    inPutText2 = "0";
                }
                if(inPutText3.length()==0){
                    editText3.setText("0");
                    inPutText3= "0";
                }
                final int result,average;
                result = Integer.parseInt(inPutText1) +Integer.parseInt(inPutText2)
                        +Integer.parseInt(inPutText3);
                average = (Integer.parseInt(inPutText1) +Integer.parseInt(inPutText2)
                        +Integer.parseInt(inPutText3))/3;
                txt1.setText(result+"점");
                txt2.setText(average+"점");
                if(average>=90){
                    img1.setImageDrawable(getResources().getDrawable(R.drawable.aa));
                }else if (average >= 80){
                    img1.setImageDrawable(getResources().getDrawable(R.drawable.bb));
                }else if (average >= 70 ){
                    img1.setImageDrawable(getResources().getDrawable(R.drawable.cc));
                }else if (average >= 60 ){
                    img1.setImageDrawable(getResources().getDrawable(R.drawable.dd));
                } else {
                    img1.setImageDrawable(getResources().getDrawable(R.drawable.ff));
                }
                img1.setVisibility(View.VISIBLE);
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(editText1.getWindowToken(), 0);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText1.setText(null);
                editText2.setText(null);
                editText3.setText(null);
                img1.setVisibility(View.GONE);
                txt1.setText("0점");
                txt2.setText("0점");
                Toast.makeText(getApplicationContext(),"초기화 되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
