package ru.geekbrains.spring.test;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ClinicReception implements DoctorRepository {

    private HashMap<String, String> doctorDataBase;

    @PostConstruct
    private void init(){
        doctorDataBase = new HashMap<>();
        add(new Doctor("Терапевт", "10.01"));
        add(new Doctor("Гигапевт", "11.01"));
        add(new Doctor("Мегапевт", "12.01"));
    }

    @Override
    public void add(Doctor doctor) {
        doctorDataBase.put(doctor.getDoctorType(), doctor.getFreeVisitDate());
    }

    @Override
    public void remove(String doctorType) {
        doctorDataBase.remove(doctorDataBase.get(doctorType));
    }

    @Override
    public String getFreeVisitDate(String doctorType) {
        for (Map.Entry<String, String> me : doctorDataBase.entrySet()) {
            if(me.getKey().equalsIgnoreCase(doctorType)) return me.getValue();
        }
        return null;
    }
}
