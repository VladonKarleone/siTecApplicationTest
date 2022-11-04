package com.example.sitectestapp.ui.users;

import android.os.Bundle;
import android.widget.Toast;
import com.example.sitectestapp.databinding.UsersActivityBinding;

import java.util.List;
import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;

public class UsersActivity extends DaggerAppCompatActivity implements UsersBaseContract.View {
    @Inject
    public UsersBaseContract.Presenter presenter;

    UsersActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UsersActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
        binding.responseField.setText(responses.toString()
                .replace(",", "")
                .replace("[", " ")
                .replace("]", ""));
    }
}
