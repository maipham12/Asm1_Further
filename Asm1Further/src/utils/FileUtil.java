package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for file operations.
 * @author Your Full Name - Your Student ID
 */
public class FileUtil {

    /**
     * Writes a list of strings to a file.
     * @param filePath The path to the file.
     * @param lines The lines to write.
     */
    public static void writeFile(String filePath, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Reads lines from a file into a list of strings.
     * @param filePath The path to the file.
     * @return A list of lines read from the file.
     */
    public static List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return lines;
    }
}
