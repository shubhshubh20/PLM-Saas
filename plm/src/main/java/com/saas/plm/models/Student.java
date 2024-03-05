package com.saas.plm.models;


import com.gogettergeeks.annotation.FileDBGenerated;
import com.gogettergeeks.annotation.Persisted;
import com.gogettergeeks.annotation.UniqueKey;
import com.saas.plm.annotation.ApiGenerator;

@FileDBGenerated
@ApiGenerator
public class Student {

    @Persisted
    private String name;

    @Persisted
    @UniqueKey
    private int rollNumber;

    private double percentage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }
}

