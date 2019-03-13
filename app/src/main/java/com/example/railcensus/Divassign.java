package com.example.railcensus;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.railcensus.Hdlogin1.databasefirestorelogin;
import static java.lang.String.valueOf;

public class Divassign extends AppCompatActivity
{      private long mLastClickTime = 0;
    TextView assigndivname, assigndate;
    DatePickerDialog datepick;
    Spinner divec,divsta;
    String temp1;
    TextView et_from_time,et_to_time,submitnew;
    TimePickerDialog timePickerDialog;
    String get_section="MAS-PTMS";
    int year, month, dayOfMonth,loding=0;
    Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divassign);
        assigndivname = (TextView) findViewById(R.id.div_nam);
        assigndate = (EditText) findViewById(R.id.editText);
        divec = (Spinner) findViewById(R.id.et_station);
        divsta = (Spinner) findViewById(R.id.et_section);
        assigndivname.setText(Hdlogin1.sec);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth)
            {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
                assigndate.setText(sdf.format(calendar.getTime()));
            }
        };



        assigndate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                new DatePickerDialog(Divassign.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        List<String> categories2 = new ArrayList<String>();
        if (Hdlogin1.sec.equals("chennai"))
        {
            categories2.add("MAS-PTMS");
            categories2.add("EGT-AJJ");
            categories2.add("IPT-PUDI");
            categories2.add("MLPM-JTJ");
            categories2.add("MSB-CGL");
            categories2.add("TKO-RDY");
            categories2.add("TRX-MYP");
            categories2.add("MPKT-VLCY");
            categories2.add("RPM-GPD");
            categories2.add("ELR-ODUR");
        }
        else if (Hdlogin1.sec.equals("VELCHERY TO AVADI"))
        {
            categories2.add("Select");
            categories2.add("Basin Bridge");
            categories2.add("Villivakkam");
            categories2.add("Korattur");
            categories2.add("Ambattur");
            categories2.add("Avadi");
        }
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        divec.setAdapter(dataAdapter2);
        divec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                 get_section = valueOf(divec.getSelectedItem());
                  Toast.makeText(parentView.getContext(),
                        "OnItemSelectedListener : " + get_section,
                        Toast.LENGTH_SHORT).show();
                  station();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
            }
        });
    }
    public  void station()
    {
        List<String> categories3 = new ArrayList<String>();
        if(get_section.equals("MAS-PTMS"))
        {
            categories3.add("Chennai Central");
            categories3.add("Basin Bridge Jn.");
            categories3.add("Vyasarpadi Jeeva");
            categories3.add("Perambur");
            categories3.add("Perambur Carriage Works");
            categories3.add("Villivakkam");
            categories3.add("Korattur");
            categories3.add("Pattaravakkam");
            categories3.add("Ambattur");
            categories3.add("Tirumullaivayil");
            categories3.add("Annanur");
            categories3.add("Avadi");
            categories3.add("Hindu College");
            categories3.add("Pattabiram");
            categories3.add("PATTABIRAM MILITARY SIDING");
            categories3.add("Pattabiram 'E' depot");
            categories3.add("NEMILICHERRY HALT");
            categories3.add("Tiruninravur");
            categories3.add("Veppampattu");
            categories3.add("Sevvapet Road");
            categories3.add("Putlur Halt");
            categories3.add("Tiruvallur");
        }
        else if (get_section.equals("EGT-AJJ"))
        {
            categories3.add("Egattur");
            categories3.add("Kadambattur");
            categories3.add("Senji Panambakkam");
            categories3.add("Manavur");
            categories3.add("Tiruvalangadu");
            categories3.add("Mosur");
            categories3.add("PULIYAMANGALAM");
            categories3.add("Arakkonam JN");
        }
        else if(get_section.equals("IPT-PUDI"))
        {
            categories3.add("ICHCHIPUTTUR HALT");
            categories3.add("Tiruttani");
            categories3.add("Ponpadi");
            categories3.add("Venkatanarasimharajuvaripeta");
            categories3.add("Nagari");
            categories3.add("Ekambarakuppam");
            categories3.add("Vepagunta");
            categories3.add("Puttur");
            categories3.add("Taduku");
            categories3.add("Pudi");
        }
        else if(get_section.equals("MLPM-JTJ")) {
            categories3.add("Chitteri");
            categories3.add("Anavardikhanpettai");
            categories3.add("Mahendravadi");
            categories3.add("Sholinghur");
            categories3.add("Thalangai");
            categories3.add("Marudalam");
            categories3.add("Walajah Road");
            categories3.add("Mukundarayapuram");
            categories3.add("Tiruvalam");
            categories3.add("Sevur");
            categories3.add("Katpadi Jn.");
            categories3.add("Latteri");
            categories3.add("Virinchipuram");
            categories3.add("Kavanur");
            categories3.add("Gudiyattam");
            categories3.add("Melattur");
            categories3.add("Valathoor");
            categories3.add("Melpatti");
            categories3.add("Pachchakuppam");
            categories3.add("Ambur");
            categories3.add("Vinnamangalam");
            categories3.add("Vaniyambadi");
            categories3.add("Kettandapatti");
            categories3.add("Jolarpettai Jn");
        }
        else if(get_section.equals("MSB-CGL"))
        {
            categories3.add("Chennai Beach Jn");
            categories3.add("Chennai Fort");
            categories3.add("Chennai Park");
            categories3.add("Chennai Egmore");
            categories3.add("Chennai Chetpat");
            categories3.add("Nungambakkam");
            categories3.add("Kodambakkam");
            categories3.add("Mambalam");
            categories3.add("Saidapet");
            categories3.add("Guindy");
            categories3.add("St. Thomas Mount");
            categories3.add("Palavantangal");
            categories3.add("Minambakkam");
            categories3.add("Tirusulam");
            categories3.add("Pallavaram");
            categories3.add("Chromepet");
            categories3.add("Tambaram Sanatorium");
            categories3.add("Tambaram");
            categories3.add("Perungalattur");
            categories3.add("Vandalur");
            categories3.add("Urappakkam");
            categories3.add("Guduvancheri");
            categories3.add("Potheri");
            categories3.add("Kattangulatur");
            categories3.add("Maraimalai Nagar Kamarajar");
            categories3.add("Singaperumalkoil");
            categories3.add("Paranur");
            categories3.add("Chengalpattu Jn");
        }
        else if(get_section.equals("TKO-RDY")) {
            categories3.add("Takkolam");
            categories3.add("Tirumalpur");
            categories3.add("Kanchipuram");
            categories3.add("Kanchipuram East");
            categories3.add("Nathapettai");
            categories3.add("Walajabad");
            categories3.add("Palayasivaram");
            categories3.add("Palur");
            categories3.add("Villiyambakkam");
            categories3.add("Reddipalayam");
        }
        else if(get_section.equals("TRX-MYP")) {
            categories3.add("Tirumani");
            categories3.add("Ottivakkam");
            categories3.add("Padalam");
            categories3.add("Karunguzhi");
            categories3.add("Madurantakam");
            categories3.add("Pakkam");
            categories3.add("Melmaruvathur");
            categories3.add("Acharapakkam");
            categories3.add("Tozhuppedu");
            categories3.add("Karasangal");
            categories3.add("Olakur");
            categories3.add("Panchalam");
            categories3.add("Tindivanam");
            categories3.add("Mailam");
            categories3.add("Nedimolyanur");
            categories3.add("Perani");
            categories3.add("Vikravandi");
            categories3.add("Mundiyampakkam");
        }
        else if(get_section.equals("MPKT-JTJ")) {
            categories3.add("CHENNAI PARK TOWN");
            categories3.add("Chintadaripet");
            categories3.add("Chepauk");
            categories3.add("Tiruvallikeni");
            categories3.add("Light House");
            categories3.add("Mundakakanni Amman Koil");
            categories3.add("Thirumayilai");
            categories3.add("Mandaveli");
            categories3.add("Greenways Road");
            categories3.add("Kotturpuram");
            categories3.add("Kasturba Nagar");
            categories3.add("Indra Nagar");
            categories3.add("Tiruvanmiyur");
            categories3.add("TARAMANI");
            categories3.add("PERUNGUDI");
            categories3.add("VELACHERY");
        }
        else if(get_section.equals("RPM-GPD")) {
            categories3.add("Royapuram");
            categories3.add("Washermanpet");
            categories3.add("Korukkupet");
            categories3.add("Tondiarpet");
            categories3.add("VOC Nagar");
            categories3.add("Tiruvottiyur");
            categories3.add("Wimco Nagar");
            categories3.add("Kathivakkam");
            categories3.add("Ennore");
            categories3.add("Attipattu Pudu Nagar.H");
            categories3.add("Attippattu");
            categories3.add("Nandiyampakkam");
            categories3.add("Minjur");
            categories3.add("Anuppambattu");
            categories3.add("Ponneri");
            categories3.add("Kavaraippettai");
            categories3.add("Gummidipundi");
        }
        else if(get_section.equals("ELR-ODUR"))
        {
            categories3.add("Elavur");
            categories3.add("Arambakam");
            categories3.add("Tada");
            categories3.add("Akkampet");
            categories3.add("Sullurupeta");
            categories3.add("Polireddipalem");
            categories3.add("Doravarichattram");
            categories3.add("Nayudupeta");
            categories3.add("Pedapariya");
            categories3.add("Odur");
        }
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories3);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        divsta.setAdapter(dataAdapter3);
        divsta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String get_section1 = valueOf(divsta.getSelectedItem());
//                Toast.makeText(getApplicationContext(),getrenewal,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        et_from_time = (TextView) findViewById(R.id.et_from_time);
        et_to_time = (TextView) findViewById(R.id.et_to_time);



        et_from_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                // Create a new instance of TimePickerDialog
                timePickerDialog = new TimePickerDialog(Divassign.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        et_from_time.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
                et_from_time.setFocusable(false);
            }
        });


        et_to_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                // Create a new instance of TimePickerDialog
                timePickerDialog = new TimePickerDialog(Divassign.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        et_to_time.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
                et_from_time.setFocusable(false);
            }
        });



    }
}