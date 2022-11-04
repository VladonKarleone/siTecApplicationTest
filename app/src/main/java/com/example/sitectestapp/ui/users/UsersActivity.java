package com.example.sitectestapp.ui.users;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sitectestapp.R;
import com.example.sitectestapp.data.network.SiTecApi;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class UsersActivity extends DaggerAppCompatActivity implements UsersBaseContract.View {
    @Inject
    public UsersBaseContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_list_activity);
        presenter.bindView(this);
        presenter.setText();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResponses(List<String> responses) {
        TextView responseField = findViewById(R.id.responseField);
        for (int i = 0; i < responses.size(); i++) {
            responseField.setText(responseField.getText().toString() + responses.get(i));
        }
    }
}
