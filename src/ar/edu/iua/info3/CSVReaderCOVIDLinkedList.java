package ar.edu.iua.info3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.edu.iua.info3.structures.HashTable;
import ar.edu.iua.info3.structures.LinkedList;

import com.opencsv.CSVReader;


public class CSVReaderCOVIDLinkedList {

    //nuevo nico
    static LinkedList<Casos> casosList;
    //listado de provincias
    static HashTable<Integer,String> listadoProvincias;

    //Listado de casos Infectados por provincia
    static HashTable<Integer,LinkedList<Casos>> listadoCasosProvincias;
    //ordenadoNombres
    static HashTable<Integer,String> listadoProvinciasOrdenadasContagiados;
    //ordenadoMuertos
    static HashTable<Integer,Integer> listadoProvinciasOrdenadasContagiadosNumeros;

    //listado casos Muertes por provincia
    static HashTable<Integer,LinkedList<Casos>> listadoMuertesProvincia;
    //ordenadoNombres
    static HashTable<Integer,String> listadoProvinciasOrdenadasMuertos;
    //ordenadoMuertos
    static HashTable<Integer,Integer> listadoProvinciasOrdenadasMuertosNumeros;


    // Contadores infectados por rango etareo de 10
    private int inf0a10;
    private int inf11a20;
    private int inf21a30;
    private int inf31a40;
    private int inf41a50;
    private int inf51a60;
    private int inf61a70;
    private int inf71a80;
    private int inf81a90;
    private int inf91a100;
    private int inf101a150;

    // Contadores fallecidos por rango etareo de 10
    private int fall0a10;
    private int fall11a20;
    private int fall21a30;
    private int fall31a40;
    private int fall41a50;
    private int fall51a60;
    private int fall61a70;
    private int fall71a80;
    private int fall81a90;
    private int fall91a100;
    private int fall101a150;


    private float pctgInfMuestra;
    private float pctgFallInf;

