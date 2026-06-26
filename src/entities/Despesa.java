package entities;

import java.time.LocalDate;

import enums.Categoria;

public class Despesa extends Transacao {
    /**
     * Representa uma saída financeira negativa no controle pessoal.
     */
    public Despesa(Categoria categoria, LocalDate data, double valor, String descricao, String id) {
        super(categoria, data, valor, descricao, id);
    }
}
