package ar.edu.iua.info3;

import java.io.FileReader;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import ar.edu.iua.info3.structures.LinkedList;

import com.opencsv.CSVReader;


public class CSVReaderCOVID {

    public CSVReaderCOVID() {
    }

    public static int readCSV() {
        CSVReader reader = null;
        int cantMuestras = 0;
        int cantInfectados = 0;
        int cantFallecidos = 0;

        try {

            // Instanciar el reader del archivo CSV
            URL url = CSVReaderCOVID.class.getResource("covid.csv");
            File file = new File(url.getPath());

            reader = new CSVReader(new FileReader(file), ',');
            String[] nextLine;

            // Leer linea por linea
            // TODO Cambiar para guardarlo en memorio en vez de imprimirlo
            // TODO y devolverlo con return desde esta funcion
            while ((nextLine = reader.readNext()) != null) {
                LinkedList<String> caso = new LinkedList<>();
                int index = 0;
                for (String token : nextLine) {
                    caso.add(token, index);
                    index ++;
                }

                // Cuenta la cantidad de muestras
                cantMuestras ++;

                // Cuenta la cantidad de infectados
                if (caso.get(20).toLowerCase().equals("confirmado")) {
                    cantInfectados ++;
                }

                // Cuenta la cantidad de fallecidos
                if (caso.get(14).toLowerCase().equals("si")) {
                    cantFallecidos ++;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

        float pctgInfMuestra = cantInfectados * 100 / cantMuestras;
        float pctgFallInf = cantFallecidos * 100 / cantInfectados;

        System.out.println("Cantidad de Muestras: " + cantMuestras);
        System.out.println("Cantidad de Infectados: " + cantInfectados);
        System.out.println("Cantidad de Fallecidos: " + cantFallecidos);
        System.out.println("Tasa Infectados/Muestras: " + pctgInfMuestra + "%");
        System.out.println("Tasa Fallecidos/Infectados: " + pctgFallInf + "%");
        return cantMuestras;
    }

}
