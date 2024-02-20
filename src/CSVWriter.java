import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {


    public static void writeToCSV(String csvFile, String[] header, String[][] data) {
        try (FileWriter writer = new FileWriter(csvFile)) {
            // Writing header
            writer.write(String.join(",", header) + "\n");

            // Writing data
            for (String[] row : data) {
                writer.write(String.join(",", row) + "\n");
            }

            System.out.println("Data written to CSV successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
