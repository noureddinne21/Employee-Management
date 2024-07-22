package com.nouroeddinne.roomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends AndroidViewModel {

    Reposetry reposetry;
    public MyViewModel(@NonNull Application application) {
        super(application);
        reposetry = new Reposetry(application);
    }



    //Employe


    LiveData<List<Employe>> AllEmploye(){
        return reposetry.AllEmploye();
    }
    LiveData<List<Employe>> AllEmployebyDate(){
        return reposetry.AllEmployebyDate();
    }

    LiveData<List<Employe>> AllEmployeActive(){
        return reposetry.AllEmployeActive();
    }
    LiveData<List<Employe>> AllEmployeActivebyDate(){
        return reposetry.AllEmployeActivebyDate();
    }
    LiveData<List<Employe>> AllEmployeInactive(){
        return reposetry.AllEmployeInactive();
    }
    LiveData<List<Employe>> AllEmployeInactivebyDate(){
        return reposetry.AllEmployeInactivebyDate();
    }
    public void insertEmploy(Employe Employe){
        reposetry.insertEmploy(Employe);;
    }

    public void insertEmploy(Employe... Employe){
        reposetry.insertEmploy(Employe);
    }

    public void updatetEmploy(Employe Employe){
        reposetry.updatetEmploy(Employe);
    }

    public void deleteEmploy(Employe Employe){
        reposetry.deleteEmploy(Employe);
    }

    public void deleteEmployByemail(String email){
        reposetry.deleteEmployByemail(email);
    }

    public LiveData<List<Employe>> findByNameandEmail(String first, String email){
        return reposetry.searchByNameandEmail(first, email);
    }

//    public LiveData<List<Employe>> employeByDate(Date startDate, Date endDate){
//        return reposetry.employeByDate(startDate, endDate);
//    }
//




//salary



    public   LiveData<List<Salary>> AllSalary(){
        return reposetry.AllSalary();
    }

    public   LiveData<List<Salary>> AllSalaryEmployee(int id){
        return reposetry.AllSalaryEmployee(id);
    }

    public void insertSalary(Salary salary){
        reposetry.insertSalary(salary);
    }

    public void insertSalary(Salary... salaries){
        reposetry.insertSalary(salaries);
    }

    public void updatetSalary(Salary salary){
        reposetry.updatetSalary(salary);
    }

    public void deleteSalary(Salary salary){
        reposetry.deleteSalary(salary);
    }

    public void deleteSalaryByemail(String email){
        reposetry.deleteSalaryByemail(email);
    }

























































}
