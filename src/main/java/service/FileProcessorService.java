package service;

import dto.Employee;
import dto.EmployeePairProjectWork;
import dto.Project;
import dto.ProjectWorkDays;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.DateFormatter.calculateDaysBetweenRange;
import static util.DateFormatter.parseDate;

public class FileProcessorService {

    public static List<EmployeePairProjectWork> findEmployeeWorkPairs(File file) {
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

    private static List<EmployeePairProjectWork> findProjectPairs(List<Employee> employees) {
        List<EmployeePairProjectWork> employeePairProjectWorkDays = new ArrayList<>();

        for (int i = 0; i < employees.size(); i++) {
            for (int j = i + 1; j < employees.size(); j++) {
                List<ProjectWorkDays> projectData = findCommonProjects(employees.get(i), employees.get(j));

                if (!projectData.isEmpty()) {
                    int employee1Id = employees.get(i).getEmpId();
                    int employee2Id = employees.get(j).getEmpId();

                    employeePairProjectWorkDays.add(new EmployeePairProjectWork(employee1Id, employee2Id, projectData));
                }
            }
        }

        return employeePairProjectWorkDays;
    }

    private static List<ProjectWorkDays> findCommonProjects(Employee employee1, Employee employee2) {
        List<ProjectWorkDays> projectData = new ArrayList<>();

        for (Project project1 : employee1.getProjects()) {
            for (Project project2 : employee2.getProjects()) {
                if (project1.getProjectId() == (project2.getProjectId())) {
                    long daysWorked = calculateDaysBetweenRange(project1.getDateFrom(), project1.getDateTo(),
                            project2.getDateFrom(), project2.getDateTo());

                    if (daysWorked > 0) {
                        ProjectWorkDays projectWork = new ProjectWorkDays(project1.getProjectId(), daysWorked);
                        projectData.add(projectWork);
                    }
                }
            }
        }

        return projectData;
    }

}
