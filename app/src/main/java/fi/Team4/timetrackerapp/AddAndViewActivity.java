package fi.Team4.timetrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddAndViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_view);

        TextView date = findViewById(R.id.tvDate);
        EditText eventName = findViewById(R.id.et_EventName);
        EditText startTime = findViewById(R.id.et_StartingTime);
        EditText endTime = findViewById(R.id.et_EndingTime);
        Spinner spinner = findViewById(R.id.spinnerEventType);
        Button btnView = findViewById(R.id.btnView);
        Button btnAdd = findViewById(R.id.btnAdd);
        ListView eventView = findViewById(R.id.eventView);

        String getDate = getIntent().getStringExtra("Date");
//        date.setText(getDate);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.EventTypeArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Events events = new Events (eventName.getText().toString(), spinner.getSelectedItem().toString(), getDate,
                        Integer.parseInt(startTime.getText().toString()), Integer.parseInt(endTime.getText().toString()) );
                Toast.makeText(AddAndViewActivity.this, events.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date.setText(getDate);
                Toast.makeText(AddAndViewActivity.this, "Add button clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

}