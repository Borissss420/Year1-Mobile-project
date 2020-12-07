package fi.Team4.timetrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StatisticActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    private int totalEventCount;
    private int IdleCount, StudyingCount, WorkingCount, DiningCount, TidyingCount, BathingCount, HangOutCount, GamingCount, ReadingCount, TVCount, OthersCount;
    String[] Types = {"Idle", "Studying", "Working", "Dining", "Tidying", "Bathing", "Hanging out", "Gaming", "Reading", "Watching TV", "Others"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        RadioButton rbtnDay = findViewById(R.id.rbtnDay);
        RadioButton rbtnWeek = findViewById(R.id.rbtnWeek);
//        RadioButton rbtnMonth = findViewById(R.id.rbtnMonth);
        TextView tvStatement = findViewById(R.id.tvStatement);
        TextView tvStatement2 = findViewById(R.id.tvStatement2);
        AnyChartView pieChart = findViewById(R.id.pieChart);
        Pie pie = AnyChart.pie();

        loadList();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar day1 = Calendar.getInstance();
        day1.add(Calendar.DATE, -1);
        Calendar day2 = Calendar.getInstance();
        day2.add(Calendar.DATE, -2);
        Calendar day3 = Calendar.getInstance();
        day3.add(Calendar.DATE, -3);
        Calendar day4 = Calendar.getInstance();
        day4.add(Calendar.DATE, -4);
        Calendar day5 = Calendar.getInstance();
        day5.add(Calendar.DATE, -5);
        Calendar day6 = Calendar.getInstance();
        day6.add(Calendar.DATE, -6);
        Calendar day7 = Calendar.getInstance();
        day7.add(Calendar.DATE, -7);

        String todayDate = dateFormat.format(new Date());
        String dayBefore1 = dateFormat.format(day1.getTime());
        String dayBefore2 = dateFormat.format(day2.getTime());
        String dayBefore3 = dateFormat.format(day3.getTime());
        String dayBefore4 = dateFormat.format(day4.getTime());
        String dayBefore5 = dateFormat.format(day5.getTime());
        String dayBefore6 = dateFormat.format(day6.getTime());
        String dayBefore7 = dateFormat.format(day7.getTime());
        System.out.println(todayDate);
        System.out.println(dayBefore1);
        System.out.println(dayBefore2);
        System.out.println(dayBefore3);
        System.out.println(dayBefore4);
        System.out.println(dayBefore5);
        System.out.println(dayBefore6);
        System.out.println(dayBefore7);

        ArrayList<Event> processList = new ArrayList<>();
        List<DataEntry> pieData1 = new ArrayList<>();
        List<DataEntry> pieData2 = new ArrayList<>();

        rbtnDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processList.clear();
                pieData1.clear();
                totalEventCount = 0;
                IdleCount = 0;
                StudyingCount = 0;
                WorkingCount = 0;
                DiningCount = 0;
                TidyingCount = 0;
                BathingCount = 0;
                HangOutCount = 0;
                GamingCount = 0;
                ReadingCount = 0;
                TVCount = 0;
                OthersCount = 0;

                for (int i = 0; i < Events.getInstance().events.size(); i++) {
                    if (Events.getInstance().events.get(i).getDate().equals(dayBefore1)) {
                        processList.add(Events.getInstance().events.get(i));
                        totalEventCount++;
                    }
                }
                for (int i = 0; i < processList.size(); i++) {
                    if (processList.get(i).getEventType().equals("Idle")) {
                        IdleCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Studying")) {
                        StudyingCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Working")) {
                        WorkingCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Dining")) {
                        DiningCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Tidying")) {
                        TidyingCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Bathing")) {
                        BathingCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Hanging out")) {
                        HangOutCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Gaming")) {
                        GamingCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Reading")) {
                        ReadingCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Watching TV")) {
                        TVCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Others")) {
                        OthersCount++;
                    }
                }
                tvStatement.setText("Total number of events in yesterday is " + totalEventCount);
                tvStatement2.setText(
                        "Idling:           " + IdleCount +
                        "\nStudying:      " + StudyingCount +
                        "\nWorking:       " + WorkingCount +
                        "\nDining:           " + DiningCount +
                        "\nTidying:         " + TidyingCount +
                        "\nBathing:         " + BathingCount +
                        "\nHanging out: " + HangOutCount +
                        "\nGaming:         " + GamingCount +
                        "\nReading:        " + ReadingCount +
                        "\nWatching TV: " + TVCount +
                        "\nOthers:           " + OthersCount);
                int[] Counts = {IdleCount, StudyingCount, WorkingCount, DiningCount, TidyingCount, BathingCount, HangOutCount, GamingCount, ReadingCount, TVCount, OthersCount};

                for (int i = 0; i < Types.length; i++) {
                    pieData1.add(new ValueDataEntry(Types[i], Counts[i]));
                }
                if (rbtnDay.isChecked()) {
                    pie.data(pieData1);
                }
//                pieChart.setChart(pie);
//                pie.data(pieData1);
//                pieChart.setChart(pie);
            }
        });
        rbtnWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processList.clear();
                pieData2.clear();
                totalEventCount = 0;
                IdleCount = 0;
                StudyingCount = 0;
                WorkingCount = 0;
                DiningCount = 0;
                TidyingCount = 0;
                BathingCount = 0;
                HangOutCount = 0;
                GamingCount = 0;
                ReadingCount = 0;
                TVCount = 0;
                OthersCount = 0;

                for (int i = 0; i < Events.getInstance().events.size(); i++) {
                    if (Events.getInstance().events.get(i).getDate().equals(dayBefore1)) {
                        processList.add(Events.getInstance().events.get(i));
                        totalEventCount++;
                    }
                    else if (Events.getInstance().events.get(i).getDate().equals(dayBefore2)) {
                        processList.add(Events.getInstance().events.get(i));
                        totalEventCount++;
                    }
                    else if (Events.getInstance().events.get(i).getDate().equals(dayBefore3)) {
                        processList.add(Events.getInstance().events.get(i));
                        totalEventCount++;
                    }
                    else if (Events.getInstance().events.get(i).getDate().equals(dayBefore4)) {
                        processList.add(Events.getInstance().events.get(i));
                        totalEventCount++;
                    }
                    else if (Events.getInstance().events.get(i).getDate().equals(dayBefore5)) {
                        processList.add(Events.getInstance().events.get(i));
                        totalEventCount++;
                    }
                    else if (Events.getInstance().events.get(i).getDate().equals(dayBefore6)) {
                        processList.add(Events.getInstance().events.get(i));
                        totalEventCount++;
                    }
                    else if (Events.getInstance().events.get(i).getDate().equals(dayBefore7)) {
                        processList.add(Events.getInstance().events.get(i));
                        totalEventCount++;
                    }
                }
                for (int i = 0; i < processList.size(); i++) {
                    if (processList.get(i).getEventType().equals("Idle")) {
                        IdleCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Studying")) {
                        StudyingCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Working")) {
                        WorkingCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Dining")) {
                        DiningCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Tidying")) {
                        TidyingCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Bathing")) {
                        BathingCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Hanging out")) {
                        HangOutCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Gaming")) {
                        GamingCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Reading")) {
                        ReadingCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Watching TV")) {
                        TVCount++;
                    }
                    else if (processList.get(i).getEventType().equals("Others")) {
                        OthersCount++;
                    }
                }
                tvStatement.setText("Total number of events in yesterday is " + totalEventCount);
                tvStatement2.setText(
                        "Idling:           " + IdleCount +
                                "\nStudying:      " + StudyingCount +
                                "\nWorking:       " + WorkingCount +
                                "\nDining:           " + DiningCount +
                                "\nTidying:         " + TidyingCount +
                                "\nBathing:         " + BathingCount +
                                "\nHanging out: " + HangOutCount +
                                "\nGaming:         " + GamingCount +
                                "\nReading:        " + ReadingCount +
                                "\nWatching TV: " + TVCount +
                                "\nOthers:           " + OthersCount);

                int[] Counts = {IdleCount, StudyingCount, WorkingCount, DiningCount, TidyingCount, BathingCount, HangOutCount, GamingCount, ReadingCount, TVCount, OthersCount};

                for (int i = 0; i < Types.length; i++) {
                    pieData2.add(new ValueDataEntry(Types[i], Counts[i]));
                }
//                pie.data(pieData2);
//                pieChart.setChart(pie);
                if (rbtnWeek.isChecked()) {
                    pie.data(pieData2);
                }
//                pieChart.setChart(pie);
            }
        });
        pieChart.setChart(pie);
    }
    private void loadList() {
        sharedPreferences = getSharedPreferences("AllEvents", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Key", null);
        Type type = new TypeToken<ArrayList<Event>>() {}.getType();
        Events.getInstance().events = gson.fromJson(json, type);
    }
//    public void setUpPieChart() {
//        Pie pie = AnyChart.pie();
//        List<> pieData = new ArrayList<>();
//        for (int i = 0; i < Types.length; i++) {
//            pieData.add(new ValueDataEntry(Types[i], Counts[i]));
//        }
//        pie.data(pieData);
//        pieChart.setChart(pie);
//    }
}