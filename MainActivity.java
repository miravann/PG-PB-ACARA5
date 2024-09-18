package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private NumberPicker hourPicker, minutePicker, amPmPicker;
    private Spinner statusSpinner;
    private EditText descriptionEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupTimePickers();
        setupStatusSpinner();
        setupSubmitButton();
    }

    private void initViews() {
        calendarView = findViewById(R.id.calendarView);
        hourPicker = findViewById(R.id.hourPicker);
        minutePicker = findViewById(R.id.minutePicker);
        amPmPicker = findViewById(R.id.amPmPicker);
        statusSpinner = findViewById(R.id.statusSpinner);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        submitButton = findViewById(R.id.submitButton);
    }

    private void setupTimePickers() {
        hourPicker.setMinValue(1);
        hourPicker.setMaxValue(12);

        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);
        minutePicker.setFormatter(i -> String.format("%02d", i));

        amPmPicker.setMinValue(0);
        amPmPicker.setMaxValue(1);
        amPmPicker.setDisplayedValues(new String[]{"AM", "PM"});

        // Set current time
        Calendar calendar = Calendar.getInstance();
        hourPicker.setValue(calendar.get(Calendar.HOUR));
        minutePicker.setValue(calendar.get(Calendar.MINUTE));
        amPmPicker.setValue(calendar.get(Calendar.AM_PM));
    }

    private void setupStatusSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);
    }

    private void setupSubmitButton() {
        submitButton.setOnClickListener(v -> {
            String selectedDate = calendarView.getDate() + "";
            String selectedTime = hourPicker.getValue() + ":" +
                    String.format("%02d", minutePicker.getValue()) + " " +
                    (amPmPicker.getValue() == 0 ? "AM" : "PM");
            String selectedStatus = statusSpinner.getSelectedItem().toString();
            String description = descriptionEditText.getText().toString();

            String message = "Date: " + selectedDate + "\n" +
                    "Time: " + selectedTime + "\n" +
                    "Status: " + selectedStatus + "\n" +
                    "Description: " + description;

            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        });
    }
}