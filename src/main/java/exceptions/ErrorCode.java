package exceptions;

public enum ErrorCode {
    ENTERED_NOT_INTEGER(1, "Entered not integer"),
    ENTERED_INTEGER_OUTOFRANGE(2, "Entered integer out of range"),
    CONTACT_NOT_SAVED(3, "Failed to save contact"),
    ENTERED_INTEGER_MARRIED(4, "You need to enter only 'Y' or 'N' to the question of marriage"),
    SQL_ERROR_INSERT_CONTACT(101, "SQL: Error when inserting contact"),
    SQL_ERROR_SELECT_CONTACT(102, "SQL: Error when selecting contact"),
    SQL_ERROR_UPDATE_CONTACT(103, "SQL: Error when updating contact"),
    SQL_ERROR_DELETE_CONTACT(104, "SQL: Error when deleting contact"),
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
        return "[Error:" + codeId + "] " + message + "!";
    }
}
