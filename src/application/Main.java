package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import entities.Despesa;
import entities.GerenciadorFinanceiro;
import entities.Receita;
import enums.Categoria;
import exceptions.TransacaoInvalidaException;
import services.Relatorio;

public class Main {
    // Aplicação pra registrar receitas e despesas e gerar relatórios.
    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in)) {
            GerenciadorFinanceiro gf = new GerenciadorFinanceiro();
            // Indice de escolha do menu.
            int indice;
            while (true) {
                System.out.println("0 - Sair do programa");
                System.out.println("1 - Registrar receita");
                System.out.println("2 - Registrar despesa");
                System.out.println("3 - Total gasto");
                System.out.println("4 - Despesas por categoria");
                System.out.println("5 - Somar gastos");
                System.out.println("6 - Top 3 categorias mais gastas");

                System.out.println("Digite um numero: ");
                indice = entrada.nextInt();
                if (indice == 0)
                    break;

                switch (indice) {
                    case 1 -> {
                        entrada.nextLine();
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

                        Receita receita = new Receita(Categoria.valueOf(categoria.toUpperCase()), data, valor,
                                descricao, id);

                        if (!gf.registrarReceita(receita)) {

                            throw new TransacaoInvalidaException("Ja existe uma transacao com esse ID!");
                        } else {
                            System.out.println("Receita registrada!");
                            Relatorio.exportarRelatorioTransacao(receita);
                        }
                    }
                    case 2 -> {
                        entrada.nextLine();
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

                        Despesa despesa = new Despesa(Categoria.valueOf(categoria), data, valor, descricao, id);

                        if (!gf.registrarDespesa(despesa)) {
                            throw new TransacaoInvalidaException("Despesa com mesmo id ja existente!");
                        } else {
                            System.out.println("Despesa registrada!");
                            Relatorio.exportarRelatorioTransacao(despesa);
                        }
                    }
                    case 3 -> {
                        System.out.println("-Total gasto-");
                        System.out.println(String.format("%.2f", gf.totalGasto()));
                    }
                    case 4 -> {
                        System.out.println("-Despesas por categoria-");
                        entrada.nextLine();
                        System.out.print("Digite a categoria que será relatada: ");
                        String categoria = entrada.nextLine();
                        System.out.println(
                                String.format("%.2f", gf.totalCategoria(Categoria.valueOf(categoria.toUpperCase()))));
                    }
                }
            }

        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        } catch (TransacaoInvalidaException e) {
            e.getMessage();
        }
    }
}
