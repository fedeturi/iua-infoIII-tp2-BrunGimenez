package ar.edu.iua.info3;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {

        CSVReaderCOVIDLinkedList csvReader = new CSVReaderCOVIDLinkedList();

        csvReader.readCSV();
        csvReader.p_casos();
        csvReader.p_muertes();

        Scanner entrada = new Scanner(System.in);
        String op;
        boolean salir = false;

        System.out.println("");
        System.out.println("========================================");
        System.out.println("    -estad");
        System.out.println("    -p_casos");
        System.out.println("    -p_muertes");
        System.out.println("    -casos_edad");
        System.out.println("    -casos_cui");
        System.out.println("    -exit");
        System.out.println("========================================");
        while (!salir) {
            System.out.println("");
            System.out.println("Ingrese una Opcion: ");
            op = entrada.nextLine();
            switch (op) {
                case "-estad":
                    System.out.println("========================================");
                    System.out.println("ESTADISTICAS GENERALES");
                    System.out.println("----------------------------------------");
                    csvReader.imprimirCasosLista();
                    csvReader.printStats();
                    break;
                case "-p_casos":
                    System.out.println("----------------------------------------");
                    System.out.println(" CASOS INFECTADOS POR PROVINCIA");
                    System.out.println("----------------------------------------");
                    System.out.println("Cantidad: (Enter para traer todas)");
                    Scanner tecla = new Scanner(System.in);
                    String n = tecla.nextLine();
                    if(n == "" ){
                        csvReader.imprimirCasosInfectados(23);
                        break;
                    }else{
                        csvReader.imprimirCasosInfectados(Integer.parseInt(n));
                        break;
                    }
                case "-p_muertes":
                    System.out.println("----------------------------------------");
                    System.out.println(" CASOS INFECTADOS POR PROVINCIA");
                    System.out.println("----------------------------------------");
                    System.out.println("Cantidad: (Enter para traer todas)");
                    Scanner teclam = new Scanner(System.in);
                    String nm = teclam.nextLine();
                    if(nm == "" ){
                        csvReader.imprimirCasosMuertes(24);
                        break;
                    }else{
                        csvReader.imprimirCasosMuertes(Integer.parseInt(nm));
                        break;
                    }
                case "-casos_edad":
                    System.out.println("----------------------------------------");
                    System.out.println(" CASOS EDAD");
                    System.out.println("----------------------------------------");
                    System.out.println("Años/Meses: (Enter para traer Años)");
                    Scanner teclanie = new Scanner(System.in);
                    String nnie = teclanie.nextLine();
                    if(nnie == "" ){
                        csvReader.casos_edad("Años");
                        break;
                    }else{
                        csvReader.casos_edad(nnie);
                        break;
                    }
                case "-casos_cui":
                    System.out.println("----------------------------------------");
                    System.out.println(" CASOS CUIDADO INTENSIVO");
                    System.out.println("----------------------------------------");
                    System.out.println("A partir de (yyyy-mm-dd): (Enter para traer todas)");
                    Scanner teclacui = new Scanner(System.in);
                    String ncui = teclacui.nextLine();
                    if(ncui == "" ){
                        csvReader.casos_cui("0000-00-00");
                        break;
                    }else{
                        csvReader.casos_cui(ncui);
                        break;
                    }
                case "-exit":
                    salir = true;
                    break;
                case "-help":
                    System.out.println("");
                    System.out.println("========================================");
                    System.out.println("    -estad");
                    System.out.println("    -p_casos");
                    System.out.println("    -p_muertes");
                    System.out.println("    -casos_edad");
                    System.out.println("    -casos_cui");
                    System.out.println("    -exit");
                    System.out.println("========================================");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    System.out.println("-help comando para ayuda");

            }
        }


    }


}
