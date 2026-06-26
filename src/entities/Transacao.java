package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import enums.Categoria;

public class Transacao {
    /**
     * Representa uma transação financeira genérica, seja receita ou despesa.
     */

    private Categoria categoria;
    private LocalDate data;
    private double valor;
    private String descricao;
    private String id;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Transacao(Categoria categoria, LocalDate data, double valor, String descricao, String id) {
        this.categoria = categoria;
        this.data = data;
        this.valor = valor;
        this.descricao = descricao;
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDate getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "#" + id + " - " + categoria + "\n"
                + descricao + "\n"
                + String.format("%.2f", valor) + " - " + data.format(formatter);
    }

}
