package com.abdo.montgat.ui.login.forget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.abdo.montgat.R;
import com.abdo.montgat.ui.login.LoginActivity;

public class DoneUpdateActivity3 extends AppCompatActivity {

    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_update3);

        initView();
    }

    private void initView() {
        login = findViewById(R.id.login_btn_forget4);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoneUpdateActivity3.this, LoginActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

        return;
    }
}