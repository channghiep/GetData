package com.example.getdata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    private OkHttpClient client;
    private Request request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Button b = this.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText inputText = findViewById(R.id.editText);
                String getText = inputText.getText().toString();
                client = new OkHttpClient();
                String url = "https://learn.operatoroverload.com/rental/"+ getText;
                request = new Request.Builder().url(url).build();

                Thread t =new Thread(){
                    @Override
                    public void run(){
                        super.run();
                        try
                        {
                            final Response response = client.newCall(request).execute();
                            final String response_string = response.body().string();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    TextView v = findViewById(R.id.text);
                                    v.setText(response_string);
                                }
                            });
                        }catch(IOException e){

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    TextView v = findViewById(R.id.text);
                                    v.setText("error");
                                }
                            });
                        }
                    }
                };
                t.start();
            }
        });





    }
}
