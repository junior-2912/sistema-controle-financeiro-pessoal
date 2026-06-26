package exceptions;

public class TransacaoInvalidaException extends RuntimeException {
    /**
     * Indica que uma transação foi informada com dados inválidos.
     */
    public TransacaoInvalidaException(String msg) {
        super(msg);
    }
}
