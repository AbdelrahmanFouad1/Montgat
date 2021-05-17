package com.abdo.montgat.ui.login.Register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.abdo.montgat.R;
import com.abdo.montgat.utlis.Constant;

import java.util.Calendar;

public class SignUpActivity2 extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private ImageView back;
    private Button next;
    private TextView error;
    private Calendar myCalendar;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        initView();
    }

    private void initView() {
        next = findViewById(R.id.next_btn_signUp2);
        back = findViewById(R.id.back_image_signUp2);
        error = findViewById(R.id.error_rb_signUp2);
        radioGroup = findViewById(R.id.radioGroup);
        datePicker = findViewById(R.id.age_dp_signUp2);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioGroup.getCheckedRadioButtonId() == -1) {

                    // no radio buttons are checked
                    error.setVisibility(View.VISIBLE);
                    return;
                } else {
                    // one of the radio buttons is checked
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(selectedId);
                    error.setVisibility(View.GONE);
                }

                String day = datePicker.getDayOfMonth()+"";
                String month = datePicker.getMonth() + 1 + "";
                String year = datePicker.getYear()+"";


                String dateString = day + "-" + month + "-" + year;


                startActivity(new Intent(SignUpActivity2.this, SignUpActivity3.class));

                Constant.setDefaults("gender", radioButton.getText().toString(), getBaseContext());
                Constant.setDefaults("birthDay", dateString, getBaseContext());
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity2.this, SignUpActivity1.class));
            }
        });
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SignUpActivity2.this, SignUpActivity1.class);
        startActivity(intent);
        finish();
    }
}