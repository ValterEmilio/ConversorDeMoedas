
import br.com.valter.ConvesorDeMoedas.Modelos.ConversorDeMoedas;
import br.com.valter.ConvesorDeMoedas.Modelos.Menu;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();

        System.out.println("Conversor de Moeda:");

        boolean sair = false;
        while (!sair) {
            menu.mostrarMenu();
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    converterMoedas(scanner, "USD", "ARS");
                    break;
                case 2:
                    converterMoedas(scanner, "ARS", "USD");
                    break;
                case 3:
                    converterMoedas(scanner, "BOB", "BRL");
                    break;
                case 4:
                    converterMoedas(scanner, "BRL", "BOB");
                    break;
                case 5:
                    converterMoedas(scanner, "CLP", "COP");
                    break;
                case 6:
                    converterMoedas(scanner, "COP", "CLP");
                    break;
                case 7:
                    sair = true;
                    System.out.println("Encerrando a aplicação, por favor aguarde...");
                    try {
                        Thread.sleep(3000); // pausa por 3 segundos (3000 milissegundos)
                    } catch (InterruptedException e) {
                        System.out.println("Interrupção inesperada ao encerrar.");
                    }
                    System.out.println("Aplicação encerrada.");
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
            }
        }

    }

    public static void converterMoedas(Scanner scanner, String moedaOrigem, String moedaDestino) {
        System.out.println("Digite o valor que deseja converter:");
        double valor = scanner.nextDouble();
        ConversorDeMoedas Conversor = new ConversorDeMoedas();

        try {
            double valorConvertido = Conversor.converteMoeda(valor, moedaOrigem, moedaDestino);
            System.out.println("=========================================================");
            System.out.printf("(%s%.2f %s) equivale a (%s%.2f %s%n",
                    getSimboloMoeda(moedaOrigem), valor, moedaOrigem,
                    getSimboloMoeda(moedaDestino), valorConvertido, moedaDestino +")");


        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao converter moeda: " + e.getMessage());
        }
    }
    public static String getSimboloMoeda(String codigoMoeda) {
        return switch (codigoMoeda) {
            case "USD" -> "$";
            case "EUR" -> "€";
            case "BRL" -> "R$";
            default -> "";
        };
    }

}