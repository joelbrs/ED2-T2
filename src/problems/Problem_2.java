package problems;

import structures.checkers.PlagiarismChecker;
import utils.ReportsGenerator;

public class Problem_2 {
    /**
     * Você foi contratado para desenvolver um verificador de plágio online, que permite que
     * usuários enviem trabalhos escritos e verifiquem se existem copias das seções inteiras de um
     * conjunto, D, de documentos escritos que que você carregou no programa. Você deve
     * carregar qualquer documento, d, e separá-lo em uma sequência de suas n palavras em sua
     * ordem dada (com duplicatas incluídas) em tempo O (n). É considerado um ato de plágio se
     * for utilizada uma sequência de m palavras (em sua ordem) de um documento em D, onde m
     * é um parâmetro definido pelo usuário. Implemente um programa pelo qual você pode ler um
     * documento, d, de n palavras, e testar se ele contém algum plágio. Seu sistema deve
     * processar o conjunto de documentos em D no tempo esperado proporcional ao seu
     * comprimento total, o que é feito apenas uma vez. Seu programa deve detectar o plágio em
     * tempo inferior a O (nm)!. Seu programa deverá apresentar as ocorrências do plágio
     * (documento e parágrafo/frase).
     *
     * */

    private static final String PLAGED_FILE_PATH = "src/reports/problem_2/files/PlagedFileLoremIpsum.txt";
    private static final String EMPTY_FILE_PATH = "src/reports/problem_2/files/EmptyFile.txt";
    private static final String NOT_PLAGED_FILE_PATH = "src/reports/problem_2/files/DifferenteFileLoremIpsum.txt";

    public static void main(String[] args) throws Exception {

        // Resultados da Implementação com HashTable
        ReportsGenerator.generate(PlagiarismChecker.MultiMapChecker(PLAGED_FILE_PATH, 40).toString(), "Verificador de Plágio HashTable (Arquivo Plageado)", ReportsGenerator.PROBLEM_2_REPORTS_PATH);
        ReportsGenerator.generate(PlagiarismChecker.MultiMapChecker(EMPTY_FILE_PATH, 9).toString(), "Verificador de Plágio HashTable (Arquivo Vazio)", ReportsGenerator.PROBLEM_2_REPORTS_PATH);
        ReportsGenerator.generate(PlagiarismChecker.MultiMapChecker(NOT_PLAGED_FILE_PATH, 900).toString(), "Verificador de Plágio HashTable (Arquivo Não Plageado)", ReportsGenerator.PROBLEM_2_REPORTS_PATH);

        // Resultados da Implementação com Árvore Rubro-Negra
        ReportsGenerator.generate(PlagiarismChecker.RBTreeChecker(PLAGED_FILE_PATH, 40).toString(), "Verificador de Plágio RBTree (Arquivo Plageado)", ReportsGenerator.PROBLEM_2_REPORTS_PATH);
        ReportsGenerator.generate(PlagiarismChecker.RBTreeChecker(EMPTY_FILE_PATH, 9).toString(), "Verificador de Plágio RBTree (Arquivo Vazio)", ReportsGenerator.PROBLEM_2_REPORTS_PATH);
        ReportsGenerator.generate(PlagiarismChecker.RBTreeChecker(NOT_PLAGED_FILE_PATH, 900).toString(), "Verificador de Plágio RBTree (Arquivo Não Plageado)", ReportsGenerator.PROBLEM_2_REPORTS_PATH);
    }
}
