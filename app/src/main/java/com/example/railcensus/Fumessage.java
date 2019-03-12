package com.example.railcensus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

    public class Fumessage extends AppCompatActivity
    {
        TextView fudiv,funame,fusec,fusta,fudate,fusfin,fusfout;
        Date fucurrdate;
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fumessage);
            fudiv=(TextView)findViewById(R.id.textView2);
            funame=(TextView)findViewById(R.id.get_name);
            fusec=(TextView)findViewById(R.id.sec);
            fusta=(TextView)findViewById(R.id.station);
            fudate=(TextView)findViewById(R.id.date1);
            fusfin=(TextView)findViewById(R.id.shiftin);
            fusfout=(TextView)findViewById(R.id.shiftout);
            funame.setText(Hdlogin1.name);
            fudiv.setText("Division : "+Hdlogin1.div);
            fusec.setText(Hdlogin1.sec);
            fusta.setText(Hdlogin1.sta);
            Date fucurrdate = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = df.format(fucurrdate);
            fudate.setText(formattedDate);
            SimpleDateFormat df1 = new SimpleDateFormat("HH:mm");
            String formattedDate1 = df1.format(Hdlogin1.sfin);
            fusfin.setText(formattedDate1);
            SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");
            String formattedDate2 = df1.format(Hdlogin1.sfout);
            fusfout.setText(formattedDate2);

        }
    }
