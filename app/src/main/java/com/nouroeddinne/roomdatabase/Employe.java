package com.nouroeddinne.roomdatabase;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "TE")
@TypeConverters({DateCoverter.class})
public class Employe implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "datebirth")
    private Date datebirth;
    private String gender;
    private String title;
    private String about;
    private String skils;
    private String address;
    private String phone;
    private boolean status;



    public Employe() {
    }

    public Employe(String name, String email, Date datebirth) {
        this.name = name;
        this.email = email;
        this.datebirth = datebirth;
    }

    public Employe(long id, String name, String email, Date datebirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.datebirth = datebirth;
    }

    public Employe(String name, String email, Date datebirth,String title, String gender, String about, String skils, String address, String phone,boolean status) {
        this.name = name;
        this.email = email;
        this.datebirth = datebirth;
        this.gender = gender;
        this.about = about;
        this.skils = skils;
        this.address = address;
        this.phone = phone;
        this.status = status;
        this.title = title;
    }

    public Employe(long id, String name, String email, Date datebirth,String title ,String gender, String about, String skils, String address, String phone,boolean status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.datebirth = datebirth;
        this.gender = gender;
        this.about = about;
        this.skils = skils;
        this.address = address;
        this.phone = phone;
        this.status = status;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatebirth() {
        return datebirth;
    }

    public void setDatebirth(Date datebirth) {
        this.datebirth = datebirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getSkils() {
        return skils;
    }

    public void setSkils(String skils) {
        this.skils = skils;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
