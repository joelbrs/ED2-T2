package structures.auxiliars;

import structures.enums.PlagiarismEnum;

/**
 * Classe de Controle de Plágios, contendo: Se é plágio ou não, o trecho de plágio e o nome do documento que foi plageado
 *
 * */
public class PlagedFile {
    private PlagiarismEnum plagiarismEnum = PlagiarismEnum.NOT_PLAGIARISM;
    private String plagiarismSnippet;
    private String plagiarismDocumentName;
    private Long executionTime;

    public PlagedFile() {}

    public PlagedFile(Long executionTime) {
        this.executionTime = executionTime;
    }

    public PlagedFile(PlagiarismEnum plagiarismEnum, String plagiarismSnippet, String plagiarismDocumentName, Long executionTime) {
        this.plagiarismEnum = plagiarismEnum;
        this.plagiarismSnippet = plagiarismSnippet;
        this.plagiarismDocumentName = plagiarismDocumentName;
        this.executionTime = executionTime;
    }

    @Override
    public String toString() {
        if (plagiarismEnum.equals(PlagiarismEnum.PLAGIARISM)) {
            return   "Plágio: " + plagiarismEnum + '\n' +
                     "Trecho de Plágio: " + plagiarismSnippet + ", \n" +
                     "Nome do Arquivo Plageado: " + plagiarismDocumentName + ", \n" +
                     "Tempo de Execução (em milissegundos): " + executionTime + "ms";
        }
        return "Plágio: " + plagiarismEnum + ", \n" +
                "Tempo de Execução (em milissegundos): " + executionTime + "ms";
    }
}
