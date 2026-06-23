package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.Categoria;

public class GerenciadorFinanceiro {
    private List<Transacao> transacoes = new ArrayList<>();
    private Map<Categoria, Double> valorCategoria = new HashMap<>();
}
