package com.bednara.application;

import java.sql.Timestamp;

public class Employee extends Person {

    // Für die zukünftige Implementierung des Admin Dashboards

    private int employeeID = 0;
    private String jobTitle = "";
    private double salary = 0.0;
    private String userName = "";
    private String userPassword = "";

    public Employee(String firstName, String lastName, Timestamp birthDate, String phoneNumber, String mailAddress,
            String streetName, int streetNumber, int apartmentNumber, String zipCode, int employeeID, String jobTitle,
            double salary, String city, String country) {
        super(firstName, lastName, birthDate, phoneNumber, mailAddress, streetName, streetNumber, apartmentNumber,
                zipCode, city, country);
        this.employeeID = employeeID;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPassword() {
        return userPassword;
    }

}
