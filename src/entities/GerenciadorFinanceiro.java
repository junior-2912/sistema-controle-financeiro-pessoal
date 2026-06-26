package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.Categoria;
import exceptions.TransacaoInvalidaException;
import services.Relatorio;

public class GerenciadorFinanceiro {
    /**
     * Armazena todas as transações registradas no sistema.
     */
    private List<Transacao> transacoes = new ArrayList<>();

    /**
     * Mantém o somatório acumulado de valores por categoria.
     */
    private Map<Categoria, Double> valorCategoria = new HashMap<>();

    /**
     * Verifica se já existe uma transação com o identificador informado.
     */
    public boolean existeId(String id) {
        Transacao transacao = transacoes.stream().filter(t -> t.getId().equals(id)).findAny().orElse(null);
        return transacao != null;
    }

    /**
     * Registra uma nova receita e atualiza o total por categoria.
     */
    public boolean registrarReceita(String categoria, LocalDate data, double valor, String descricao, String id) {
        if (valor < 0) {
            throw new TransacaoInvalidaException("O valor nao pode ser negativo");
        }
        if (data.isAfter(LocalDate.now())) {
            throw new TransacaoInvalidaException("Data invalida!");
        }

        Receita receita = new Receita(Categoria.valueOf(categoria.toUpperCase()), data, valor, descricao, id);

        if (!existeId(receita.getId())) {
            transacoes.add(receita);
            if (!valorCategoria.containsKey(receita.getCategoria())) {
                valorCategoria.put(receita.getCategoria(), receita.getValor());
            } else {
                valorCategoria.put(receita.getCategoria(),
                        valorCategoria.get(receita.getCategoria()) + receita.getValor());
            }
            Relatorio.exportarRelatorioTransacao(receita);
            return true;
        }
        return false;
    }

    /**
     * Registra uma nova despesa e atualiza o total por categoria.
     */
    public boolean registrarDespesa(String categoria, LocalDate data, double valor, String descricao, String id) {

        if (valor < 0) {
            throw new TransacaoInvalidaException("O valor nao pode ser negativo");
        }

        if (data.isAfter(LocalDate.now())) {
            throw new TransacaoInvalidaException("Data invalida!");
        }

        Despesa despesa = new Despesa(Categoria.valueOf(categoria.toUpperCase()), data, valor, descricao, id);

        if (!existeId(despesa.getId())) {
            transacoes.add(despesa);
            if (valorCategoria.containsKey(despesa.getCategoria())) {
                valorCategoria.put(despesa.getCategoria(),
                        valorCategoria.get(despesa.getCategoria()) + despesa.getValor());
            } else {
                valorCategoria.put(despesa.getCategoria(), despesa.getValor());
            }
            Relatorio.exportarRelatorioTransacao(despesa);
            return true;
        }
        return false;
    }

    public double totalGasto() {
        // Filtro pra valores da categoria salario, pois salario nao é um gasto e sim um
        // ganho.
        return transacoes.stream()
                .filter(t -> t.getCategoria() != Categoria.SALARIO)
                .map(Transacao::getValor)
                .reduce(0.0, Double::sum);
    }

    public double totalCategoria(Categoria categoria) {
        if (valorCategoria.get(categoria) != null)
            return valorCategoria.get(categoria);
        return 0.0;
    }

    public double somarGastos(Categoria[] categorias) {
        double soma = 0;
        for (Categoria categoria : categorias) {
            soma += valorCategoria.get(categoria);
        }
        return soma;
    }

    public List<Map.Entry<Categoria, Double>> categoriasMaisGastos() {
        return valorCategoria.entrySet().stream()
                .sorted(Comparator.comparing((Map.Entry<Categoria, Double> entry) -> entry.getValue()).reversed())
                .limit(3).toList();
    }

}
