package com.example.amionline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onBottomClick(View view)throws UnknownHostException, IOException {

        String ipAddress = "8.8.8.8";
        InetAddress inet = InetAddress.getByName(ipAddress);

        Button button = findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);

        TextView textView = findViewById(R.id.textView);
        textView.setText("Проверка сети , пожалуйста ожидайте");

        ImageView connectResult;

            if (inet.isReachable(5000)) {
                textView.setText("Подключение к интернету успешное");
                connectResult = findViewById(R.id.goodConnect);
                connectResult.setVisibility(View.VISIBLE);
            } else {
                textView.setText("Нет подключения к интернету");
                connectResult = findViewById(R.id.badConnect);
                connectResult.setVisibility(View.VISIBLE);
            }
            connectResult.setVisibility(View.INVISIBLE);
            //System.out.println("Exception");
            //e.getLocalizedMessage();
            //System.out.println(e.getLocalizedMessage());


        textView.setText("Проверка сети , пожалуйста ожидайте");
        button.setVisibility(View.VISIBLE);
    }
}
