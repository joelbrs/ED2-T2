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

    public PlagiarismEnum valueOf(Boolean code) throws Exception {
        for (PlagiarismEnum e : values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        throw new Exception("Enum não encontrado para o código refernciado!");
    }
}
