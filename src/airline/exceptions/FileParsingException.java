package airline.exceptions;

public class FileParsingException extends Exception {
    private String message;

    public FileParsingException(String message) {
        super();
        this.message = message;
    }

    FileParsingException() {
        super();
        message = "Exception occurred while parsing Plane objects from file";
    }
    public String getCustomMessage(){
        return message;
    }
    @Override
    public String toString() {
        if (message == null) {
            return "Exception occurred while parsing Plane objects from file";
        } else {
            return "Exception occurred while parsing Plane objects from file, with message: " + message;
        }
    }
}
