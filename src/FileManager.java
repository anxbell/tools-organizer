import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    private String filename;

    public FileManager(String filename) {
        this.filename = filename;
    }
    
    public static final String COMMA_DELIMITER = ",";

    public void saveToFile(ToolManager manager) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Tool tool : manager.getAllTools()) {
                writer.write(
                    tool.getName() + "," + 
                    tool.getCategory() + "," + 
                    tool.getLocation() + "," + 
                    tool.isBorrowed() + "," + 
                   (tool.getBorrowedBy() != null ? tool.getBorrowedBy() : "") 
                );
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    

    public void loadFromFile(ToolManager manager) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA_DELIMITER);
                if (parts.length >= 5) {
                    String name = parts[0];
                    String category = parts[1];
                    String location = parts[2];
                    boolean isBorrowed = Boolean.parseBoolean(parts[3]);
                    String borrowedBy = parts[4].isEmpty() ? null : parts[4];

                    Tool tool = new Tool(name, category, location, isBorrowed, borrowedBy);
                    manager.addTool(tool);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
}
// https://www.baeldung.com/java-csv-file-array
// https://www.baeldung.com/tag/java-filereader