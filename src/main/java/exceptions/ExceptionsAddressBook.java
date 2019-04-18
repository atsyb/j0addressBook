package exceptions;

public class ExceptionsAddressBook extends Exception {
    private int errorId;
    private ErrorCode errorCode;

    public ExceptionsAddressBook() {
        super();
    }

    public ExceptionsAddressBook(String message) {
        super(message);
    }

    public ExceptionsAddressBook(int errorId, String message) {
        super(message);
        this.errorId = errorId;
    }

    public ExceptionsAddressBook(ErrorCode errorCode) {
        this.errorCode = errorCode;

    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public int getErrorId() {
        return errorId;
    }
}
