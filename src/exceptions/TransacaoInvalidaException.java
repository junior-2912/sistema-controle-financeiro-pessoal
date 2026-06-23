package exceptions;

public class TransacaoInvalidaException extends RuntimeException{
    public TransacaoInvalidaException(String msg) {
        super(msg);
    }
}
