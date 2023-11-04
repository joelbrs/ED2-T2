package problems;

import structures.plagiarism.PlagiarismChecker;
import utils.ReportsGenerator;

import java.io.IOException;

public class Problem_2 {

    private static final String FILE_WOULD_BE_VERIFIED_PATH = "src/reports/problem_2/solution/PlagedFileLoremIpsum.txt";

    public static void main(String[] args) throws IOException {

        ReportsGenerator.generate(PlagiarismChecker.RBTreeChecker(FILE_WOULD_BE_VERIFIED_PATH, 5).toString(), "Verificador de Plagio 1: ", ReportsGenerator.PROBLEM_2_REPORTS_PATH);
    }
}
