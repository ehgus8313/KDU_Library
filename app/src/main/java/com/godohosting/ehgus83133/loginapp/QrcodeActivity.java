package com.godohosting.ehgus83133.loginapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import android.graphics.Point;

import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Environment;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;

import android.view.Display;

import android.view.View;

import android.view.WindowManager;

import android.widget.Button;

import android.widget.EditText;

import android.widget.ImageView;

import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;



import com.google.zxing.WriterException;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import androidmads.library.qrgenearator.QRGContents;

import androidmads.library.qrgenearator.QRGEncoder;

import androidmads.library.qrgenearator.QRGSaver;



public class QrcodeActivity extends AppCompatActivity {

    String TAG = "GenerateQRCode";
    EditText edtValue;
    ImageView qrImage;
    String inputValue;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        qrImage = (ImageView) findViewById(R.id.QR_Image);
        edtValue = (EditText) findViewById(R.id.edt_value);
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




                inputValue = userID + "," + userName + "," + userMajor;
                if (inputValue.length() > 0) {
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int smallerDimension = width < height ? width : height;
                    smallerDimension = smallerDimension * 3 / 4;

                    qrgEncoder = new QRGEncoder(
                            inputValue, null,
                            QRGContents.Type.TEXT,
                            smallerDimension);
                    try {
                        bitmap = qrgEncoder.encodeAsBitmap();
                        qrImage.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        Log.v(TAG, e.toString());
                    }
                } else {
                    edtValue.setError("Required");
                }
    }
    @Override
    public void onBackPressed() {
        finish();
    }
    }