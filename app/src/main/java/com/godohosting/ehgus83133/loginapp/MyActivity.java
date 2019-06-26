package com.godohosting.ehgus83133.loginapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyActivity extends AppCompatActivity {

    int max = 5;
    public static int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        TextView room = (TextView) findViewById(R.id.NumText);
        TextView seat = (TextView) findViewById(R.id.RoomText);
        TextView seatText = (TextView) findViewById(R.id.SeatText);
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        TextView timeText = (TextView) findViewById(R.id.time);
        final TextView number = (TextView) findViewById(R.id.plusNum);
        Button plusButton = (Button) findViewById(R.id.button1);
        Button returnButton = (Button) findViewById(R.id.button2);



        //Intent intent = getIntent();
        //final String roomTx = intent.getStringExtra("RoomNum") + "번 사용 중";
        //final String seatNum = intent.getStringExtra("it3_season");
        //final String time = intent.getStringExtra("time");

        SharedPreferences pref= getSharedPreferences("mypref", Activity.MODE_PRIVATE);
        String roomTx = pref.getString("data2", "0"); // 키값으로 꺼냄
        String seatNum = pref.getString("data3", "0");
        String time = pref.getString("data1", "0");


        room.setText(roomTx);
        seat.setText(seatNum);
        timeText.setText(time);










        plusButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mCount < 5) {
                    final CharSequence[] items = new CharSequence[]{"1 시간", "2 시간", "3 시간"};
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MyActivity.this);
                    dialog.setTitle("연장 시간 선택하여 주십시오");
                    dialog.setItems(items, new DialogInterface.OnClickListener() {
                        // 리스트 선택 시 이벤트
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(MyActivity.this, items[which], Toast.LENGTH_SHORT).show();
                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(MyActivity.this);
                            builder.setMessage(items[which] + " 연장이 완료 되었습니다.")
                                    .setNegativeButton("확인",null)
                                    .create()
                                    .show();
                        }
                    });
                    dialog.show();
                    mCount++;
                    int rr = max- mCount;
                    number.setText(Integer.valueOf(mCount) + " (" + rr + "회 남음)");
                } else {
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(MyActivity.this);
                    builder.setMessage("연장횟수를 초과했습니다.")
                            .setNegativeButton("확인",null)
                            .create()
                            .show();
                }
                if (mCount == 5) {
                    number.setText("5 (0회 남음)");
                }
            }
        });


        returnButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(MyActivity.this);
                builder.setMessage("반납하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                SharedPreferences pref = getSharedPreferences("mypref", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                mCount=0;
                finish();

                    }
                });
                builder.setNegativeButton("취소",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }

            });




        if (seatNum == "0") {
            seatText.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
            room.setText(null);
            seat.setText(null);
            timeText.setText(null);
            number.setText(null);
        }else {
            seatText.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
        }


    }
    @Override
    public void onBackPressed() {
        finish();
        }

}
