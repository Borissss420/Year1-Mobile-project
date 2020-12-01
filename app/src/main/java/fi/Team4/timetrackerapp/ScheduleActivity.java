package fi.Team4.timetrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScheduleActivity extends AppCompatActivity {

    String dateID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        CalendarView calendar = findViewById(R.id.calendarView);
        TextView selectedDate  = findViewById(R.id.tvSelectedDate);

        calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String date = dayOfMonth + "/" + (month + 1) + "/" + year ;
            dateID = String.valueOf(dayOfMonth + "-" + (month+1) + "-" + year);
            selectedDate.setText(date);
        });

        Button AddEventButton = findViewById(R.id.btnAddEvent);
        AddEventButton.setOnClickListener(v -> {
            Intent i = new Intent(ScheduleActivity.this, AddAndViewActivity.class);
            i.putExtra("Date", dateID);
            startActivity(i);
        });
    }
}