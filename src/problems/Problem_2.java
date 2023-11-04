package problems;

import structures.plagiarism.PlagiarismChecker;
import utils.ReportsGenerator;

import java.io.IOException;

public class Problem_2 {

    private static final String PLAGED_FILE_PATH = "src/reports/problem_2/solution/PlagedFileLoremIpsum.txt";
    private static final String EMPTY_FILE__PATH = "src/reports/problem_2/solution/EmptyFile.txt";
    private static final String NOT_PLAGED_FILE_PATH = "src/reports/problem_2/solution/EmptyFile.txt";

    public static void main(String[] args) throws IOException {

        ReportsGenerator.generate(PlagiarismChecker.RBTreeChecker(PLAGED_FILE_PATH).toString(), "Verificador de Plagio (Arquivo Plageado)", ReportsGenerator.PROBLEM_2_REPORTS_PATH);
        ReportsGenerator.generate(PlagiarismChecker.RBTreeChecker(EMPTY_FILE__PATH).toString(), "Verificador de Plagio (Arquivo Vazio)", ReportsGenerator.PROBLEM_2_REPORTS_PATH);
        ReportsGenerator.generate(PlagiarismChecker.RBTreeChecker(NOT_PLAGED_FILE_PATH).toString(), "Verificador de Plagio (Arquivo Nao Plageado)", ReportsGenerator.PROBLEM_2_REPORTS_PATH);
    }
}
