package dto;

import java.time.LocalDate;

public record Project(int projectId, LocalDate dateFrom, LocalDate dateTo) {

}
