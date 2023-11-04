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

    private final static String REFERENCES_FILES_PATH = "src/reports/problem_2/problem/";

    /**
     * RBTreeChecker
     *
     * Implementação de Verificador de Plágio através de Árvore Rubro-Negra
     *
     *
     * @param path representa o caminho dos arquivos que o usuario quer verificar o plagio
     * @param m representa a quantidade de palavras consecutivas a serem levadas em consideração para classificação de plágio
     * @return PlagedFile representa uma estrutura de arquivo possivelmente plageado, onde possui um booleano para tal e o trecho de plagio
     * */
    public static PlagedFile RBTreeChecker(String path, Integer m) throws IOException {
        // Carregando os Arquivos de Referencia
        List<FileContent> referenceFiles = LoadFiles(REFERENCES_FILES_PATH);

        // Carregando o Arquivo do Usuario
        FileContent userFile = LoadFile(path);

        /*
         * Criamos uma lista de Árvores Rubro-Negras em que, cada uma, contém, os conteúdos dos Arquivos de Referência
         *
         * Em seguida, adicionamos esses conteúdos às devidas árvores antes de inserir na lista
         * */
        List<RBTree<String>> referencesTrees = new ArrayList<>();
        for (FileContent fc : referenceFiles) {
            RBTree<String> tree = new RBTree<>();

            for (int i = 0; i < fc.getContent().size() - m; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < m; j++) {
                    sb.append(fc.getContent().get(i + j)).append(" ");
                }
                tree.insert(sb.toString().trim());
            }
            referencesTrees.add(tree);
        }

        //Percorrendo a lista de conteúdos do Arquivo Verificado
        for (int i = 0; i < userFile.getContent().size() - m; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < m; j++) {
                sb.append(userFile.getContent().get(i + j)).append(" ");
            }
            String snippet = sb.toString().trim();

            for (int j = 0; j < referencesTrees.size(); j++) {
                RBTree<String> tree = referencesTrees.get(j);

                if (!snippet.isEmpty() && tree.contains(snippet)) {
                    return new PlagedFile(PlagiarismEnum.PLAGIARISM, snippet, referenceFiles.get(j).getName());
                }
            }
        }

       return new PlagedFile();
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
     * @return o arquivo lido, o seu nome e seu conteudo em formato de Lista com cada palavra sepadara
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
        Scanner sc = new Scanner(inputStream).useDelimiter("\\b+");

        FileContent file = new FileContent(paste.getAbsoluteFile().getName());
        while (sc.hasNextLine()) {
            file.addContent(sc.next());
        }

        sc.close();
        inputStream.close();
        return file;
    }
}
