package ar.edu.iua.info3;

import ar.edu.iua.info3.structures.HashTable;

public class Main {

    public static void main(String[] args) throws Exception {
        CSVReaderCOVIDLinkedList csvReader = new CSVReaderCOVIDLinkedList();

        HashTable<String, Integer> estadisticas = csvReader.readCSV();

        // Lee los parametros de la consola e invoca a la funcion correspondiente
        if (args.length == 1) {

            String funct = args[0];

            switch (funct) {
                case "-estad":
                    csvReader.printStats();
                    break;
                case "-p_casos":
                    System.out.println("Casos");
                    break;
                case "-p_muertes":
                    System.out.println("Muertes");
                    break;
                case "-casos_edad":
                    System.out.println("Casos por edad");
                    break;
                case "-casos_cui":
                    System.out.println("Casos cui");
                    break;
                case "-graph":
                    System.out.println("Graficar");
                    break;
            }
        } else if (args.length == 2) {

            String funct = args[0];
            int functParam = Integer.parseInt(args[1]);

            switch (funct) {
                case "-estad":
                    csvReader.printStats();
                    System.out.println(estadisticas.get("Muestras"));
                    break;
                case "-p_casos":
                    System.out.println("Casos " + functParam);
                    break;
                case "-p_muertes":
                    System.out.println("Muertes " + functParam);
                    break;
                case "-casos_edad":
                    System.out.println("Casos por edad " + functParam);
                    break;
                case "-casos_cui":
                    System.out.println("Casos cui" + functParam);
                    break;
                case "-graph":
                    System.out.println("Graficar " + functParam);
            }
        }
    }


}
