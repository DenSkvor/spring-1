package ru.geekbrains.spring.test;

public interface DoctorRepository {
    void add(Doctor doctor);
    void remove(String doctorType);

    String getFreeVisitDate(String doctorType);

}
