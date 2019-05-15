package exceptions;

public enum ErrorCode {
    ENTERED_NOT_INTEGER(1, "Entered not integer"),
    ENTERED_INTEGER_OUTOFRANGE(2, "Entered integer out of range"),
    CONTACT_NOT_SAVED (3,"Failed to save contact"),
    ENTERED_INTEGER_MARRIED(4, "You need to enter only 'Y' or 'N' to the question of marriage"),
    ;

    private final int codeId;
    private final String message;

    ErrorCode(int codeId, String message) {
        this.codeId = codeId;
        this.message = message;
    }

    public int getCodeId() {
        return codeId;
    }

    public String getMessage() {
        return message;
    }
    public String getMessageWithCode() {
        return "[Error:"+codeId+"] "+message+"!";
    }
}
