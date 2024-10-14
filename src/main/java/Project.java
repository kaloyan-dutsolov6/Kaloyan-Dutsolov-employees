import java.time.LocalDate;

public class Project {

    private int projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Project(int projectId, LocalDate dateFrom, LocalDate dateTo) {
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
