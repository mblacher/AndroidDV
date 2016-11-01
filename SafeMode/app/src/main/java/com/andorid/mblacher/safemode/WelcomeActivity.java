package com.andorid.mblacher.safemode;

/**
 * Created by mblacher on 14-Oct-16.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    public Button btnLogin;
    public Button btnRegister1;
    public Button btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister1 = (Button) findViewById(R.id.btnRegister1);
        btnAbout = (Button) findViewById(R.id.btnAbout);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(WelcomeActivity.this, RegActivity.class);
                startActivity(intent);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(WelcomeActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }
}



