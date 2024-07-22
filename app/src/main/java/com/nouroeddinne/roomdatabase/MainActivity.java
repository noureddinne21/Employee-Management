package com.nouroeddinne.roomdatabase;

import static android.widget.Toast.LENGTH_LONG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nouroeddinne.roomdatabase.databinding.ActivityMainBinding;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MyViewModel myViewModel;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    Context context=this;
    TextView totalEmployee,activeEmployee;
    Button add;
    ImageView filter;
    boolean showEmployActive=true,showEmployInactive=true;

    ActivityResultLauncher<Intent> launchResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if (o!=null&&o.getResultCode()==RESULT_OK){
                        Employe e = (Employe) o.getData().getSerializableExtra("employee");
                        myViewModel.insertEmploy(e);
                    }
                }
            });

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        totalEmployee = findViewById(R.id.textView_total_employee);
        activeEmployee = findViewById(R.id.textView_active_employee);
        add = findViewById(R.id.button_add);
        filter = findViewById(R.id.imageView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        myViewModel.AllEmploye().observe(this, new Observer<List<Employe>>() {
            @Override
            public void onChanged(List<Employe> employes) {
                adapter = new EmployeeAdapter(context,employes);
                recyclerView.setAdapter(adapter);

//                for (Employe e : employes){
//                    Log.d("TAG", "onChanged: "+e.getGender());
//                }

                totalEmployee.setText(String.valueOf(employes.size()));
                activeEmployee.setText(String.valueOf(activeEmployee(employes)));

            }
        });

        filter.setOnClickListener(v ->{

            PopupMenu popup = new PopupMenu(v.getContext(),filter);
            popup.inflate(R.menu.sort_by);

            MenuItem statusItem1 = popup.getMenu().findItem(R.id.menu3);
            statusItem1.setChecked(showEmployActive);
            MenuItem statusItem2 = popup.getMenu().findItem(R.id.menu4);
            statusItem2.setChecked(showEmployInactive);

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == R.id.menu1) {
                        showEmployByNameandByDate(showEmployActive,showEmployInactive,"name");
                        return true;
                    }else if (item.getItemId() == R.id.menu2) {
                        showEmployByNameandByDate(showEmployActive,showEmployInactive,"date");
                        return true;
                    } else if (item.getItemId() == R.id.menu3) {
                        if (showEmployInactive){
                            showEmployActive = !showEmployActive;
                            statusItem1.setChecked(showEmployActive);
                            showEmployActiveandInactive(showEmployActive,showEmployInactive);
                        }
                        return true;
                    } else if (item.getItemId() == R.id.menu4) {
                        if (showEmployActive){
                            showEmployInactive = !showEmployInactive;
                            statusItem2.setChecked(showEmployInactive);
                            showEmployActiveandInactive(showEmployActive,showEmployInactive);
                        }
                        return true;
                    }
                    return false;
                }
            });

            popup.show();


        });

        add.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEmployee.class);
            launchResult.launch(intent);
        });




    }


public int activeEmployee(List<Employe> employes){
        int total = 0;
        for (Employe e : employes){
            if (e.getStatus()){
                total++;
            }
        }
        return total;
}



public void showEmployActiveandInactive(boolean s1, boolean s2){

        if (s1&&s2) {
            myViewModel.AllEmploye().observe(MainActivity.this, new Observer<List<Employe>>() {
                @Override
                public void onChanged(List<Employe> employes) {
                    adapter = new EmployeeAdapter(context,employes);
                    recyclerView.setAdapter(adapter);
                }
            });
        }else {
            if (s1){
                myViewModel.AllEmployeActive().observe(MainActivity.this, new Observer<List<Employe>>() {
                    @Override
                    public void onChanged(List<Employe> employes) {
                        adapter = new EmployeeAdapter(context,employes);
                        recyclerView.setAdapter(adapter);
                    }
                });
            } else if (s2) {
                myViewModel.AllEmployeInactive().observe(MainActivity.this, new Observer<List<Employe>>() {
                    @Override
                    public void onChanged(List<Employe> employes) {
                        adapter = new EmployeeAdapter(context,employes);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        }

}



    public void showEmployByNameandByDate(boolean s1, boolean s2,String type){

        switch (type){
            case "name":

                if (s1&&s2) {
                    myViewModel.AllEmploye().observe(MainActivity.this, new Observer<List<Employe>>() {
                        @Override
                        public void onChanged(List<Employe> employes) {
                            adapter = new EmployeeAdapter(context,employes);
                            recyclerView.setAdapter(adapter);
                        }
                    });
                }else {
                    if (s1){
                        myViewModel.AllEmployeActive().observe(MainActivity.this, new Observer<List<Employe>>() {
                            @Override
                            public void onChanged(List<Employe> employes) {
                                adapter = new EmployeeAdapter(context,employes);
                                recyclerView.setAdapter(adapter);
                            }
                        });
                    } else if (s2) {
                        myViewModel.AllEmployeInactive().observe(MainActivity.this, new Observer<List<Employe>>() {
                            @Override
                            public void onChanged(List<Employe> employes) {
                                adapter = new EmployeeAdapter(context,employes);
                                recyclerView.setAdapter(adapter);
                            }
                        });
                    }
                }

                break;
            case "date":

                if (s1&&s2) {
                    myViewModel.AllEmployebyDate().observe(MainActivity.this, new Observer<List<Employe>>() {
                        @Override
                        public void onChanged(List<Employe> employes) {
                            adapter = new EmployeeAdapter(context,employes);
                            recyclerView.setAdapter(adapter);
                        }
                    });
                }else {
                    if (s1){
                        myViewModel.AllEmployeActivebyDate().observe(MainActivity.this, new Observer<List<Employe>>() {
                            @Override
                            public void onChanged(List<Employe> employes) {
                                adapter = new EmployeeAdapter(context,employes);
                                recyclerView.setAdapter(adapter);
                            }
                        });
                    } else if (s2) {
                        myViewModel.AllEmployeInactivebyDate().observe(MainActivity.this, new Observer<List<Employe>>() {
                            @Override
                            public void onChanged(List<Employe> employes) {
                                adapter = new EmployeeAdapter(context,employes);
                                recyclerView.setAdapter(adapter);
                            }
                        });
                    }
                }

                break;
        }

    }


















}
