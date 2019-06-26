package com.godohosting.ehgus83133.loginapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LibraryActivity extends AppCompatActivity {
    private AlertDialog dialog;
    private boolean room = true;
    private String RoomNum;
    private Spinner spinner;
    private ArrayAdapter adapter;

    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date date = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm");
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(date);
    TextView dateNow;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        final EditText roomText = (EditText) findViewById(R.id.roomText);
        final TextView timeText = (TextView) findViewById(R.id.timeTx);
        spinner = (Spinner) findViewById(R.id.roomSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.Libroom, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        dateNow = (TextView) findViewById(R.id.dateNow);
        dateNow.setText(formatDate);    // TextView 에 현재 시간 문자열 할당






        final Button registerButton = (Button) findViewById(R.id.roomButton);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String RoomNum = roomText.getText().toString();
                String str_season = spinner.getSelectedItem().toString();


                if (RoomNum.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LibraryActivity.this);
                    dialog = builder.setMessage("좌석을 입력해 주세요.")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                else {
                    Toast.makeText(LibraryActivity.this, "좌석발권에 성공했습니다.", Toast.LENGTH_LONG).show();
                    finish();
                }

                SharedPreferences pref= getSharedPreferences("mypref", Activity.MODE_PRIVATE);

                SharedPreferences.Editor editor = pref.edit();
                editor.putString("data1", formatDate);
                editor.putString("data2", RoomNum);
                editor.putString("data3", str_season);
                editor.commit();
            }
        });

    }
}
