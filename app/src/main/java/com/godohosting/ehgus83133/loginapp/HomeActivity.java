package com.godohosting.ehgus83133.loginapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button mobileButton = (Button) findViewById(R.id.mobileButton);
        Button noticeButton = (Button) findViewById(R.id.noticeButton);
        Button homepageButton = (Button) findViewById(R.id.homepageButton);
        Button roomButton = (Button) findViewById(R.id.roomButton);
        Button myButton = (Button) findViewById(R.id.myButton);
        TextView idText = (TextView) findViewById(R.id.idText);
        TextView nameText = (TextView) findViewById(R.id.nameText);
        TextView majorText = (TextView) findViewById(R.id.majorText);


        Intent intent = getIntent();
        final String userID = intent.getStringExtra("userID");
        final String userName = intent.getStringExtra("userName");
        final String userMajor = intent.getStringExtra("userMajor");


        idText.setText(userID);
        nameText.setText(userName);
        majorText.setText(userMajor);

        mobileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, QrcodeActivity.class);
                intent.putExtra("userID", userID);
                intent.putExtra("userName", userName);
                intent.putExtra("userMajor", userMajor);
                HomeActivity.this.startActivity(intent);
            }
        });

        noticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(HomeActivity.this, MainActivity.class);
                HomeActivity.this.startActivity(intent2);
            }
        });

        homepageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://lib.kdu.ac.kr/"));
                HomeActivity.this.startActivity(intent3);
            }
        });

        roomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent4 = new Intent(HomeActivity.this, LibraryActivity.class);
                HomeActivity.this.startActivity(intent4);
            }
        });

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent5 = new Intent(HomeActivity.this, MyActivity.class);
                HomeActivity.this.startActivity(intent5);
            }
        });

    }

    private long lastTimeBackPressed;

    @Override
    public void onBackPressed() {

        if(System.currentTimeMillis() - lastTimeBackPressed < 1500) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setMessage("좌석반납이 되지 않았습니다. 반납하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("mypref", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.clear();
                        editor.commit();
                        MyActivity.mCount = 0;
                        finishAffinity();
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        finishAffinity();
                    }
                });
                builder.show();
            }
        Toast.makeText(HomeActivity.this, "뒤로 버튼을 한 번 더 눌러 종료합니다.", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }

}
