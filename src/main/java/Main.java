import javax.swing.*;
import java.awt.*;
import java.io.File;

import static service.FileProcessorService.findEmployeeWorkPairs;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee Work Pair Task");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        JButton findCsvButton = new JButton("Find CSV");
        findCsvButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                findEmployeeWorkPairs(selectedFile);
                displayResult();
            }
        });

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(findCsvButton, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static void displayResult() {

    }
}
