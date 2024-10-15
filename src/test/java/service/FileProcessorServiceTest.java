package service;

import dto.EmployeePairProjectWork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileProcessorServiceTest {

    private File testFile;

    @BeforeEach
    public void setup() throws IOException {
        testFile = Files.createTempFile("testEmployeeData", ".csv").toFile();

        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("101,3001,2024-01-01,2024-02-15\n");
            writer.write("102,3001,2024-01-10,2024-03-01\n");
            writer.write("103,3001,2024-02-15,2024-05-01\n");
            writer.write("104,3001,2024-06-01,NULL\n");
            writer.write("101,3002,2024-02-01,2024-04-01\n");
            writer.write("104,3003,2024-03-01,NULL\n");
        }
    }

    @Test
    public void findEmployeeWorkPairsTest_TwoEmployeeWorkPairs() {
        List<EmployeePairProjectWork> result = FileProcessorService.findEmployeeWorkPairs(testFile);

        assertNotNull(result);
        assertEquals(3, result.size());
        checkResult(result.get(0), 0, 101, 102, 3001, 37);
        checkResult(result.get(1), 0, 101, 103, 3001, 1);
        checkResult(result.get(2), 0, 102, 103, 3001, 16);
    }

    @Test
    public void findEmployeeWorkPairsTest_NoOverlappingProjects() throws IOException {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("101,3001,2024-01-01,2024-02-15\n");
            writer.write("102,3002,2024-01-10,2024-03-01\n");
        }

        List<EmployeePairProjectWork> result = FileProcessorService.findEmployeeWorkPairs(testFile);

        assertNotNull(result);
        assertTrue(result.isEmpty(), "Expected no overlapping projects");
    }

    @Test
    public void findEmployeeWorkPairsTest_PairsForMultipleProjects() throws IOException {
        try (FileWriter writer = new FileWriter(testFile, true)) {
            writer.write("102,3002,2024-01-15,2024-04-01\n");
            writer.write("101,3002,2024-02-01,2024-04-01\n");
        }

        List<EmployeePairProjectWork> result = FileProcessorService.findEmployeeWorkPairs(testFile);

        assertNotNull(result);
        assertEquals(3, result.size());

        assertEquals(2, result.get(0).projectWorkDays().size());

        checkResult(result.get(0), 0, 101, 102, 3001, 37);
        checkResult(result.get(0), 1, 101, 102, 3002, 61);
    }

    private void checkResult(EmployeePairProjectWork pair, int projectWorkDayIndex, int emp1Id, int emp2Id, int projectId, int daysWorked) {
        assertEquals(emp1Id, pair.employee1Id());
        assertEquals(emp2Id, pair.employee2Id());
        assertEquals(projectId, pair.projectWorkDays().get(projectWorkDayIndex).projectId());
        assertEquals(daysWorked, pair.projectWorkDays().get(projectWorkDayIndex).daysWorked());
    }
}
