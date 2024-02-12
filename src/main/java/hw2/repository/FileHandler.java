package hw2.repository;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileHandler {

    public final String fileLogs = "src/main/java/hw2/repository/log.txt";

    public void saveInfoInRepo(String text) {
        try (FileWriter writer = new FileWriter(fileLogs, true)) {
            writer.write(text);
            writer.write("\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String loadInfoFromRepo() {
        try {
            return FileUtils.readFileToString(new File("src/main/java/hw1/server/log.txt"),
                    String.valueOf(StandardCharsets.UTF_8));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
