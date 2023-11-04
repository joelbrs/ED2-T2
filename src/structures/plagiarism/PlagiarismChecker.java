package structures.plagiarism;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlagiarismChecker {

    private final static String ORIGINAL_FILES_PATH = "src/reports/problem_2/problem/";

    /**
     * RBTreeChecker
     *
     * Implementação de Verificador de Plágio através de Árvore Rubro-Negra
     *
     *
     * @param path representa o caminho dos arquivos que o usuario quer verificar o plagio
     * @param m representa a quantidade de palavras iguais consecutivas que sera utilizado para verificacao de plagio
     * @return PlagedFile representa uma estrutura de arquivo possivelmente plageado, onde possui um booleano para tal e o trecho de plagio
     * */
    public static void RBTreeChecker(String path, Integer m) throws IOException {
        LoadFile(ORIGINAL_FILES_PATH).forEach(System.out::println);
    }

    private static List<String> LoadFile(String path) throws IOException {
        File paste = new File(path);
        File[] files = paste.listFiles();

        if (paste.exists()) {
            if (paste.length() > 0) {
                for (File file : files) {
                    if (file.isFile()) {
                        FileInputStream inputStream = new FileInputStream(file);
                        Scanner scanner = new Scanner(inputStream);
                        List<String> texts = new ArrayList<>();

                        System.out.println("Reading File: " + file.getName());
                        while (scanner.hasNextLine()) {
                            texts.add(scanner.nextLine());
                        }

                        scanner.close();
                        inputStream.close();
                        return texts;
                    }
                }
            }
            throw new IOException("O diretório não possui nenhum arquivo para leitura");
        }
        throw new IOException("Esse diretório não existe!");
    }
}
