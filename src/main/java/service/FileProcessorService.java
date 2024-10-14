package service;

import dto.Employee;
import dto.Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.DateFormatter.parseDate;

public class FileProcessorService {

    public static Map<Employee, Employee> findEmployeeWorkPairs(File file) {
        List<Employee> employees = readEmployees(file);

        return findProjectPairs(employees);
    }

    private static List<Employee> readEmployees(File file) {
        Map<Integer, Employee> employeeMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] empData = line.split(",");
                int empId = Integer.parseInt(empData[0].trim());
                int projectId = Integer.parseInt(empData[1].trim());
                LocalDate dateFrom = parseDate(empData[2].trim());
                LocalDate dateTo = empData[3].trim().equals("NULL") ? LocalDate.now() : parseDate(empData[3].trim());

                Employee employee = employeeMap.computeIfAbsent(empId, Employee::new);
                employee.addProject(new Project(projectId, dateFrom, dateTo));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(employeeMap.values());
    }

    private static Map<Employee, Employee> findProjectPairs(List<Employee> employees) {

        return new HashMap<>();
    }
}
