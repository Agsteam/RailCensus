package com.example.railcensus;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

        Button imgButton;

        Button btnassignlogin,btndivlogin;
        String loginlable;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_welcome);
            imgButton=(Button)findViewById(R.id.hq);
            imgButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"Page under construction",Toast.LENGTH_LONG).show();
                }


            });
            btndivlogin=(Button)findViewById(R.id.button2);
            btndivlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginlable="Divisional Admin";
                    openHdLogin();
                }
            });
            btnassignlogin = (Button) findViewById(R.id.btn3);
            btnassignlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginlable="Census Officer";
                    openHdLogin();
                }
            });
        }
       public void openHdLogin()
    {
        Intent intent = new Intent(Welcome.this, Hdlogin1.class);
        intent.putExtra("lable",loginlable);
        startActivity(intent);
    }
}