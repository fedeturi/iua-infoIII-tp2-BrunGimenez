package ar.edu.iua.info3;

import java.io.FileReader;
import java.io.File;
import java.net.URL;

import com.opencsv.CSVReader;


public class CSVReaderCOVID {

    public CSVReaderCOVID() {
    }

    public static void readCSV() {
        CSVReader reader = null;
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
                for (String token : nextLine) {
                    System.out.print(token + "\t");
                }
                System.out.print("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
