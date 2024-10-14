package dto;

import java.util.List;

public class EmployeePairProjectWork {

    private int employee1Id;
    private int employee2Id;
    private List<ProjectWorkDays> projectWorkDays;

    public EmployeePairProjectWork(int employee1Id, int employee2Id, List<ProjectWorkDays> projectWorkDays) {
        this.employee1Id = employee1Id;
        this.employee2Id = employee2Id;
        this.projectWorkDays = projectWorkDays;
    }

    public int getEmployee1Id() {
        return employee1Id;
    }

    public int getEmployee2Id() {
        return employee2Id;
    }

    public List<ProjectWorkDays> getProjectWorkDays() {
        return projectWorkDays;
    }
}
