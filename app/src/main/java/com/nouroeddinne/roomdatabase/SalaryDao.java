package com.nouroeddinne.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Dao

public interface SalaryDao {

    @Query("SELECT * FROM Salary ORDER BY id ASC")
    LiveData<List<Salary>> AllSalary();

    @Query("SELECT * FROM Salary WHERE empId=:id ORDER BY salary ASC")
    LiveData<List<Salary>> AllSalaryEmployee( int id );

    @Insert
    void insertSalary(Salary salary);

    @Insert
    void insertSalary(Salary... salaries);

    @Update
    void updatetSalary(Salary salary);

    @Delete
    void deleteSalary(Salary salary);

    @Query("delete FROM Salary WHERE empId=:id")
    void deleteSalaryByemail(String id);




















}
