package com.saas.plm.annotations;

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
