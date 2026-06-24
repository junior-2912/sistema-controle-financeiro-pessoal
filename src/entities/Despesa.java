package entities;

import java.time.LocalDate;

import enums.Categoria;

public class Despesa extends Transacao {
    public Despesa(Categoria categoria, LocalDate data, double valor, String descricao, String id) {
        super(categoria, data, valor, descricao, id);
    }
}
