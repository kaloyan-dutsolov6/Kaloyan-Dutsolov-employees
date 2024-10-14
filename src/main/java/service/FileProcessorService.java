package service;

import dto.Employee;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileProcessorService {

    public static Map<Employee, Employee> findEmployeeWorkPairs(File file) {
        List<Employee> employees = readEmployees(file);

        return findProjectPairs(employees);
    }

    private static List<Employee> readEmployees(File file) {

        return new ArrayList<>();
    }

    private static Map<Employee, Employee> findProjectPairs(List<Employee> employees) {

        return new HashMap<>();
    }
}
