package com.nouroeddinne.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface EmployeDao {

    @Query("SELECT * FROM TE ORDER BY name ASC")
    LiveData<List<Employe>> AllEmploye();
    @Query("SELECT * FROM TE ORDER BY datebirth ASC")
    LiveData<List<Employe>> AllEmployebyDate();

    @Query("SELECT * FROM TE WHERE status=1")
    LiveData<List<Employe>> AllEmployeActive();

    @Query("SELECT * FROM TE WHERE status=1 ORDER BY name ASC")
    LiveData<List<Employe>> AllEmployeActivebyDate();

    @Query("SELECT * FROM TE WHERE status=0")
    LiveData<List<Employe>> AllEmployeInactive();

    @Query("SELECT * FROM TE WHERE status=0 ORDER BY name ASC")
    LiveData<List<Employe>> AllEmployeInactivebyDate();
    @Insert
    void insertEmploy(Employe Employe); //one Employee

    @Insert
    void insertEmploy(Employe... Employe); // onr employee or many employees

    @Update
    void updatetEmploy(Employe Employe);

    @Delete
    void deleteEmploy(Employe Employe);

    @Query("delete FROM TE WHERE email=:email")
    void deleteEmployByemail(String email);

    @Query("SELECT * FROM TE WHERE name LIKE :first AND " +
            "email LIKE :email ORDER BY name ASC")
    LiveData<List<Employe>> searchByNameandEmail(String first, String email);

//    @Query("SELECT * FROM TE WHERE datebirth >= :startDate AND datebirth<= :endDate ORDER BY dateBirth ASC")
//    LiveData<List<Employe>> employeByDate(Date startDate, Date endDate);
//
































}
