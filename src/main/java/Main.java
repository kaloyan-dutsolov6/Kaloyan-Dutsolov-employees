import dto.EmployeePairProjectWork;
import dto.ProjectWorkDays;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.List;

import static service.FileProcessorService.findEmployeeWorkPairs;

public class Main {

    public static final String WINDOW_TITLE = "Employee Work Pair Task";
    public static final String BROWSE_CSV_BUTTON_TEXT = "Find CSV";
    public static final String COLUMN_EMPLOYEE_ID_1 = "Employee ID #1";
    public static final String COLUMN_EMPLOYEE_ID_2 = "Employee ID #2";
    public static final String COLUMN_PROJECT_ID = "Project ID";
    public static final String COLUMN_DAYS_WORKED = "Days Worked";

    public static void main(String[] args) {
        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        JButton openButton = new JButton(BROWSE_CSV_BUTTON_TEXT);
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(openButton, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        openButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                List<EmployeePairProjectWork> commonProjects = findEmployeeWorkPairs(selectedFile);
                createTableModel(table, commonProjects);
            }
        });

        frame.setVisible(true);
    }

    private static void createTableModel(JTable table, List<EmployeePairProjectWork> commonProjects) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{COLUMN_EMPLOYEE_ID_1, COLUMN_EMPLOYEE_ID_2,
                        COLUMN_PROJECT_ID, COLUMN_DAYS_WORKED}, 0);

        for (EmployeePairProjectWork employeePairProjectWork : commonProjects) {
            for (ProjectWorkDays projectWorkDays : employeePairProjectWork.getProjectWorkDays()) {
                model.addRow(new Object[]{
                        employeePairProjectWork.getEmployee1Id(),
                        employeePairProjectWork.getEmployee2Id(),
                        projectWorkDays.getProjectId(),
                        projectWorkDays.getDaysWorked()
                });
            }
        }

        table.setModel(model);
    }
}
