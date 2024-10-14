package dto;

public class ProjectWorkDays {

    private int projectId;
    private long daysWorked;

    public ProjectWorkDays(int projectId, long daysWorked) {
        this.projectId = projectId;
        this.daysWorked = daysWorked;
    }

    public int getProjectId() {
        return projectId;
    }

    public long getDaysWorked() {
        return daysWorked;
    }

}
