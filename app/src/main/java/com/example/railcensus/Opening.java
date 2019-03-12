package com.example.railcensus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
public class Opening extends AppCompatActivity {
    TextView ed;
    public static String name1;
    private static int SPLASH_TIME = 2000; //This is 4 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        ed=(TextView) findViewById(R.id.textView9);
        ed.setText("Welcome "+Hdlogin1.name);
                        new Handler().postDelayed(new Runnable() {

            @Override

            public void run() {
                Intent mySuperIntent = new Intent(Opening.this, Fu.class);
                startActivity(mySuperIntent);
                finish();
            }

        }, SPLASH_TIME);

    }

}

