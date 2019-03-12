package com.example.railcensus;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.Thread.sleep;

public class Fu extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static String a,b,c,text;
    Button sundar;
    String g,ctype1, div1, sta1, t, date51;
    float z;
    int cc1 = 0, fem1 = 0, sen1 = 0, sea1 = 0, av1 = 0, occ1 = 0, conum1 = 0, male1 = 0, student1 = 0, train1 = 0,check1=0;
    DatabaseReference savetask;
    TextView carryingcapacity, female, ss, st, coachno, trainno, totalcount, male, student;
    Spinner spinner;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fu);
        FirebaseApp.initializeApp(this);
        savetask = FirebaseDatabase.getInstance().getReference("field");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        sundar = (Button) findViewById(R.id.button3);
        TextView tooltext1 = (TextView) toolbar.findViewById(R.id.textView);
        TextView tooltext2 = (TextView) toolbar.findViewById(R.id.textView2);
        TextView tooltext = (TextView) toolbar.findViewById(R.id.textView3);
        trainno = (EditText) findViewById(R.id.tno);
        coachno = (EditText) findViewById(R.id.coachno);
        carryingcapacity = (EditText) findViewById(R.id.ccy);
        totalcount = (TextView) findViewById(R.id.tt);
        st = (EditText) findViewById(R.id.sst);
        female = (EditText) findViewById(R.id.female);
        ss = (EditText) findViewById(R.id.sc);
        male = (EditText) findViewById(R.id.male);
        student = (EditText) findViewById(R.id.student_count);
        g = "MDU";
        tooltext.setText(g);
        b = tooltext.getText().toString();
        tooltext1.setText(Hdlogin1.name);
        //Hdlogin1.re1 = "";
        DateFormat df = new SimpleDateFormat("HH:mm"); //format time
        String time = df.format(Calendar.getInstance().getTime());
        DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");//foramt date
        String date = df1.format(Calendar.getInstance().getTime());
        c = date;
        a = date + " " + time;
        tooltext2.setText(a);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        spinner = findViewById(R.id.coachtype);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Div, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        st.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Log.e("JJJ","e1");
                    sundar1();
                    occ1 = fem1 + sea1 + sen1 + male1 + student1;
                    totalcount.setText(String.valueOf(occ1));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });
        sundar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setTitle("Recheck");
                alertDialogBuilder
                        .setMessage(show(a))
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                sundar1();
                                    sundar2();
                                    if(check1!=1)
                                    {
                                    trainno.setText("");
                                    coachno.setText("");
                                    carryingcapacity.setText("");
                                    male.setText("");
                                    female.setText("");
                                    ss.setText("");
                                    student.setText("");
                                    st.setText("");
                                    totalcount.setText("");
                                    return;}


                        }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                Toast.makeText(Fu.this, "Loggedout", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.goback:
                Toast.makeText(Fu.this, "Return back to counting page", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    private void sundar1()
    {
        ctype1 = text;
        div1 = b;
        sta1 = b;
        date51 = c;
          if ((trainno.getText().toString().equals("")) || (trainno.getText().toString().length() != 5)) {
                Toast.makeText(this, "please fill this train no", Toast.LENGTH_LONG).show();
                trainno.setError("Please Enter Train No:");
                check1 = 1;
                return;
            } else {
                t = trainno.getText().toString();
                if (t.equals("")) {
                    Toast.makeText(this, "Artist added", Toast.LENGTH_LONG).show();
                } else {
                    train1 = Integer.parseInt(t);
                    Log.e("dsd9s", "trainno" + train1);
                }
            }
            if (coachno.getText().toString().equals("")) {
                Toast.makeText(this, "please fill coach number", Toast.LENGTH_LONG).show();
                coachno.requestFocus();
                check1 = 1;
            } else {
                t = coachno.getText().toString();
                if (t.equals("")) {
                    Toast.makeText(this, "Artist added", Toast.LENGTH_LONG).show();
                } else {
                    conum1 = Integer.parseInt(t);
                    Log.e("dsd9s", "coach no" + conum1);
                }
            }
            if (ctype1.equals("Coach Type")) {
                Toast.makeText(this, "please fill coach type", Toast.LENGTH_LONG).show();
                spinner.requestFocus();
                check1 = 1;
            }
            if (carryingcapacity.getText().toString().equals("")) {
                Toast.makeText(this, "please fill carrying capacity", Toast.LENGTH_LONG).show();
                carryingcapacity.requestFocus();
                check1 = 1;
            } else {
                t = carryingcapacity.getText().toString();
                if (t.equals("")) {
                    Toast.makeText(this, "Artist added", Toast.LENGTH_LONG).show();

                } else {
                    cc1 = Integer.parseInt(t);
                    Log.e("dsd9s", "cc" + cc1);
                }
            }
            if (female.getText().toString().equals("")) {
                Toast.makeText(this, "please fill female count", Toast.LENGTH_LONG).show();
                female.requestFocus();
                check1 = 1;
            } else {
                t = female.getText().toString();
                if (t.equals("")) {
                    Toast.makeText(this, "Artist added", Toast.LENGTH_LONG).show();
                } else {
                    fem1 = Integer.parseInt(t);
                    Log.e("dsd9s", "femalr" + fem1);
                }
            }
            if (ss.getText().toString().equals("")) {
                Toast.makeText(this, "please fill Senior count", Toast.LENGTH_LONG).show();
                ss.requestFocus();
                check1 = 1;
            } else {
                t = ss.getText().toString();
                if (t.equals("")) {
                    Toast.makeText(this, "Artist added", Toast.LENGTH_LONG).show();
                } else {
                    sen1 = Integer.parseInt(t);
                    Log.e("dsd9s", "senior" + sen1);
                }
            }
            if (st.getText().toString().equals("")) {
                Toast.makeText(this, "please fill season ticket count", Toast.LENGTH_LONG).show();
                ss.requestFocus();
                check1 = 1;
            } else {
                t = st.getText().toString();
                if (t.equals("")) {
                    Toast.makeText(this, "Artist added", Toast.LENGTH_LONG).show();
                } else {
                    sea1 = Integer.parseInt(t);
                    Log.e("dsd9s", "season" + sea1);
                }
            }
            if (student.getText().toString().equals("")) {
                Toast.makeText(this, "please fill Student count", Toast.LENGTH_LONG).show();
                student.requestFocus();
                check1 = 1;
            } else {
                t = student.getText().toString();
                if (t.equals("")) {
                    Toast.makeText(this, "Artist added", Toast.LENGTH_LONG).show();
                } else {
                    student1 = Integer.parseInt(t);
                    Log.e("dsd9s", "student" + student1);
                }
            }
            if (male.getText().toString().equals("")) {
                Toast.makeText(this, "please fill Male count", Toast.LENGTH_LONG).show();
                male.requestFocus();
                check1 = 1;
            } else {
                t = male.getText().toString();
                if (t.equals("")) {
                    Toast.makeText(this, "Artist added", Toast.LENGTH_LONG).show();
                } else {
                    male1 = Integer.parseInt(t);
                    Log.e("dsd9s", "male" + male1);
                }
            }

    }

    public String show(String a) {
        a = "Train No :" + trainno.getText().toString()+ " " +"Coach NO :" + coachno.getText().toString() + " " + "Coach Type : " + text + " " + "Carrying Capacity :" + carryingcapacity.getText().toString() + " " +"Total :"+occ1;
        z = (float) occ1 / (float)cc1;
        Log.e("dsd9s", "male" + z);
        av1 = (int) (z * 100);
        Log.e("dsd9s", "male" + av1);
        return (a);
    }
    public void sundar2()
    {
        if(check1!=1)
       {
        String id = savetask.push().getKey();
        Adddata1 artist = new Adddata1(date51, train1, div1, sta1, ctype1, conum1, cc1, male1, fem1, sen1, student1, sea1, occ1, av1);
        savetask.child(id).setValue(artist);
        Toast.makeText(this, "Artist added", Toast.LENGTH_LONG).show();
       }
    }
}





