package ao.agt.common.exception;

public class ErrorHandler {

    public String toMessage(Exception exception) {
        if (exception instanceof BusinessException) {
            return exception.getMessage();
        }
        return "Unexpected error";
    }
}
