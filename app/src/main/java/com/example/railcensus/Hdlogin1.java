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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Hdlogin1 extends AppCompatActivity {
    EditText pf1,dob1,hdloginlable;
    Button singin1;
    public static String pfvalue,temp,pfvali,name,div,sta,actype,sec,temp1,temp2;
    public static Date dobvalue,sfin,sfout,datevali,curdate;
    public static FirebaseFirestore databasefirestorelogin = FirebaseFirestore.getInstance();
    public static DocumentReference noteRef=databasefirestorelogin.document("");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdlogin1);
        hdloginlable=(EditText) findViewById(R.id.textView5);
        hdloginlable.setText(getIntent().getStringExtra("lable"));
        curdate = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        temp1 = df.format(curdate);
        pf1=(EditText) findViewById(R.id.pfno);
        dob1=(EditText) findViewById(R.id.dob);
        dob1.addTextChangedListener(mDateEntryWatcher);
        singin1=(Button)findViewById(R.id.signin);
        singin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    private TextWatcher mDateEntryWatcher = new TextWatcher()
    {
       @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
       {
            String working = s.toString();
            boolean isValid = true;
            if(!working.equals(""))
            {
                try {

                    int aa = Integer.parseInt(working);
                    if (working.length() == 2 && before == 0)
                    {
                        if (Integer.parseInt(working) < 1 || Integer.parseInt(working) > 31)
                        {
                            isValid = false;
                        }
                        else
                        {
                            working += "/";
                            dob1.setText(working);
                            dob1.setSelection(working.length());
                        }
                    }
                    if (working.length() == 5 && before == 0)
                    {
                        if (Integer.parseInt(working.substring(3, 4)) < 1 || Integer.parseInt(working.substring(3, 4)) > 12)
                        {
                            isValid = false;
                        }
                        else
                        {
                            working += "/";
                            dob1.setText(working);
                            dob1.setSelection(working.length());
                        }
                    }
                    if (working.length() == 10 && before == 6)
                    {
                        String enteredYear = working.substring(6);
                        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                        if (Integer.parseInt(enteredYear) < currentYear)
                        {
                            isValid = false;
                        }
                    }
                    else if (working.length() != 10)
                    {
                        isValid = false;
                    }
                    if (!isValid)
                    {

                        dob1.setError("Enter a valid date: DD/MM/YYYY");
                    } else
                    {
                        dob1.setError(null);
                    }
                }catch(NumberFormatException e)
                {}
        }}
        @Override
        public void afterTextChanged(Editable s) {}
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    };

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
                else{
                    for(DocumentSnapshot doc :queryDocumentSnapshots)
                    {
                        pfvali =doc.getString("Pfno");
                        Date d=doc.getDate("DOB");
                        datevali=doc.getDate("Shift in");
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        temp2 = df.format(datevali);
                        if(pfvali.equals(pfvalue)&& d.equals(dobvalue)&&temp1.equals(temp2))
                        {
                            name=doc.getString("Name");
                            div=doc.getString("Division");
                            sta=doc.getString("Station");
                            actype=doc.getString("Accestype");
                            sfin=doc.getDate("Shift in");
                            sfout=doc.getDate("Shift out");
                            sec=doc.getString("Section");
                            Log.d("sundar", "record" + name);
                            if(actype.equals("field"))
                            {
                                Intent intent = new Intent(Hdlogin1.this,Fumessage.class);
                                startActivity(intent);
                            }
                            else if(actype.equals("Div"))
                            {
                                Intent intent = new Intent(Hdlogin1.this,Divassign.class);
                                startActivity(intent);
                            }
                        }
                    }}
            }
        });
    }
}