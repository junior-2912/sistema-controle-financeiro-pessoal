package entities;

import java.time.LocalDate;

import enums.Categoria;

public class Receita extends Transacao {
    public Receita(Categoria categoria, LocalDate data, double valor, String descricao, String id) {
        super(categoria, data, valor, descricao, id);
    }
}
