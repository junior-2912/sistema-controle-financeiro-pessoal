package exceptions;

public class OperacaoArquivoException extends RuntimeException {
    /**
     * Indica uma falha ao realizar operações de arquivo para o relatório.
     */
    public OperacaoArquivoException(String message) {
        super(message);
    }
}
