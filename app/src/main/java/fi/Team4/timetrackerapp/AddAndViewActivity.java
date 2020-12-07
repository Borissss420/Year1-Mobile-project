package fi.Team4.timetrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AddAndViewActivity extends AppCompatActivity {

    private static final String TAG = "Testing";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_view);

        TextView date = findViewById(R.id.tvDate);
        EditText eventName = findViewById(R.id.et_EventName);
        TextView startTime = findViewById(R.id.tvStartingTime);
        TextView endTime = findViewById(R.id.tvEndingTime);
        Spinner spinner = findViewById(R.id.spinnerEventType);
        Button btnView = findViewById(R.id.btnView);
        Button btnAdd = findViewById(R.id.btnAdd);
        ListView eventView = findViewById(R.id.eventView);

        String getDate = getIntent().getStringExtra("Date");
        date.setText(String.valueOf(getDate));

        loadList();

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog startTimeDialog = new TimePickerDialog(AddAndViewActivity.this,
                        (timePicker, hours, minutes) -> startTime.setText(String.format("%02d:%02d", hours, minutes)), 0, 0, false);
                startTimeDialog.show();
            }
        });
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog endTimeDialog = new TimePickerDialog(AddAndViewActivity.this,
                        (timePicker, hours, minutes) -> endTime.setText(String.format("%02d:%02d", hours, minutes)), 0, 0, false);
                endTimeDialog.show();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.EventTypeArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

//        List<Event> allEvents = Events.getInstance().events;
        ArrayList<Event> dailyEvents = new ArrayList<>();

        ArrayAdapter<Event> eventArrayAdapter = new ArrayAdapter<Event>(
                AddAndViewActivity.this,
                android.R.layout.simple_list_item_1,
                dailyEvents);
//        if (eventArrayAdapter.getCount() > 5) {
//            View item = eventArrayAdapter.getView(0, null, eventView);
//            item.measure(0, 0);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (5.5 * item.getMeasuredHeight()));
//            eventView.setLayoutParams(params);
//        }

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddAndViewActivity.this, "Hold item to delete", Toast.LENGTH_SHORT).show();

                loadList();
                dailyEvents.clear();

                for (int i = 0; i < Events.getInstance().events.size(); i++) {
                    if (Events.getInstance().events.get(i).getDate().equals(String.valueOf(getDate))) {
                        dailyEvents.add(Events.getInstance().events.get(i));
                    }
                }
                if (eventArrayAdapter.getCount() > 5) {
                    View item = eventArrayAdapter.getView(0, null, eventView);
                    item.measure(0, 0);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (5.5 * item.getMeasuredHeight()));
                    eventView.setLayoutParams(params);
                }

                System.out.println(Events.getInstance().events);
                System.out.println(dailyEvents);
                eventView.setAdapter(eventArrayAdapter);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startTime.getText().toString().equals("Insert starting time")) {
                    Toast.makeText(AddAndViewActivity.this, "Invalid starting time", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (endTime.getText().toString().equals("Insert ending time")) {
                    Toast.makeText(AddAndViewActivity.this, "Invalid ending time", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(AddAndViewActivity.this, "Event added", Toast.LENGTH_SHORT).show();
                    Events.getInstance().addEvent(eventName.getText().toString(), spinner.getSelectedItem().toString(), getDate,
                            startTime.getText().toString(), endTime.getText().toString());
                    eventName.setText("");
                    startTime.setText("Insert starting time");
                    endTime.setText("Insert ending time");
                }
                saveList();
            }
        });
        eventView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                Events.getInstance().events.remove(Events.getInstance().getEventIndex(eventArrayAdapter.getItem(i)));
                eventArrayAdapter.remove(eventArrayAdapter.getItem(i));
                eventArrayAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }
    private void saveList() {
        sharedPreferences = getSharedPreferences("AllEvents", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(Events.getInstance().events);
        editor.putString("Key", json);
        editor.apply();
    }
    private void loadList() {
        sharedPreferences = getSharedPreferences("AllEvents", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Key", null);
        Type type = new TypeToken<ArrayList<Event>>() {}.getType();
        Events.getInstance().events = gson.fromJson(json, type);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "AddAndViewActivity started");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "AddAndViewActivity paused");
        saveList();
    }
}