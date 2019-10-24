package airline.exceptions;

public class PlaneParseException extends Exception {
    private String message;

    public PlaneParseException(String message) {
        super();
        this.message = message;
    }

    public PlaneParseException() {
        super();
        message = "Exception occurred while plane object parsing from String";
    }

    public String getCustomMessage() {
        return message;
    }

    @Override
    public String toString() {
        if (message != null) {
            return "Exception occurred while plane object parsing from String, with message: " + message;
        } else {
            return "Exception occurred while plane object parsing from String";
        }
    }
}
