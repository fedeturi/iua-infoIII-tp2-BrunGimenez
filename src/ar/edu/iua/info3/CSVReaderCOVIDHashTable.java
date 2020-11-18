package ar.edu.iua.info3;

import ar.edu.iua.info3.structures.HashTable;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Hashtable;

public class CSVReaderCOVIDHashTable {

    static HashTable<Integer, String> infectadosHash;
    static HashTable<Integer, String> fallecidosHash;
    static HashTable<Integer, String> provinciaHash;
    static HashTable<Integer, String> fechaHash;

    public static void readCSV() {

        //Para saber el size de las tablas Hash
        CSVReader reader = null;

        //Instancias de las tablas Hash
        CSVReader reader2 = null;

        //tamaño de filas de CSV
        int tamanio = 0;

        try {

            // Instanciar el reader del archivo CSV
            URL url = CSVReaderCOVIDLinkedList.class.getResource("Covid19Casos-1000.csv");
            File file = new File(url.getPath());

            reader = new CSVReader(new FileReader(file), ',');
            reader2 = new CSVReader(new FileReader(file), ',');
            String[] nextLine;


            //solo para saber el tamanio de filas del archivo csv y pasarle el size a la tabla hash
            while(( reader2.readNext()) != null) {
                tamanio++;
            }

            infectadosHash = new HashTable<>(tamanio);
            fallecidosHash = new HashTable<>(tamanio);
            provinciaHash = new HashTable<>(tamanio);
            fechaHash = new HashTable<>(tamanio);

            // FIN  HASH

            // Leer linea por linea
            // TODO Cambiar para guardarlo en memorio en vez de imprimirlo
            // TODO y devolverlo con return desde esta funcion


            int contador = 0;

            while ((nextLine = reader.readNext()) != null) {

                int indexx = 0;
                for (String token: nextLine){
                    //HASH                  ------nico
                    if(indexx == 20){
                        infectadosHash.insert(contador,token);
                    }
                    if(indexx == 13){
                        fallecidosHash.insert(contador,token);
                    }
                    if(indexx == 5){
                        provinciaHash.insert(contador,token);
                    }
                    if(indexx == 12){
                        fechaHash.insert(contador,token);
                    }
                    indexx++;
                }

                contador++;
                p_casos();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static void p_casos () throws Exception {

        Hashtable<String,Integer> provincias = new Hashtable<>(24);

        for(int i=0 ; i < infectadosHash.sizeHash(); i++){

            try {
                if(infectadosHash.get(i).equals("Confirmado")) {
                    if(provincias.containsKey(provinciaHash.get(i))){
                        provincias.replace(provinciaHash.get(i),provincias.get(provinciaHash.get(i)+1));
                    }else{
                        provincias.put(provinciaHash.get(i),1);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        while(provincias.elements().hasMoreElements()){
            Object clave = provincias.elements().nextElement();
            Object valor = provincias.get(clave);
            System.out.println("Provincia: "+clave+" infectados: "+valor);
        }
    }
}
