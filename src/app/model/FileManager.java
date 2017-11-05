package app.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class FileManager {

    // Creates the specified directory if it doesn't exist yet
    public static void createDirectory(String directory) {
        try {
            if (!Files.exists(Paths.get(directory.toLowerCase()))) {
                Files.createDirectory(Paths.get(directory.toLowerCase()));
            }
        } catch (IOException ioe) {
            System.out.println("ERROR - Could not create directory!");
        }
    }

    // Creates the specified directory if it doesn't exist yet
    public static void deleteDirectory(String directory) {
        try {
            if (Files.exists(Paths.get(directory.toLowerCase()))) {
                Files.delete(Paths.get(directory.toLowerCase()));
            }
        } catch (IOException ioe) {
            System.out.println("ERROR - Could not delete directory!");
        }
    }

    // Creates the specified file if it doesn't exist yet
    public static void createFile(String filename) {
        try {
            if (!Files.exists(Paths.get(filename.toLowerCase()))) {
                Files.createFile(Paths.get(filename.toLowerCase()));
            }
        } catch (IOException ioe) {
            System.out.println("ERROR - Could not create file!");
        }
    }

    // Creates the specified file if it doesn't exist yet
    public static void deleteFile(String filename) {
        try {
            if (Files.exists(Paths.get(filename.toLowerCase()))) {
                Files.delete(Paths.get(filename.toLowerCase()));
            }
        } catch (IOException ioe) {
            System.out.println("ERROR - Could not delete file!");
        }
    }

    // Checks if the specified file or directory already exists
    public static boolean doesPathExist(String pathName) {
        return Files.exists(Paths.get(pathName.toLowerCase()));
    }

    // Reads (and returns) the contents of the specified file
    public static List<String> readFromFile(String filename) {
        List<String> data = new ArrayList<>();
        try {
            data = Files.readAllLines(Paths.get(filename.toLowerCase()));
        } catch (IOException ioe) {
            System.out.println("ERROR - Could not read from file!");
        }
        return data;
    }

    // Writes the given data list into the specified file
    public static void writeDataToFile(String filename, List<String> data) {
        try {
            Files.write(Paths.get(filename.toLowerCase()), data);
        } catch (IOException ioe) {
            System.out.println("ERROR - Could not write to file!");
        }
    }
}