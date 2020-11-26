package fi.Team4.timetrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView time = findViewById(R.id.tvCurrentTime);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateAndTime = new SimpleDateFormat("'Date:' EEE, dd.MMM.yyyy \n'Zone:' z");
        String currentDateAndTime = dateAndTime.format(new Date());
        time.setText(currentDateAndTime);

        Button scheduleButton = findViewById(R.id.btnSchedule);
        scheduleButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(MainActivity.this, ScheduleActivity.class);
            startActivity(intent1);
        });

        Button statisticsButton = findViewById(R.id.btnStatistics);
        statisticsButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, StatisticActivity.class));
        });
    }
}