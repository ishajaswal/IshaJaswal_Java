package package1;
import java.io.*;
import java.util.*;
public class CSV {

    public static void saveDataToCSV(String fileName, List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String[] row : data) {
                bw.write(String.join(",", row));
                bw.newLine();  
            }
            System.out.println("Data saved to CSV successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data to CSV: " + e.getMessage());
        }
    }

   
    public static void loadDataFromCSV(String fileName) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                System.out.println(Arrays.toString(values));
            }
        } catch (IOException e) {
            System.out.println("Error loading data from CSV: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String fileName = "data.csv";

      
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"Name", "Age", "City"});
        data.add(new String[]{"John", "25", "New York"});
        data.add(new String[]{"Alice", "30", "Los Angeles"});
        data.add(new String[]{"Bob", "22", "Chicago"});

       
        saveDataToCSV(fileName, data);

       
        System.out.println("Loading data from CSV file:");
        loadDataFromCSV(fileName);
    }
}

