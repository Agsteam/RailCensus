package com.example.railcensus;

import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.widget.TextView;

        import java.util.Date;

public class Opening extends AppCompatActivity {
    TextView ed;
    String name2,div2,sta2,actype2,sec2,sinin2,sinout2,lable2;
    Date sinin1,sinout1;

    private static int SPLASH_TIME = 500; //This is 4 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        ed=(TextView) findViewById(R.id.textView9);
        name2= getIntent().getStringExtra("name1");
        div2=getIntent().getStringExtra("div1");
        sta2=getIntent().getStringExtra("Sta1");
        actype2=getIntent().getStringExtra("acsty");
        sec2=getIntent().getStringExtra("sec1");
        sinin2=getIntent().getStringExtra("sinin");
        sinout2=getIntent().getStringExtra("sinout");
        lable2=getIntent().getStringExtra("lable");
        Log.e("sun:",""+sinin2+sinout2+div2+sta2+actype2+sec2);
        ed.setText("Welcome "+name2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                if(actype2.equals("field"))
                {
                    Intent mySuperIntent = new Intent(Opening.this, Fu.class);
                    startActivity(mySuperIntent);
                    finish();
                }
                else if(actype2.equals("Div"))   {
                    Intent mySuperIntent = new Intent(Opening.this, Divassign.class);
                    startActivity(mySuperIntent);
                    finish();
                }
            }

        }, SPLASH_TIME);

    }

}

