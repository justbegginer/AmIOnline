package com.example.amionline;

import android.os.AsyncTask;

import java.net.InetAddress;

public class IpCheckTask extends AsyncTask<String, Void, Boolean> {
    @Override
    protected Boolean doInBackground(String... strings) {
        String ipAddress = strings[0];
        try {
            InetAddress inet = InetAddress.getByName(ipAddress);

            return inet.isReachable(5000);
        } catch (Exception e) {
            return false;
        }
    }
}
