package structures.enums;

public enum RBColorEnum {
    RED(Boolean.TRUE),
    BLACK(Boolean.FALSE);

    private final Boolean code;

    RBColorEnum(Boolean code) {
        this.code = code;
    }

    public boolean getCode() {
        return code;
    }
}
