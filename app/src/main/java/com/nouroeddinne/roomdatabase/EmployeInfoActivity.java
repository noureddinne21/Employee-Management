package com.nouroeddinne.roomdatabase;

import static android.widget.Toast.LENGTH_LONG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

public class EmployeInfoActivity extends AppCompatActivity {

    LinearLayout linearInfo,linearSalary;
    TextView name,address,title,about,skils,phone,email;
    RecyclerView recyclerView;
    Button add,showSalary;
    EditText salary,info;
    RecyclerView.Adapter adapter;
    Context context=this;
    MyViewModel myViewModel;
    private List<Salary> SalaryList;
    private long id=-1;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employe_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.textView13);
        title = findViewById(R.id.textView14);
        about = findViewById(R.id.textView15);
        skils = findViewById(R.id.textView17);
        phone = findViewById(R.id.textView22);
        email = findViewById(R.id.textView23);
        address = findViewById(R.id.textView25);
        recyclerView = findViewById(R.id.recyclerView2);
        add = findViewById(R.id.button);
        salary = findViewById(R.id.editTextText9);
        info = findViewById(R.id.editTextText8);
        linearInfo = findViewById(R.id.linear_more_info);
        linearSalary = findViewById(R.id.linear_add);
        showSalary = findViewById(R.id.button3);

        linearInfo.setVisibility(View.VISIBLE);
        linearSalary.setVisibility(View.GONE);

        Date currentDate = new Date();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);



        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            Employe e = (Employe) extras.getSerializable("employee");

            if (e.getId()>=0){
                id=e.getId();
            }

            if (e.getName()==null || e.getName().isEmpty()){
                name.setText("null");
            }else{
                name.setText(e.getName());
            }

            if (e.getTitle()==null || e.getTitle().isEmpty()){
                title.setText("null");
            }else{
                title.setText(e.getTitle());
            }

            if (e.getAbout()==null || e.getAbout().isEmpty()){
                about.setText("null");
            }else{
                about.setText(e.getAbout());
            }

            if (e.getSkils()==null || e.getSkils().isEmpty()){
                skils.setText("null");
            }else{
                skils.setText(e.getSkils());
            }

            if (e.getPhone()==null || e.getPhone().isEmpty()){
                phone.setText("null");
            }else{
                phone.setText(e.getPhone());
            }

            if (e.getEmail()==null || e.getEmail().isEmpty()){
                email.setText("null");
            }else{
                email.setText(e.getEmail());
            }

            if (e.getAddress()==null || e.getAddress().isEmpty()){
                address.setText("null");
            }else{
                address.setText(e.getAddress());
            }
        }

        myViewModel.AllSalaryEmployee((int) id).observe(this, new Observer<List<Salary>>() {
            @Override
            public void onChanged(List<Salary> salaries) {
                adapter = new SalaryAdapter(context,salaries);
                recyclerView.setAdapter(adapter);

                //Toast.makeText(context, salaries.size()+"", Toast.LENGTH_SHORT).show();
//                for (Salary s : salaries){
//                    Log.d("TAG", "onChanged: <"+id+"> "+s.getId()+" <"+s.getEmpId()+"> "+s.getSalary());
//                }

            }
        });

//        myViewModel.AllSalary().observe(this, new Observer<List<Salary>>() {
//            @Override
//            public void onChanged(List<Salary> salaries) {
//                adapter = new SalaryAdapter(context,salaries);
//                recyclerView.setAdapter(adapter);
//
//                //Toast.makeText(context, salaries.size()+"", Toast.LENGTH_SHORT).show();
//                for (Salary s : salaries){
//                    Log.d("TAG", "onChanged: <"+id+"> "+s.getId()+" <"+s.getEmpId()+"> "+s.getSalary());
//                }
//
//            }
//        });


        showSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearInfo.setVisibility(View.GONE);
                linearSalary.setVisibility(View.VISIBLE);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.insertSalary(new Salary(Double.parseDouble(salary.getText().toString()), (int) id,currentDate,info.getText().toString()));
            }
        });





    }






















}