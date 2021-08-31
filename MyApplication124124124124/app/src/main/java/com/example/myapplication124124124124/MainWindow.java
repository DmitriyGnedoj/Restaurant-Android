package com.example.myapplication124124124124;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import androidx.appcompat.app.AppCompatActivity;

public class MainWindow extends AppCompatActivity {
    private static final int NOTIFY_ID = 101;

    // Идентификатор канала
    private static String CHANNEL_ID = "Cat channel";
    TextView txtInfo,textInfoWaiter, numberOrder;
    EditText dish,  count,  table;
    Button btnCreate, btnRead, btnCheck, btnClose,button2;
String getId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInfo = (TextView) findViewById(R.id.txtInfo);
        textInfoWaiter = (TextView) findViewById(R.id.textInfoWaiter);
        dish = (EditText) findViewById(R.id.dish);
        count = (EditText) findViewById(R.id.count);
        table = (EditText) findViewById(R.id.table);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnCheck = (Button) findViewById(R.id.btnCheck);
        btnClose = (Button) findViewById(R.id.btnClose);
        numberOrder = (TextView) findViewById(R.id.numberOrder);
        button2 = (Button) findViewById(R.id.button2);
        button2.setVisibility(View.INVISIBLE);
        getId = getIntent().getStringExtra("id");
        textInfoWaiter.setText(getId);




        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Functions functions = new Functions();
                String date = functions.getData();
                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://192.168.1.42/getdata.php").newBuilder();
                    urlBuilder.addQueryParameter("dish", dish.getText().toString());
                    urlBuilder.addQueryParameter("count", count.getText().toString());
                    urlBuilder.addQueryParameter("table", table.getText().toString());
                    urlBuilder.addQueryParameter("waiter", textInfoWaiter.getText().toString());


                    String url = urlBuilder.build().toString();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {

                                     clearFields();
                                        txtInfo.setText(response.body().string());
                                        String str = txtInfo.getText().toString();
                                        String number = new Functions().numbers(str);
                                        numberOrder.setText(number);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }

                        ;
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://192.168.1.42/neworder.php").newBuilder();
                    urlBuilder.addQueryParameter("dish", dish.getText().toString());
                    urlBuilder.addQueryParameter("count", count.getText().toString());
                    urlBuilder.addQueryParameter("table", table.getText().toString());
                    urlBuilder.addQueryParameter("waiter", textInfoWaiter.getText().toString());
                    urlBuilder.addQueryParameter("id_order", numberOrder.getText().toString());


                    String url = urlBuilder.build().toString();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {

                                        txtInfo.setText(response.body().string());


                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }

                        ;
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://192.168.1.42/check.php").newBuilder();
                    urlBuilder.addQueryParameter("check", textInfoWaiter.getText().toString());


                    String url = urlBuilder.build().toString();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        txtInfo.setText(response.body().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }

                        ;
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override

                public void onClick(View v) {


                    try {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        OkHttpClient client = new OkHttpClient();

                        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://192.168.1.42/closeorder.php").newBuilder();
                        urlBuilder.addQueryParameter("number", dish.getText().toString());

                        String url = urlBuilder.build().toString();

                        Request request = new Request.Builder()
                                .url(url)
                                .build();

                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, final Response response) throws IOException {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        try {
                                            txtInfo.setText(response.body().string());
                                            table.setVisibility(View.VISIBLE);
                                            count.setVisibility(View.VISIBLE);
                                            dish.setText("");
                                            dish.setHint("Блюдо");
                                            button2.setVisibility(View.INVISIBLE);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                            }

                            ;
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table.setVisibility(View.INVISIBLE);
                count.setVisibility(View.INVISIBLE);
                dish.setHint("Номер заказа");
                button2.setVisibility(View.VISIBLE);
            }
        /*    @Override
            public void onClick(View view) {


                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://192.168.1.42/delete.php").newBuilder();
                    //urlBuilder.addQueryParameter("PID", txtPID.getText().toString());

                    String url = urlBuilder.build().toString();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        txtInfo.setText(response.body().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }

                        ;
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/
        });
    }

    private void clearFields() {
        dish.setText("");
        table.setText("");
        count.setText("");
    }
}
