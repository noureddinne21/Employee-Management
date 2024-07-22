package com.nouroeddinne.roomdatabase;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;

@Entity(foreignKeys = {@ForeignKey(entity = Employe.class,parentColumns = {"id"},childColumns = {"empId"},onUpdate = ForeignKey.CASCADE,onDelete = ForeignKey.CASCADE)})
@TypeConverters({DateCoverter.class})

public class Salary implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double salary;
    private int empId;
    private Date datePayment;
    private String InformationPayment;

    public Salary(int id, double salary, int empId, Date datePayment, String informationPayment) {
        this.id = id;
        this.salary = salary;
        this.empId = empId;
        this.datePayment = datePayment;
        InformationPayment = informationPayment;
    }

    public Salary(double salary, int empId, Date datePayment, String informationPayment) {
        this.salary = salary;
        this.empId = empId;
        this.datePayment = datePayment;
        InformationPayment = informationPayment;
    }

    public Salary() {
    }

    public Salary(int id, double salary, int empId) {
        this.id = id;
        this.salary = salary;
        this.empId = empId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    public String getInformationPayment() {
        return InformationPayment;
    }

    public void setInformationPayment(String informationPayment) {
        InformationPayment = informationPayment;
    }
}
