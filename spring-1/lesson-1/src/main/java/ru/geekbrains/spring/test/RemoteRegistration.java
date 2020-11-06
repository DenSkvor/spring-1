package ru.geekbrains.spring.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoteRegistration {

    @Autowired
    private DoctorRepository clinicReception;
/*
    @Autowired
    public void setClinicReception(DoctorRepository clinicReception) {
        this.clinicReception = clinicReception;
    }
*/

    public void register(String doctorType){
        String date = clinicReception.getFreeVisitDate(doctorType);
        if(date != null) {
            System.out.printf("Вы записаны к %sу на %s число.%n", doctorType, date);
            clinicReception.remove(doctorType);
        }else System.out.printf("Свободных номерков к %sу нет.%n", doctorType);
    }

}
