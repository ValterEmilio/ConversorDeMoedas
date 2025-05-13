import br.com.valter.ConvesorDeMoedas.Modelos.ConversorDeMoedas;
import br.com.valter.ConvesorDeMoedas.Modelos.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        List<String> historico = carregarHistorico(); // Carrega o histórico do arquivo

        System.out.println("Conversor de Moeda:");

        boolean sair = false;
        while (!sair) {
            menu.mostrarMenu();

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    converterMoedas(scanner, "USD", "ARS", historico);
                    break;
                case 2:
                    converterMoedas(scanner, "ARS", "USD", historico);
                    break;
                case 3:
                    converterMoedas(scanner, "BOB", "BRL", historico);
                    break;
                case 4:
                    converterMoedas(scanner, "BRL", "BOB", historico);
                    break;
                case 5:
                    converterMoedas(scanner, "CLP", "COP", historico);
                    break;
                case 6:
                    converterMoedas(scanner, "COP", "CLP", historico);
                    break;

                case 7:
                    System.out.println("Histórico de conversões:");
                    if (historico.isEmpty()) {
                        System.out.println("Nenhuma conversão realizada ainda.");
                    } else {
                        for (String entrada : historico) {
                            System.out.println(entrada);
                        }
                    }
                    break;
                case 8:
                    sair = true;
                    System.out.println("Encerrando a aplicação, por favor aguarde...");
                    try {
                        Thread.sleep(3000); // Espera 3 segundos antes de encerrar
                    } catch (InterruptedException e) {
                        System.out.println("Interrupção inesperada.");
                    }
                    System.out.println("Aplicação encerrada.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void converterMoedas(Scanner scanner, String moedaOrigem, String moedaDestino, List<String> historico) {
        System.out.println("Digite o valor que deseja converter:");
        double valor = scanner.nextDouble();
        ConversorDeMoedas conversor = new ConversorDeMoedas();

        try {
            double valorConvertido = conversor.converteMoeda(valor, moedaOrigem, moedaDestino);
            System.out.println("=========================================================");
            System.out.printf("%s%.2f %s equivale a %s%.2f %s%n",
                    getSimboloMoeda(moedaOrigem), valor, moedaOrigem,
                    getSimboloMoeda(moedaDestino), valorConvertido, moedaDestino);

            String entrada = String.format("%s%.2f %s -> %s%.2f %s",
                    getSimboloMoeda(moedaOrigem), valor, moedaOrigem,
                    getSimboloMoeda(moedaDestino), valorConvertido, moedaDestino);

            historico.add(entrada);
            salvarNoArquivo(entrada);

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao converter moeda: " + e.getMessage());
        }
    }

    public static String getSimboloMoeda(String codigoMoeda) {
        return switch (codigoMoeda) {
            case "USD" -> "$";
            case "ARS" -> "ARS$";
            case "BOB" -> "$b";
            case "COP" -> "COP$";
            case "CLP" -> "CLP$";
            case "BRL" -> "R$";
            default -> "";
        };
    }

    public static void salvarNoArquivo(String entrada) {
        try (FileWriter fw = new FileWriter("historico_conversoes.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(entrada);
        } catch (IOException e) {
            System.out.println("Erro ao salvar no arquivo: " + e.getMessage());
        }
    }

    public static List<String> carregarHistorico() {
        List<String> historico = new ArrayList<>();
        File arquivo = new File("historico_conversoes.txt");

        if (arquivo.exists()) {
            try (Scanner leitor = new Scanner(arquivo)) {
                while (leitor.hasNextLine()) {
                    historico.add(leitor.nextLine());
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o histórico do arquivo: " + e.getMessage());
            }
        }

        return historico;
    }
}
