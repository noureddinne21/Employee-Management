package com.nouroeddinne.roomdatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kotlin.jvm.Volatile;

@Database(entities = {Employe.class,Salary.class},version =1,exportSchema = true)
public abstract class RoomDB extends RoomDatabase {

    public abstract EmployeDao employeDao();
    public abstract SalaryDao salaryDao();
    private static volatile RoomDB INSTANCE ;
    private static final int NUMBER_OF_THREDS = 4;
    static ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREDS);

    public static RoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RoomDB.class, "employee_salary_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }



    @Override
    public void clearAllTables() {
        runInTransaction(() -> {
            for (String table : getTableNames()) {
                this.query("DELETE FROM " + table, null);
            }
        });
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(@NonNull DatabaseConfiguration databaseConfiguration) {
        return null;
    }






    private List<String> getTableNames() {
        return Arrays.asList("TE","salary"); // Add more tables if needed
    }



































}

