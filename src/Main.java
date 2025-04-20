import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       try {
            ConsultaApi consultaApi = new ConsultaApi();
            Menu menu = new Menu();
            Scanner scanner = new Scanner(System.in);

            String moedaOrigem = menu.selecionarMoeda(scanner, "de origem");
            if (moedaOrigem == null) {
                System.out.println("Operação cancelada.");
                return;
            }

            String moedaDestino = menu.selecionarMoeda(scanner, "de destino");
            if (moedaDestino == null) {
                System.out.println("Operação cancelada.");
                return;
            }

            String valor = menu.solicitarValor(scanner).replace(",", ".");

            System.out.printf("\nVocê quer converter %s de %s para %s\n", valor, moedaOrigem, moedaDestino);

            ResponseConvertion consulta = consultaApi.executeConvertion(moedaOrigem, moedaDestino, valor);

            System.out.println(
                    "\nO valor " + valor + " [" + moedaOrigem + "]" + " corresponde ao valor final de =>> " +
                            consulta.conversion_result() + " [" + moedaDestino + "]");

            FilesGenerate generate = new FilesGenerate();
            generate.saveJson(consulta);
        } catch (RuntimeException | IOException e){
            System.out.println(e.getMessage());
            System.out.println("Aplicação Finalizada");
        }
    }
}