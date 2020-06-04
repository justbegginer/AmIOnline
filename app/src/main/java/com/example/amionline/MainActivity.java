package com.example.amionline;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBottomClick(View view) {
        String ipAddress = "8.8.8.8";

        TextView textView = findViewById(R.id.textView);
        textView.setText("Проверка сети , пожалуйста ожидайте");

        ImageView connectResult;

        IpCheckTask ipCheckTask = new IpCheckTask();
        try {
            boolean isConnected = ipCheckTask.execute(ipAddress)
                    .get(5000, TimeUnit.MILLISECONDS);

            if (isConnected) {
                textView.setText("Подключение к интернету успешное");
                connectResult = findViewById(R.id.goodConnect);
                connectResult.setVisibility(View.VISIBLE);
            } else {
                logError(textView,"Нет подключения к интернету");
            }
        } catch (ExecutionException e) {
            Throwable realException = e.getCause();
            if (realException != null) {
                realException.printStackTrace();
                logError(textView, realException.getLocalizedMessage());
            } else {
                logError(textView, "Execution problem");
            }
        } catch (InterruptedException e) {
            logError(textView, "Was interrupted");
        } catch (TimeoutException e) {
            logError(textView, "Timeout");
        }
    }

    private void logError(TextView textView, String msg) {
        textView.setText(msg);
        ImageView connectResult = findViewById(R.id.badConnect);
        connectResult.setVisibility(View.VISIBLE);
    }
}
