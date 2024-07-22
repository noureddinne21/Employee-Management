package com.nouroeddinne.roomdatabase;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddEmployee extends AppCompatActivity implements Serializable, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    EditText name,address,title,about,skils,phone,email;
    TextView pickDate;
    RadioGroup radioGroup;
    RadioButton male,female;
    Button add;
    String gender = "" ;
    Date currentDate ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_employee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.editTextText);
        title = findViewById(R.id.editTextText2);
        about = findViewById(R.id.editTextText3);
        skils = findViewById(R.id.editTextText4);
        phone = findViewById(R.id.editTextText5);
        email = findViewById(R.id.editTextText6);
        address = findViewById(R.id.editTextText7);
        male = findViewById(R.id.radioButton);
        female = findViewById(R.id.radioButton2);
        radioGroup = findViewById(R.id.radioGroup);
        add = findViewById(R.id.button2);
        pickDate = findViewById(R.id.textView_pick_date);




        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButton) {
                male.setError(null);
                female.setError(null);
                gender = male.getText().toString();
            } else if (checkedId == R.id.radioButton2) {
                female.setError(null);
                male.setError(null);
                gender = female.getText().toString();
            } else {
                male.setError("Please enter");
                female.setError("Please enter");
            }
        });

        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        add.setOnClickListener(v -> {

            if (name.getText().toString() == null || name.getText().toString().isEmpty()){
                name.setError("Please enter name");
            }else {
                if (gender == null || gender.isEmpty()){
                    male.setError("Please enter");
                    female.setError("Please enter");
                }else {
                    if (title.getText().toString() == null || title.getText().toString().isEmpty()){
                        title.setError("Please enter title");
                    }else {
                        if (about.getText().toString() == null || about.getText().toString().isEmpty()){
                            about.setError("Please enter about");
                        }else {
                            if (skils.getText().toString() == null || skils.getText().toString().isEmpty()){
                                skils.setError("Please enter skils");
                            }else {
                                if (phone.getText().toString() == null || phone.getText().toString().isEmpty()){
                                    phone.setError("Please enter phone");
                                }else {
                                    if (email.getText().toString() == null || email.getText().toString().isEmpty()){
                                        email.setError("Please enter name");
                                    }else {
                                        if (address.getText().toString() == null || address.getText().toString().isEmpty()){
                                            address.setError("Please enter name");
                                        }else {
                                            if (currentDate == null){
                                                Toast.makeText(this, "Please enter date", Toast.LENGTH_SHORT).show();
                                                showDatePickerDialog();
                                            }else {
                                                Employe e = new Employe(name.getText().toString(),email.getText().toString(),currentDate,title.getText().toString(),gender, about.getText().toString(),
                                                        skils.getText().toString(),address.getText().toString(),phone.getText().toString(),true);
                                                Intent intent = new Intent(AddEmployee.this,MainActivity.class);
                                                intent.putExtra("employee",e);
                                                setResult(RESULT_OK, intent);
                                                finish();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        });








    }



//    private Date showDatePickerDialogAndGetDate() {
//        final Calendar c = Calendar.getInstance();
//        int year = c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH);
//        int day = c.get(Calendar.DAY_OF_MONTH);
//
//        // Use a DatePickerDialog to select a date
//        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
//                new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        // Create a Calendar instance with the selected date
//                        Calendar selectedCalendar = Calendar.getInstance();
//                        selectedCalendar.set(year, monthOfYear, dayOfMonth);
//
//                        // Assign the selected date to datebirth
//                        currentDate = selectedCalendar.getTime();
//                        pickDate.setText(String.valueOf(currentDate));
//                        Log.d("TAG", "onDateSet: "+currentDate);
//                        // Update UI or perform other actions here if needed
//                        // For example, update a TextView with the selected date
//                        // textView.setText(datebirth.toString());
//                    }
//                },
//                year, month, day);
//        // Show the DatePickerDialog
//        datePickerDialog.show();
//        // Return the selected date (Note: Initially it will return null or you can handle this accordingly)
//        return currentDate;
//    }

    private void showDatePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(year, monthOfYear, dayOfMonth);
                        currentDate= selectedCalendar.getTime();
                        pickDate.setText(DateCoverter.handleSelectedDate(currentDate));
                    }
                },
                year, month, day);

        datePickerDialog.show();
    }



//    private String handleSelectedDate(Date selectedDate) {
//        currentDate = selectedDate;
//        String formattedDate = formatDate(currentDate);
//        return formattedDate;
//    }
//
//    private String formatDate(Date date) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
//        return dateFormat.format(date);
//    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Toast.makeText(this, ""+year+"/"+month+"/"+dayOfMonth, Toast.LENGTH_SHORT).show();
    }



















}