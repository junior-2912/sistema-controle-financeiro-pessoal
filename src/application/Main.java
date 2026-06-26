package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import entities.GerenciadorFinanceiro;
import enums.Categoria;
import exceptions.TransacaoInvalidaException;

public class Main {
    /**
     * Ponto de entrada da aplicação.
     * Apresenta um menu interativo para registrar receitas, despesas e exibir
     * relatórios.
     */
    public static void main(String[] args) {
        GerenciadorFinanceiro gf = new GerenciadorFinanceiro();
        try (Scanner entrada = new Scanner(System.in)) {
            while (true) {
                try {
                    System.out.println("0 - Sair do programa");
                    System.out.println("1 - Registrar receita");
                    System.out.println("2 - Registrar despesa");
                    System.out.println("3 - Total gasto");
                    System.out.println("4 - Despesas por categoria");
                    System.out.println("5 - Somar gastos");
                    System.out.println("6 - Top 3 categorias mais gastas");

                    System.out.println("Digite um numero: ");
                    int indice = entrada.nextInt();
                    entrada.nextLine();
                    if (indice == 0) {
                        break;
                    }
                    switch (indice) {
                        case 1 -> {
                            System.out.print("Digite o ID: ");
                            String id = entrada.nextLine();
                            System.out.print("Digite o valor: ");
                            double valor = entrada.nextDouble();
                            entrada.nextLine();
                            System.out.print("Digite a descricao: ");
                            String descricao = entrada.nextLine();
                            System.out.print("Digite a categoria: ");
                            String categoria = entrada.nextLine();
                            System.out.print("Digite a data(dd/mm/aaaa): ");
                            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate data = LocalDate.parse(entrada.nextLine(), fmt);

                            if (!gf.registrarReceita(categoria, data, valor, descricao, id)) {
                                throw new TransacaoInvalidaException("Ja existe uma transacao com esse ID!");
                            } else {
                                System.out.println("Receita registrada!");
                            }
                        }
                        case 2 -> {
                            System.out.print("Digite o ID: ");
                            String id = entrada.nextLine();
                            System.out.print("Digite o valor: ");
                            double valor = entrada.nextDouble();
                            entrada.nextLine();
                            System.out.print("Digite a descricao: ");
                            String descricao = entrada.nextLine();
                            System.out.print("Digite a categoria: ");
                            String categoria = entrada.nextLine();
                            System.out.print("Digite a data(dd/mm/aaaa): ");
                            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate data = LocalDate.parse(entrada.nextLine(), fmt);

                            if (!gf.registrarDespesa(categoria, data, valor, descricao, id)) {
                                throw new TransacaoInvalidaException("Ja existe uma transacao com esse ID!");
                            } else {
                                System.out.println("Despesa registrada!");
                            }
                        }
                        case 3 -> {
                            System.out.println("-Total gasto-");
                            System.out.println(String.format("%.2f", gf.totalGasto()));
                        }
                        case 4 -> {
                            System.out.println("-Despesas por categoria-");
                            System.out.print("Digite a categoria que será relatada: ");
                            String categoria = entrada.nextLine();
                            // Apesar do nome da funcao ser despesas por categoria, SALARIO nao é uma
                            // despesa, mas sim uma categoria de receita.
                            // Tratamento para caso a categoria escolhida seja SALARIO.
                            if (categoria.toUpperCase().equals(Categoria.SALARIO)) {
                                System.out.println(
                                        "Salario é uma categoria de receita, entao os numeros abaixo sao de ganhos e nao de gastos");
                                String.format("%.2f",
                                        gf.totalCategoria(Categoria.valueOf(categoria.toUpperCase())));
                            } else {
                                System.out.println(
                                        "Valor gasto: R$ " + String.format("%.2f",
                                                gf.totalCategoria(Categoria.valueOf(categoria.toUpperCase()))));
                            }
                        }
                        case 5 -> {
                            System.out.println("De quantas categorias voce quer somar os gastos: ");
                            int nCategorias = entrada.nextInt();
                            entrada.nextLine();
                            Categoria[] categorias = new Categoria[nCategorias];
                            for (int i = 0; i < categorias.length; i++) {
                                System.out.println("Digite a categoria a somar gastos: ");
                                categorias[i] = Categoria.valueOf(entrada.nextLine().toUpperCase());
                            }
                            System.out.println("Gastos somados: " + String.format("%.2f", gf.somarGastos(categorias)));
                        }
                        case 6 -> {
                            System.out.println("Top 3 categorias com mais gastos ");
                            System.out.println(gf.categoriasMaisGastos());
                        }
                        default -> System.out.println("Valor invalido!");
                    }
                } catch (DateTimeParseException | TransacaoInvalidaException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}