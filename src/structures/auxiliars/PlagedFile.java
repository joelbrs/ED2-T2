package structures.auxiliars;

import structures.enums.PlagiarismEnum;

public class PlagedFile {
    private PlagiarismEnum plagiarismEnum = PlagiarismEnum.NOT_PLAGIARISM;
    private String plagiarismSnippet;
    private String plagiarismDocumentName;

    public PlagedFile() {}

    public PlagedFile(PlagiarismEnum plagiarismEnum, String plagiarismSnippet, String plagiarismDocumentName) {
        this.plagiarismEnum = plagiarismEnum;
        this.plagiarismSnippet = plagiarismSnippet;
        this.plagiarismDocumentName = plagiarismDocumentName;
    }

    public PlagiarismEnum getPlagiarismEnum() {
        return plagiarismEnum;
    }

    public void setPlagiarismEnum(PlagiarismEnum plagiarismEnum) {
        this.plagiarismEnum = plagiarismEnum;
    }

    public String getPlagiarismSnippet() {
        return plagiarismSnippet;
    }

    public void setPlagiarismSnippet(String plagiarismSnippet) {
        this.plagiarismSnippet = plagiarismSnippet;
    }

    public String getPlagiarismDocumentName() {
        return plagiarismDocumentName;
    }

    public void setPlagiarismDocumentName(String plagiarismDocumentName) {
        this.plagiarismDocumentName = plagiarismDocumentName;
    }

    @Override
    public String toString() {
        return "PlagedFile: {" +
                "Plágio: " + plagiarismEnum +
                ", Trecho de Plágio: '" + plagiarismSnippet + '\'' +
                ", Nome do Arquivo Plageado: '" + plagiarismDocumentName + '\'' +
                '}';
    }
}
