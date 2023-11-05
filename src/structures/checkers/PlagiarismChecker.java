package structures.checkers;

import structures.auxiliars.FileContent;
import structures.auxiliars.PlagedFile;
import structures.enums.PlagiarismEnum;
import structures.hashs.MultiMap;
import structures.trees.RedBlack.RBTree;
import utils.FilesUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        Long startTime = System.currentTimeMillis();
        // Carregando os Arquivos de Referencia
        List<FileContent> referenceFiles = FilesUtils.LoadFiles(REFERENCES_FILES_PATH);

        // Carregando o Arquivo do Usuario
        FileContent userFile = FilesUtils.LoadFile(path);

        /*
         * Criamos uma lista de Árvores Rubro-Negras em que, cada uma, contém, os conteúdos dos Arquivos de Referência
         *
         * Em seguida, adicionamos esses conteúdos às devidas árvores antes de inserir na lista
         * */
        List<RBTree<String>> referencesTrees = new ArrayList<>();
        for (FileContent fc : referenceFiles) {
            RBTree<String> tree = new RBTree<>();

            for (int i = 0; i < fc.getContent().size() - m; i++) {

                /**
                 * Utilizamos o StringBuilder para concatenar as Strings e gerar uma sequencia de m palavras
                 *
                 * Fonte: https://www.tabnine.com/code/java/methods/java.lang.StringBuilder/append
                 *
                 * */
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < m; j++) {
                    sb.append(fc.getContent().get(i + j));
                }
                tree.insert(sb.toString().trim());
            }
            referencesTrees.add(tree);
        }

        //Percorrendo a lista de conteúdos do Arquivo Verificado
        for (int i = 0; i < userFile.getContent().size() - m; i++) {

            /**
             * Repetimos a abordagem do String Builder a fim de gerar uma possivel String que sera comparada com que esta presente na Arvore Rubro-Negra
             * */
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < m; j++) {
                sb.append(userFile.getContent().get(i + j));
            }
            String snippet = sb.toString().trim();

            // Iteramos em todas as arvores da Lista e verificamos se existe tal trecho em alguma delas
            for (int j = 0; j < referencesTrees.size(); j++) {
                RBTree<String> tree = referencesTrees.get(j);

                if (!snippet.isEmpty() && tree.contains(snippet)) {
                    return new PlagedFile(PlagiarismEnum.PLAGIARISM, snippet, referenceFiles.get(j).getName(), System.currentTimeMillis() - startTime);
                }
            }
        }

        return new PlagedFile(System.currentTimeMillis() - startTime);
    }

    /**
     * MultiMapChecker
     *
     * Implementação de Verificador de Plágio através de HashTable (no caso, utilizei o MultiMap dinamico desenvolvido p/ o primeiro problema
     *
     * OBS.: Implementacao seguindo a mesma linha que da Arvore Rubro-Negra
     *
     *
     * @param path representa o caminho dos arquivos que o usuario quer verificar o plagio
     * @param m representa a quantidade de palavras consecutivas a serem levadas em consideração para classificação de plágio
     * @return PlagedFile representa uma estrutura de arquivo possivelmente plageado, onde possui um booleano para tal e o trecho de plagio
     * */
    public static PlagedFile MultiMapChecker(String path, Integer m) throws Exception {
        Long startTime = System.currentTimeMillis();

        // Carregando os Arquivos de Referencia
        List<FileContent> referenceFiles = FilesUtils.LoadFiles(REFERENCES_FILES_PATH);

        // Carregando o Arquivo do Usuario
        FileContent userFile = FilesUtils.LoadFile(path);

        List<MultiMap<String, String>> referencesHash = new ArrayList<>();
        for (FileContent fc : referenceFiles) {
            MultiMap<String, String> hash = new MultiMap<>(Boolean.FALSE);

            for (int i = 0; i < fc.getContent().size() - m; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < m; j++) {
                    sb.append(fc.getContent().get(i + j));
                }
                hash.put(fc.getName(), sb.toString().trim());
            }
            referencesHash.add(hash);
        }

        //Percorrendo a lista de conteúdos do Arquivo Verificado
        for (int i = 0; i < userFile.getContent().size() - m; i++) {

            /**
             * Repetimos a abordagem do String Builder a fim de gerar uma possivel String que sera comparada com que esta presente no HashTable
             * */
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < m; j++) {
                sb.append(userFile.getContent().get(i + j));
            }
            String snippet = sb.toString().trim();

            // Iteramos em todos os hashs da Lista e verificamos se existe tal trecho em alguma delas
            for (int j = 0; j < referencesHash.size(); j++) {
                MultiMap<String, String> hash = referencesHash.get(j);

                if (!snippet.isEmpty()) {
                    Object[] arr = hash.findAll(referenceFiles.get(j).getName());
                    for (Object str : arr) {
                        if (str != null && str.equals(snippet)) {
                            return new PlagedFile(PlagiarismEnum.PLAGIARISM, snippet, referenceFiles.get(j).getName(), System.currentTimeMillis() - startTime);
                        }
                    }
                }
            }
        }

        return new PlagedFile(System.currentTimeMillis() - startTime);
    }
}
