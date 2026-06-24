package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.Categoria;

public class GerenciadorFinanceiro {
    private List<Transacao> transacoes = new ArrayList<>();
    private Map<Categoria, Double> valorCategoria = new HashMap<>();

    public boolean existeId(String id) {
        Transacao transacao = transacoes.stream().filter(t -> t.getId().equals(id)).findAny().orElse(null);
        return transacao != null;
    }

    public boolean registrarReceita(Receita receita) {
        if (!existeId(receita.getId())) {
            transacoes.add(receita);
            if (!valorCategoria.containsKey(receita.getCategoria())) {
                valorCategoria.put(receita.getCategoria(), receita.getValor());
            } else {
                valorCategoria.put(receita.getCategoria(),
                        valorCategoria.get(receita.getCategoria()) + receita.getValor());
            }
            return true;
        }
        return false;
    }

    public boolean registrarDespesa(Despesa despesa) {
        if (!existeId(despesa.getId())) {
            transacoes.add(despesa);
            if (valorCategoria.containsKey(despesa.getCategoria())) {
                valorCategoria.put(despesa.getCategoria(),
                        valorCategoria.get(despesa.getCategoria()) + despesa.getValor());
            } else {
                valorCategoria.put(despesa.getCategoria(), despesa.getValor());
            }
            return true;
        }
        return false;
    }

    public double totalGasto() {
        //Filtro pra valores da categoria salario, pois salario nao é um gasto e sim um ganho.
        return transacoes.stream()
                .filter(t -> t.getCategoria() != Categoria.SALARIO)
                .map(Transacao::getValor)
                .reduce(0.0, Double::sum);
    }

    public double totalCategoria(Categoria categoria) {
        return valorCategoria.get(categoria);
    }

}
