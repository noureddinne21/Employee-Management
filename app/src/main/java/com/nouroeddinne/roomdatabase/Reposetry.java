package com.nouroeddinne.roomdatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

public class Reposetry {
    EmployeDao employeDao;
    SalaryDao salaryDao;
    public Reposetry(Application application) {
        RoomDB roomDB = RoomDB.getDatabase(application);
        employeDao = roomDB.employeDao();
        salaryDao = roomDB.salaryDao();

    }


    //Employe


    LiveData<List<Employe>> AllEmploye(){
        return employeDao.AllEmploye();
    }
    LiveData<List<Employe>> AllEmployebyDate(){
        return employeDao.AllEmployebyDate();
    }

    LiveData<List<Employe>> AllEmployeActive(){
        return employeDao.AllEmployeActive();
    }
    LiveData<List<Employe>> AllEmployeActivebyDate(){
        return employeDao.AllEmployeActivebyDate();
    }

    LiveData<List<Employe>> AllEmployeInactive(){
        return employeDao.AllEmployeInactive();
    }
    LiveData<List<Employe>> AllEmployeInactivebyDate(){
        return employeDao.AllEmployeInactivebyDate();
    }

    public void insertEmploy(Employe Employe){
        RoomDB.executorService.execute(new Runnable() {
            @Override
            public void run() {
                employeDao.insertEmploy(Employe);
            }
        });
    }

    public void insertEmploy(Employe... Employe){
        RoomDB.executorService.execute(new Runnable() {
            @Override
            public void run() {
                employeDao.insertEmploy(Employe);
            }
        });
    }

    public void updatetEmploy(Employe Employe){
        RoomDB.executorService.execute(new Runnable() {
            @Override
            public void run() {
                employeDao.updatetEmploy(Employe);
            }
        });
    }

    public void deleteEmploy(Employe Employe){
        RoomDB.executorService.execute(new Runnable() {
            @Override
            public void run() {
                employeDao.deleteEmploy(Employe);
            }
        });
    }

    public void deleteEmployByemail(String email){
        RoomDB.executorService.execute(new Runnable() {
            @Override
            public void run() {
                employeDao.deleteEmployByemail(email);
            }
        });
    }

    public LiveData<List<Employe>> searchByNameandEmail(String first, String email){
        return employeDao.searchByNameandEmail(first, email);
    }
//
//    public LiveData<List<Employe>> employeByDate(Date startDate, Date endDate){
//        return employeDao.employeByDate(startDate, endDate);
//    }
//




//salary



    public   LiveData<List<Salary>> AllSalary(){
        return salaryDao.AllSalary();
    }
    public   LiveData<List<Salary>> AllSalaryEmployee(int id){
        return salaryDao.AllSalaryEmployee(id);
    }

    public void insertSalary(Salary salary){
        RoomDB.executorService.execute(new Runnable() {
            @Override
            public void run() {
                salaryDao.insertSalary(salary);
            }
        });
    }

    public void insertSalary(Salary... salaries){
        RoomDB.executorService.execute(new Runnable() {
            @Override
            public void run() {
                salaryDao.insertSalary(salaries);
            }
        });
    }

    public void updatetSalary(Salary salary){
        RoomDB.executorService.execute(new Runnable() {
            @Override
            public void run() {
                salaryDao.updatetSalary(salary);
            }
        });
    }

    public void deleteSalary(Salary salary){
        RoomDB.executorService.execute(new Runnable() {
            @Override
            public void run() {
                salaryDao.deleteSalary(salary);
            }
        });
    }

    public void deleteSalaryByemail(String email){
        RoomDB.executorService.execute(new Runnable() {
            @Override
            public void run() {
                salaryDao.deleteSalaryByemail(email);
            }
        });
    }

























}
