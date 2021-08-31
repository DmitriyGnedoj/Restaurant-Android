package com.example.myapplication124124124124;

import android.content.Intent;
import android.os.StrictMode;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    EditText textLogin,textPassword;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        textLogin = findViewById(R.id.textLogin);
        textPassword = findViewById(R.id.textPassword);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://192.168.1.42/read.php").newBuilder();
                    urlBuilder.addQueryParameter("login", textLogin.getText().toString());

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
                                        //txtInfo.setText(response.body().string());

                                        try {
                                            String data = response.body().string();

                                            JSONArray jsonArray = new JSONArray(data);
                                            JSONObject jsonObject;

                                            jsonObject = jsonArray.getJSONObject(0);

                                         String id= jsonObject.getString("id");
                                       String pwd =  jsonObject.getString("password");
                                       if(textPassword.getText().toString().equals(pwd)) {
                                           Intent intent = new Intent(MainActivity.this, MainWindow.class);
                                           intent.putExtra("id", id);
                                           startActivity(intent);
                                       }else{
                                           Toast toast = Toast.makeText(getApplicationContext(),
                                                   "Неправильный логин  или  пароль", Toast.LENGTH_SHORT);
                                           toast.show();
                                           textPassword.setText("");
                                       }
                                        } catch (JSONException e) {
                                            //txtInfo.setText(e.getMessage());
                                            Toast toast = Toast.makeText(getApplicationContext(),
                                                    e.getMessage(), Toast.LENGTH_SHORT);
                                            toast.show();
                                        }


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

    }



}