package ar.edu.iua.info3;

import ar.edu.iua.info3.CSVReaderCOVID;

public class Main {

    public static void main(String[] args) {
        CSVReaderCOVID csvReader = new CSVReaderCOVID();

        int cantMuestras = csvReader.readCSV();

        System.out.println("Cantidad de muestras: " + cantMuestras);
    }
}
