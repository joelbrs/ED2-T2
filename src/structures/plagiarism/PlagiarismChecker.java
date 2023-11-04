package structures.plagiarism;

import structures.auxiliars.FileContent;
import structures.auxiliars.PlagedFile;
import structures.enums.PlagiarismEnum;
import structures.trees.RedBlack.RBTree;

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
    public static PlagedFile RBTreeChecker(String path, Integer m) throws IOException {
        System.out.println(LoadFiles(path));

        return null;
    }

    /**
     * LoadFiles
     *
     * @param directoryPath representa o caminho do diretorio de arquivos que sera lido
     * @return uma lista de arquivos lidos, com o seus nomes e seus conteudos
     * */
    private static List<FileContent> LoadFiles(String directoryPath) throws IOException {
        File paste = new File(directoryPath);
        File[] files = paste.listFiles();
        List<FileContent> filesContent = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                filesContent.add(LoadFile(file.getPath()));
            }
            return filesContent;
        }
        throw new NullPointerException("Diretorio Vazio!");
    }

    /**
     * LoadFile
     *
     * @param filePath representa o caminho do arquivo que sera lido
     * @return o arquivo lido, o seu nome e seu conteudo
     * */
    private static FileContent LoadFile(String filePath) throws IOException {
        File paste = new File(filePath);

        if (!paste.exists()) {
            throw new IOException("Esse diretório não existe!");
        }

        if (paste.length() <= 0) {
            throw new IOException("O diretório não possui nenhum arquivo para leitura");
        }

        FileInputStream inputStream = new FileInputStream(paste.getAbsoluteFile());
        Scanner sc = new Scanner(inputStream);

        FileContent f = new FileContent(paste.getAbsoluteFile().getName());
        while (sc.hasNextLine()) {
            f.addContent(sc.nextLine());
        }

        sc.close();
        inputStream.close();
        return f;
    }
}
