import java.util.HashSet;
import java.util.Set;

public class Employee {

    private int empId;
    private Set<Project> projects = new HashSet<>();

    public Employee(int empId) {
        this.empId = empId;
    }

    public void addProject(Project project) {
        projects.add(project);
    }
}
