package dto;

import java.util.HashSet;
import java.util.Set;

public class Employee {

    private int empId;
    private Set<Project> projects;

    public Employee(int empId) {
        this.empId = empId;
        this.projects = new HashSet<>();
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public int getEmpId() {
        return empId;
    }

    public Set<Project> getProjects() {
        return projects;
    }
}