    public CSVReaderCOVIDLinkedList() {

        // Contadores infectados por rango etareo de 10
        this.inf0a10 = 0;
        this.inf11a20 = 0;
        this.inf21a30 = 0;
        this.inf31a40 = 0;
        this.inf41a50 = 0;
        this.inf51a60 = 0;
        this.inf61a70 = 0;
        this.inf71a80 = 0;
        this.inf81a90 = 0;
        this.inf91a100 = 0;
        this.inf101a150 = 0;

        // Contadores fallecidos por rango etareo de 10
        this.fall0a10 = 0;
        this.fall11a20 = 0;
        this.fall21a30 = 0;
        this.fall31a40 = 0;
        this.fall41a50 = 0;
        this.fall51a60 = 0;
        this.fall61a70 = 0;
        this.fall71a80 = 0;
        this.fall81a90 = 0;
        this.fall91a100 = 0;
        this.fall101a150 = 0;

        this.pctgInfMuestra = 0;
        this.pctgFallInf = 0;

        //nuevo nico
        casosList = new LinkedList<>();
        listadoProvincias = new HashTable<>(24);
        listadoCasosProvincias = new HashTable<>(24);
        listadoMuertesProvincia = new HashTable<>(24);
        listadoProvinciasOrdenadasMuertos = new HashTable<>(24);
        listadoProvinciasOrdenadasMuertosNumeros = new HashTable<>(24);
        listadoProvinciasOrdenadasContagiados = new HashTable<>(24);
        listadoProvinciasOrdenadasContagiadosNumeros = new HashTable<>(24);

        try {
            listadoProvincia();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readCSV() {
        CSVReader reader = null;

        try {

            // Instanciar el reader del archivo CSV
            URL url = CSVReaderCOVIDLinkedList.class.getResource("Covid19Casos-1000.csv");
            File file = new File(url.getPath());

            reader = new CSVReader(new FileReader(file), ',');
            String[] nextLine;

            // Leer linea por linea
            // TODO Cambiar para guardarlo en memorio en vez de imprimirlo
            // TODO y devolverlo con return desde esta funcion

            boolean infectado;
            boolean fallecido;
            int edad;

            while ((nextLine = reader.readNext()) != null) {
                LinkedList<String> caso = new LinkedList<>();
                int index = 0;
                for (String token : nextLine) {
                    caso.add(token, index);
                    index++;
                }

                // nuevo nico
                Casos casos = new Casos();

                //fin nuevo nico

                //Cargo Casos  NEW NEW
                if (caso.get(5) != "residencia_provincia_nombre") {
                    try {
                        casos.setEdad(Integer.parseInt(caso.get(2)));
                    } catch (NumberFormatException e) {
                        casos.setEdad(0);
                    }
                    casos.setProvincia(caso.get(5));
                    casos.setCuidadoIntensivo(caso.get(12).equals("SI"));
                    casos.setFechaCuidadoIntensivo(caso.get(13));
                    casos.setEdad_anios_meses(caso.get(3));
                }

                // Contador cantidad de infectados
                infectado = caso.get(20).toLowerCase().equals("confirmado");
                if (infectado) {
                    //nuevo nico
                    casos.setInfectado(true);
                }

                // Contador cantidad de fallecidos
                fallecido = caso.get(14).toLowerCase().equals("si");
                if (fallecido) {
                    //nuevp nico
                    casos.setFallecido(true);
                }

                casosList.add(casos);
                // Contadores por edad
                // Ignora la primera fila de encabezados y si el campo edad esta vacio
                if (!(caso.get(2).equals("edad")) && !(caso.get(2).equals(""))) {
                    edad = Integer.parseInt(caso.get(2));

                    if (edad > 0 && edad <= 10) {
                        if (infectado) {
                            this.inf0a10++;
                        }
                        if (fallecido) {
                            this.fall0a10++;
                        }
                    } else if (edad > 10 && edad <= 20) {
                        if (infectado) {
                            this.inf11a20++;
                        }
                        if (fallecido) {
                            this.fall11a20++;
                        }
                    } else if (edad > 20 && edad <= 30) {
                        if (infectado) {
                            this.inf21a30++;
                        }
                        if (fallecido) {
                            this.fall21a30++;
                        }
                    } else if (edad > 30 && edad <= 40) {
                        if (infectado) {
                            this.inf31a40++;
                        }
                        if (fallecido) {
                            this.fall31a40++;
                        }
                    } else if (edad > 40 && edad <= 50) {
                        if (infectado) {
                            this.inf41a50++;
                        }
                        if (fallecido) {
                            this.fall41a50++;
                        }
                    } else if (edad > 50 && edad <= 60) {
                        if (infectado) {
                            this.inf51a60++;
                        }
                        if (fallecido) {
                            this.fall51a60++;
                        }
                    } else if (edad > 60 && edad <= 70) {
                        if (infectado) {
                            this.inf61a70++;
                        }
                        if (fallecido) {
                            this.fall61a70++;
                        }
                    } else if (edad > 70 && edad <= 80) {
                        if (infectado) {
                            this.inf71a80++;
                        }
                        if (fallecido) {
                            this.fall71a80++;
                        }
                    } else if (edad > 80 && edad <= 90) {
                        if (infectado) {
                            this.inf81a90++;
                        }
                        if (fallecido) {
                            this.fall81a90++;
                        }
                    } else if (edad > 90 && edad <= 100) {
                        if (infectado) {
                            this.inf91a100++;
                        }
                        if (fallecido) {
                            this.fall91a100++;
                        }
                    } else if (edad > 100 && edad <= 150) {
                        if (infectado) {
                            this.inf101a150++;
                        }
                        if (fallecido) {
                            this.fall101a150++;
                        }
                    }

                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printStats() {
        System.out.println("========================================");
        System.out.println("ESTADISTICAS POR EDAD");
        System.out.println("----------------------------------------");
        System.out.println("Cantidad infectados\t  0-10\taños: " + this.inf0a10);
        System.out.println("Cantidad infectados\t 11-20\taños: " + this.inf11a20);
        System.out.println("Cantidad infectados\t 21-30\taños: " + this.inf21a30);
        System.out.println("Cantidad infectados\t 31-40\taños: " + this.inf31a40);
        System.out.println("Cantidad infectados\t 41-50\taños: " + this.inf41a50);
        System.out.println("Cantidad infectados\t 51-60\taños: " + this.inf51a60);
        System.out.println("Cantidad infectados\t 61-70\taños: " + this.inf61a70);
        System.out.println("Cantidad infectados\t 71-80\taños: " + this.inf71a80);
        System.out.println("Cantidad infectados\t 81-90\taños: " + this.inf81a90);
        System.out.println("Cantidad infectados\t 91-100\taños: " + this.inf91a100);
        System.out.println("Cantidad infectados\t101-150\taños: " + this.inf101a150);
        System.out.println("----------------------------------------");
        System.out.println("Cantidad fallecidos\t  0-10\taños: " + this.fall0a10);
        System.out.println("Cantidad fallecidos\t 11-20\taños: " + this.fall11a20);
        System.out.println("Cantidad fallecidos\t 21-30\taños: " + this.fall21a30);
        System.out.println("Cantidad fallecidos\t 31-40\taños: " + this.fall31a40);
        System.out.println("Cantidad fallecidos\t 41-50\taños: " + this.fall41a50);
        System.out.println("Cantidad fallecidos\t 51-60\taños: " + this.fall51a60);
        System.out.println("Cantidad fallecidos\t 61-70\taños: " + this.fall61a70);
        System.out.println("Cantidad fallecidos\t 71-80\taños: " + this.fall71a80);
        System.out.println("Cantidad fallecidos\t 81-90\taños: " + this.fall81a90);
        System.out.println("Cantidad fallecidos\t 91-100\taños: " + this.fall91a100);
        System.out.println("Cantidad fallecidos\t101-150\taños: " + this.fall101a150);
        System.out.println("========================================");
    }

    public void imprimirCasosLista() throws Exception {

        System.out.println("Cantidad de muestras: "+casosList.getSize());
        int cantidadInfectados = 0;
        int cantidadFallecidos = 0;
        for(int i=0; i < casosList.getSize(); i++){

            if(casosList.get(i).isFallecido())
                cantidadFallecidos++;
            if(casosList.get(i).isInfectado())
                cantidadInfectados++;
        }
        System.out.println("Cantidad Infectados: "+cantidadInfectados);
        System.out.println("Cantidad Fallecidos: "+cantidadFallecidos);
        System.out.println("% Infectados: "+(((float)cantidadInfectados*100) / (float)casosList.getSize())+"%");
        System.out.println("% Fallecidos: "+(((float)cantidadFallecidos*100) / (float)casosList.getSize())+"%");

    }

    public void p_casos() throws Exception {

        int contProvincias = 0;
        String provincia = "";
        Casos casosInterno = new Casos();
        //creo vector para ordenar
        int[] infectados = new int[24];
        String[] provinciaInfectados = new String[24];

        while(contProvincias < 24){
            //Le doy la provincia a la variable
            provincia = listadoProvincias.get(contProvincias);
            //crep la lista casitos
            LinkedList<Casos> casitos = new LinkedList<>();


            for(int i=0; i < casosList.getSize(); i++){

                if(casosList.get(i).getProvincia().equals(provincia) && casosList.get(i).isInfectado()){

                    casosInterno.setEdad(casosList.get(i).getEdad());
                    casosInterno.setFechaCuidadoIntensivo(casosList.get(i).getFechaCuidadoIntensivo());
                    casosInterno.setCuidadoIntensivo(casosList.get(i).isCuidadoIntensivo());
                    casosInterno.setFallecido(casosList.get(i).isFallecido());
                    casosInterno.setInfectado(casosList.get(i).isInfectado());
                    casosInterno.setProvincia(casosList.get(i).getProvincia());
                    // Agrego a la lista enlazada el objetos Casos
                    casitos.add(casosInterno);
                }
            }
            //Agrego a la HashTable la lista enlazada de objetos Casos
            listadoCasosProvincias.insert(contProvincias,casitos);



            //System.out.println("Provincia: "+provincia+" Cantidad Infectados: "+listadoCasosProvincias.get(contProvincias).getSize());
            //completo arrays
            infectados[contProvincias] = listadoCasosProvincias.get(contProvincias).getSize();
            provinciaInfectados[contProvincias] = provincia;
            contProvincias++;
        }
        ordenarCasosInfectados(infectados,provinciaInfectados);
    }

    public void p_muertes() throws Exception {

        int contProvincias = 0;
        String provincia = "";
        Casos casosInterno = new Casos();
        //creo vector para ordenar
        int[] muertos = new int[24];
        String[] provinciaMuertos = new String[24];

        while(contProvincias < 24){
            //Le doy la provincia a la variable
            provincia = listadoProvincias.get(contProvincias);
            //crep la lista casitos
            LinkedList<Casos> casitos = new LinkedList<>();

            for(int i=0; i < casosList.getSize(); i++){

                if(casosList.get(i).getProvincia().equals(provincia) && casosList.get(i).isFallecido()){

                    casosInterno.setEdad(casosList.get(i).getEdad());
                    casosInterno.setFechaCuidadoIntensivo(casosList.get(i).getFechaCuidadoIntensivo());
                    casosInterno.setCuidadoIntensivo(casosList.get(i).isCuidadoIntensivo());
                    casosInterno.setFallecido(casosList.get(i).isFallecido());
                    casosInterno.setInfectado(casosList.get(i).isInfectado());
                    casosInterno.setProvincia(casosList.get(i).getProvincia());
                    // Agrego a la lista enlazada el objetos Casos
                    casitos.add(casosInterno);
                }
            }
            //Agrego a la HashTable la lista enlazada de objetos Casos
            listadoMuertesProvincia.insert(contProvincias,casitos);

            //System.out.println("Provincia: "+provincia+" Cantidad Muertos: "+listadoMuertesProvincia.get(contProvincias).getSize());

            //completo arrays
            muertos[contProvincias] = listadoMuertesProvincia.get(contProvincias).getSize();
            provinciaMuertos[contProvincias] = provincia;
            //aumento contador
            contProvincias++;
        }

        ordenarCasosMuertos(muertos,provinciaMuertos);
    }


    public void casos_edad(String edad_anios_meses) throws Exception {

        int contador = 0;
        String provincia = "";
        while(contador < 24){

            provincia = listadoProvinciasOrdenadasContagiados.get(contador);
            System.out.println("----------------------------------------");
            System.out.println(provincia);
            System.out.println("----------------------------------------");
            for(int i=0; i < casosList.getSize(); i++){

                if((casosList.get(i).getEdad_anios_meses().equals(edad_anios_meses) && casosList.get(i).getProvincia().toString().equals(provincia.toString())) && (casosList.get(i).isInfectado() || casosList.get(i).isFallecido()) ){
                System.out.println(casosList.get(i).isInfectado()? "Infectado": "No Infectado");
                System.out.println(casosList.get(i).isFallecido()? "Fallecido": "Vivo");
                System.out.println(casosList.get(i).getEdad());
                System.out.println(casosList.get(i).getProvincia());
                System.out.println(casosList.get(i).getEdad_anios_meses());
                System.out.println(casosList.get(i).isCuidadoIntensivo()? "Cuidad Intensivo": "Sin cuidado intensivo");
                System.out.println(casosList.get(i).getFechaCuidadoIntensivo());

                }
            }
            contador++;
        }
    }

    public void casos_cui(String fecha) throws Exception {

        //Para convertir el string en un tipo Date y luego poder compararlo
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        }
        catch (ParseException ex)
        {
            System.out.println(ex);
        }

            for(int i=0; i < casosList.getSize(); i++){

                //Para convertir el string en un tipo Date y luego poder compararlo
                SimpleDateFormat formatoNew = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaDateNew = null;
                if(casosList.get(i).isCuidadoIntensivo()){
                    fechaDateNew = formato.parse(casosList.get(i).getFechaCuidadoIntensivo());
                    //fin convertidor
                }

                if((casosList.get(i).isCuidadoIntensivo() && fechaDate.before(fechaDateNew) && (casosList.get(i).isInfectado() || casosList.get(i).isFallecido()))){
                    System.out.print(casosList.get(i).isInfectado()? "Infectado ": "No Infectado ");
                    System.out.print(casosList.get(i).isFallecido()? "Fallecido ": "Vivo ");
                    System.out.print(casosList.get(i).getEdad()+" ");
                    System.out.print(casosList.get(i).getProvincia()+" ");
                    System.out.print(casosList.get(i).getEdad_anios_meses()+" ");
                    System.out.print(casosList.get(i).isCuidadoIntensivo()? "Cuidado Intensivo ": "Sin cuidado intensivo");
                    System.out.println(casosList.get(i).getFechaCuidadoIntensivo()+" ");

                }
            }

    }

    public void imprimirCasosMuertes(int n) throws Exception {

        for(int i=0; i < n; i++){
            System.out.print(listadoProvinciasOrdenadasMuertos.get(i));
            System.out.println(" Cantidad de Muertos: "+listadoProvinciasOrdenadasMuertosNumeros.get(i));
        }
    }

    public void imprimirCasosInfectados(int n) throws Exception {

        for(int i=0; i < n; i++){
            System.out.print(listadoProvinciasOrdenadasContagiados.get(i));
            System.out.println(" Cantidad de Infectados: "+listadoProvinciasOrdenadasContagiadosNumeros.get(i));
        }
    }

    /*
    METODOS PRIVADOS
    * */
    private void ordenarCasosMuertos(int[] vector,String[] vector2) throws Exception {

        for (int i=1; i < vector.length; i++) {
            int aux = vector[i];
            String aux2 = vector2[i];
            int j;
            for (j=i-1; j >=0 && vector[j] < aux; j--){
                vector[j+1] = vector[j];
                vector2[j+1] = vector2[j];
            }
            vector[j+1] = aux;
            vector2[j+1] = aux2;
        }

        for(int i=0; i < vector.length; i++){
            listadoProvinciasOrdenadasMuertos.insert(i,vector2[i]);
            listadoProvinciasOrdenadasMuertosNumeros.insert(i,vector[i]);
        }
    }

    private void ordenarCasosInfectados(int[] vector,String[] vector2) throws Exception {

        for (int i=1; i < vector.length; i++) {
            int aux = vector[i];
            String aux2 = vector2[i];
            int j;
            for (j=i-1; j >=0 && vector[j] < aux; j--){
                vector[j+1] = vector[j];
                vector2[j+1] = vector2[j];
            }
            vector[j+1] = aux;
            vector2[j+1] = aux2;
        }

        for(int i=0; i < vector.length; i++){
            listadoProvinciasOrdenadasContagiados.insert(i,vector2[i]);
            listadoProvinciasOrdenadasContagiadosNumeros.insert(i,vector[i]);
        }
    }


    private static void listadoProvincia() throws Exception {


        listadoProvincias.insert(0,"Buenos Aires");
        listadoProvincias.insert(1,"CABA");
        listadoProvincias.insert(2,"Catamarca");
        listadoProvincias.insert(3,"Chaco");
        listadoProvincias.insert(4,"Chubut");
        listadoProvincias.insert(5,"Córdoba");
        listadoProvincias.insert(6,"Corrientes" );
        listadoProvincias.insert(7,"Entre Ríos");
        listadoProvincias.insert(8,"Formosa");
        listadoProvincias.insert(9,"Jujuy");
        listadoProvincias.insert(10,"La Pampa");
        listadoProvincias.insert(11,"La Rioja");
        listadoProvincias.insert(12,"Mendoza");
        listadoProvincias.insert(13,"Misiones");
        listadoProvincias.insert(14,"Neuquén");
        listadoProvincias.insert(15,"Río Negro");
        listadoProvincias.insert(16,"Salta");
        listadoProvincias.insert(17,"San Juan");
        listadoProvincias.insert(18,"San Luis");
        listadoProvincias.insert(19,"Santa Cruz");
        listadoProvincias.insert(20,"Santa Fe");
        listadoProvincias.insert(21,"Santiago del Estero");
        listadoProvincias.insert(22,"Tierra del Fuego");
        listadoProvincias.insert(23,"Tucumán");
    }



}
