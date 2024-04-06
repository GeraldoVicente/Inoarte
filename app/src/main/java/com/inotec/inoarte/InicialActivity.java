package com.inotec.inoarte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;

public class InicialActivity extends AppCompatActivity {
    CalendarView calendarView;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        calendarView=findViewById(R.id.calendarView);
        calendar = Calendar.getInstance();

        setDate(3,4,2024);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int ano, int mes, int dia) {
                Toast.makeText(InicialActivity.this, dia + "/" + mes+ "/" + ano, Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void setDate(int dia, int mes,int ano){
        calendar.set(Calendar.YEAR, ano);
        calendar.set(Calendar.MONTH, mes-1);
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        long milli = calendar. getTimeInMillis();
        calendarView.setDate(milli);
    }
}