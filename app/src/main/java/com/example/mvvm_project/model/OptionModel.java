package com.example.mvvm_project.model;

public class OptionModel {

    private String driver;

    public OptionModel(String driver, String student) {
        this.driver = driver;
        this.student = student;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

   private String student;
}
