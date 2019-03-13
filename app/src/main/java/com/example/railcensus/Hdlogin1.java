package com.example.railcensus;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Hdlogin1 extends AppCompatActivity
{
    EditText pf1,dob1;
    TextView hdloginlable;
    Button singin1;
    String a;
    public static String pfvalue,temp,pfvali,name,div,sta,actype,sec,temp1,temp2;
    public static Date dobvalue,sfin,sfout,datevali,curdate;
    public static FirebaseFirestore databasefirestorelogin = FirebaseFirestore.getInstance();
    public static DocumentReference noteRef=databasefirestorelogin.document("");
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdlogin1);
        hdloginlable=(TextView) findViewById(R.id.textView5);
        hdloginlable.setText(getIntent().getStringExtra("lable"));
        curdate = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        temp1 = df.format(curdate);
        pf1=(EditText) findViewById(R.id.pfno);
        dob1=(EditText) findViewById(R.id.dob);
        singin1=(Button)findViewById(R.id.signin);
        singin1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pfvalue=pf1.getText().toString().trim();
                temp=dob1.getText().toString().trim();
                if(temp.equals("")||pfvalue.equals(""))
                {
                    Toast.makeText(Hdlogin1.this,"please check user name and password",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
                    try
                    {
                        dobvalue=format.parse(temp);
                        checklogin();
                    }catch (ParseException e)
                    {e.printStackTrace();}
                }
            }
        });
    }
    private void checklogin()
    {
        databasefirestorelogin.collection("Login").addSnapshotListener(new EventListener<QuerySnapshot>()
        {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots,  FirebaseFirestoreException e)
            {
                if(e!=null)
                {
                    Log.d("sun","error"+e.getMessage());
                }
                else
                {
                    for(DocumentSnapshot doc :queryDocumentSnapshots)
                    {
                        pfvali =doc.getString("Pfno");
                        Date d=doc.getDate("DOB");
                        datevali=doc.getDate("Shift in");
                        actype=doc.getString("Accestype");
                        if(hdloginlable.getText().toString().equals("Divisional Admin"))
                        {a="Div"; }
                        else if(hdloginlable.getText().toString().equals("Census Officer"))
                        {a="field";}
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        temp2 = df.format(datevali);
                        if(pfvali.equals(pfvalue)&& d.equals(dobvalue)&&a.equals(actype))
                        {


                            name=doc.getString("Name");
                            div=doc.getString("Division");
                            sta=doc.getString("Station");
                            sfin=doc.getDate("Shift in");
                            sfout=doc.getDate("Shift out");
                            sec=doc.getString("Section");
                            Intent intent = new Intent(Hdlogin1.this,Opening.class);
                            intent.putExtra("name1",name);
                            intent.putExtra("div1",div);
                            intent.putExtra("Sta1",sta);
                            intent.putExtra("acsty",actype);
                            String pattern = "dd/MM/yyyy HH:mm:ss";
                            DateFormat df1 = new SimpleDateFormat(pattern);
                            String todayAsString = df1.format(sfin);
                            intent.putExtra("sinin",sfin);
                            intent.putExtra("sinout",sfout);
                            intent.putExtra("sec1",sec);
                            intent.putExtra("lable",hdloginlable.getText().toString());
                            startActivity(intent);
                        }
                    }
                    if(name.equals(""))
                    {
                        Toast.makeText(Hdlogin1.this,"please check user name and password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}