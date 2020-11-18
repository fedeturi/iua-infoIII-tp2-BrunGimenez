package ar.edu.iua.info3;

public class Main {

    public static void main(String[] args) {
        CSVReaderCOVIDLinkedList csvReader = new CSVReaderCOVIDLinkedList();

        int cantMuestras = csvReader.readCSV();
    }
}
