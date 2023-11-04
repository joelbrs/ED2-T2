package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportsGenerator {

    public static final String PROBLEM_1_REPORTS_PATH = "src/reports/problem_1/";
    public static final String PROBLEM_2_REPORTS_PATH = "src/reports/problem_2/";

    public static void generate(String text, String name, String path) throws IOException {
        File file = new File(path + name + ".txt");

        if(!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(text);
        bufferedWriter.close();
    }
}
