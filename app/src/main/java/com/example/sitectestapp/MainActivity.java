package com.example.sitectestapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import com.example.sitectestapp.databinding.MainActivityBinding;
import com.example.sitectestapp.ui.users.UsersActivity;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainBaseContract.View {

    @Inject
    MainBaseContract.Presenter presenter;

    MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter.bindView(this);
        presenter.getUsersList();
        presenter.getUsersFromDB();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PackageManager.PERMISSION_GRANTED);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                presenter.getUidAndAuth(binding.userLoginSpinner.getSelectedItem().toString(), binding.editTextPassword.getText().toString());
            }
        });
    }

    @Override
    public String getImei() {
        Random random = new Random();
        int randomUid = random.nextInt(10000);
        String stringImei = String.valueOf(randomUid);
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return stringImei;
        }
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                stringImei = telephonyManager.getImei();
            }
            return stringImei;
        } catch (Throwable throwable) {
            return stringImei;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void addUsersToSpinner(List<String> users) {

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.userLoginSpinner.setAdapter(adapter);
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