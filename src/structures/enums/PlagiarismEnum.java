package structures.enums;

public enum PlagiarismEnum {

    PLAGIARISM(Boolean.TRUE),
    NOT_PLAGIARISM(Boolean.FALSE);

    private final Boolean code;

    PlagiarismEnum(Boolean code) {
        this.code = code;
    }

    public boolean getCode() {
        return code;
    }
}
