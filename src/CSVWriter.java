import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CSVWriter {

    String file = "samcsv.csv";

    String header = "Run number, Algorithm, totalNumOfNodes, numOfNodesVisited, numOfNodesToGoal, elapsedTime" ;

    public void writeToCSV(List<PathfindingData> pathfindingDataList) {
        try (FileWriter writer = new FileWriter(file, true)) {

            File myfile = new File(file);


            if (myfile.length() == 0) {
                writer.write(String.join(",", header) + "\n");
            }

            for (PathfindingData data : pathfindingDataList) {
                writer.write(data.toCSV() + "\n");
            }

            writer.close();
            System.out.println("Data written to CSV successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
