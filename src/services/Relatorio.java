package services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import entities.Transacao;
import exceptions.OperacaoArquivoException;

public class Relatorio {

    public static void exportarRelatorioTransacao(Transacao transacao) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter("c:\\windows\\temp\\transacoes.csv", true))) {
            bw.write(transacao.getId());
            bw.write(";");
            bw.write(transacao.getCategoria().toString());
            bw.write(";");
            bw.write(Double.toString(transacao.getValor()));
            bw.write(";");
            bw.write(String.valueOf(transacao.getData()));
            bw.write(";");
            bw.newLine();

        } catch (IOException e) {
            throw new OperacaoArquivoException(e.getMessage());
        }

    }

}
