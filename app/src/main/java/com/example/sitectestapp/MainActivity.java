package com.example.sitectestapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.sitectestapp.data.network.SiTecApi;
import com.example.sitectestapp.ui.users.UsersActivity;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainBaseContract.View {

    @Inject
    SiTecApi siTecApi;

    @Inject
    MainBaseContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.bindView(this);
        presenter.getUsersList(siTecApi);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PackageManager.PERMISSION_GRANTED);

        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.loginButton);
        Spinner userSpinner = findViewById(R.id.userLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                presenter.setUid(siTecApi, userSpinner.getSelectedItem().toString(), editTextPassword.getText().toString());
            }
        });
    }

    @Override
    public String getIMEI() {
        Random random = new Random();
        int randomUID = random.nextInt(10000);
        String stringIMEI = String.valueOf(randomUID);
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return stringIMEI;
        }
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                stringIMEI = telephonyManager.getImei();
            }
            return stringIMEI;
        } catch (Throwable throwable) {
            return stringIMEI;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void setSpinner(List<String> users) {
        Spinner userSpinner = findViewById(R.id.userLogin);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSpinner.setAdapter(adapter);
    }

    @Override
    public void openUsersActivity() {
        try {
            startActivity(new Intent(this, UsersActivity.class));
        } catch (Exception e) {
            Log.e("Error", "MainActivity#login", e);
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}