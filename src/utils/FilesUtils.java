package utils;

import structures.auxiliars.FileContent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilesUtils {
    /**
     * LoadFiles
     *
     * Método responsável por carregar uma lista de arquivos a partir de um diretório especificado
     * A partir dessa leitura, faz a chamada pro LoadFile (abaixo) pegando o camanho específico de cada arquivo
     *
     * @param directoryPath representa o caminho do diretorio de arquivos que sera lido
     * @return uma lista de arquivos lidos, com o seus nomes e seus conteudos
     * */
    public static List<FileContent> LoadFiles(String directoryPath) throws IOException {
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
     * Método responsável por carregar um único arquivo a partir de seu diretório especificado
     *
     * Utilizamos o seguinte fórum de discussão como base para a implementação desse método, mas realizamos algumas mudanças para suprir nossas necessidades
     * Fonte: https://www.guj.com.br/t/manipulacao-de-arquivos-txt-em-java-eclipse/410313/6
     *
     * @param filePath representa o caminho do arquivo que sera lido
     * @return o arquivo lido, o seu nome e seu conteudo em formato de Lista com cada palavra sepadara
     * */
    public static FileContent LoadFile(String filePath) throws IOException {
        File paste = new File(filePath);

        if (!paste.exists()) {
            throw new IOException("Esse diretório não existe!");
        }

        if (paste.length() <= 0) {
            throw new IOException("O diretório não possui nenhum arquivo para leitura");
        }

        /**
         * Escaneando cada palavra do arquivo (utilizando o RegEx para delimitar a partir dos espaços em branco)
         * Fonte: https://cursos.alura.com.br/forum/topico-classe-scanner-delimitador-59273
         * */
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
