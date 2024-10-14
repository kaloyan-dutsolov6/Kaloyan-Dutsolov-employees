package dto;

import java.util.List;

public record EmployeePairProjectWork(int employee1Id, int employee2Id, List<ProjectWorkDays> projectWorkDays) {

}
