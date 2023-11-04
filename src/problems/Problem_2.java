package problems;

import structures.plagiarism.PlagiarismChecker;

import java.io.IOException;

public class Problem_2 {

    private static final String FILE_WOULD_BE_VERIFIED_PATH = "src/reports/problem_2/solution/PlagedFileLoremIpsum.txt";

    public static void main(String[] args) throws IOException {

        System.out.println(PlagiarismChecker.RBTreeChecker(FILE_WOULD_BE_VERIFIED_PATH, 10));
    }
}
