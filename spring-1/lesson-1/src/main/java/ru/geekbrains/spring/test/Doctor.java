package ru.geekbrains.spring.test;

public class Doctor {

    private String doctorType;
    private String freeVisitDate;

    public Doctor(String doctorType, String freeVisitDate){
        this.doctorType = doctorType;
        this.freeVisitDate = freeVisitDate;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public String getFreeVisitDate() {
        return freeVisitDate;
    }
}
