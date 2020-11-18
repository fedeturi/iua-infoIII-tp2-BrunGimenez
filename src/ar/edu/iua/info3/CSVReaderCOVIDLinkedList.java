package ar.edu.iua.info3;

import java.io.FileReader;
import java.io.File;
import java.net.URL;

import ar.edu.iua.info3.structures.LinkedList;

import com.opencsv.CSVReader;


public class CSVReaderCOVIDLinkedList {

    public CSVReaderCOVIDLinkedList() {
    }

    public static int readCSV() {
        CSVReader reader = null;

        // Contadores
        int cantMuestras = 0;
        int cantInfectados = 0;
        int cantFallecidos = 0;

        // Contadores infectados por rango etareo de 10
        int inf0a10 = 0;
        int inf11a20 = 0;
        int inf21a30 = 0;
        int inf31a40 = 0;
        int inf41a50 = 0;
        int inf51a60 = 0;
        int inf61a70 = 0;
        int inf71a80 = 0;
        int inf81a90 = 0;
        int inf91a100 = 0;
        int inf101a150 = 0;

        // Contadores fallecidos por rango etareo de 10
        int fall0a10 = 0;
        int fall11a20 = 0;
        int fall21a30 = 0;
        int fall31a40 = 0;
        int fall41a50 = 0;
        int fall51a60 = 0;
        int fall61a70 = 0;
        int fall71a80 = 0;
        int fall81a90 = 0;
        int fall91a100 = 0;
        int fall101a150 = 0;

        try {

            // Instanciar el reader del archivo CSV
            URL url = CSVReaderCOVIDLinkedList.class.getResource("covid.csv");
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

                // Contador cantidad de muestras
                cantMuestras++;

                // Contador cantidad de infectados
                infectado = caso.get(20).toLowerCase().equals("confirmado");
                if (infectado) {
                    cantInfectados++;
                }

                // Contador cantidad de fallecidos
                fallecido = caso.get(14).toLowerCase().equals("si");
                if (fallecido) {
                    cantFallecidos++;
                }

                // Contadores por edad
                // Ignora la primera fila de encabezados y si el campo edad esta vacio
                if (!(caso.get(2).equals("edad")) && !(caso.get(2).equals(""))) {
                    edad = Integer.parseInt(caso.get(2));

                    if (edad > 0 && edad <= 10) {
                        if (infectado) {
                            inf0a10++;
                        }
                        if (fallecido) {
                            fall0a10++;
                        }
                    } else if (edad > 10 && edad <= 20) {
                        if (infectado) {
                            inf11a20++;
                        }
                        if (fallecido) {
                            fall11a20++;
                        }
                    } else if (edad > 20 && edad <= 30) {
                        if (infectado) {
                            inf21a30++;
                        }
                        if (fallecido) {
                            fall21a30++;
                        }
                    } else if (edad > 30 && edad <= 40) {
                        if (infectado) {
                            inf31a40++;
                        }
                        if (fallecido) {
                            fall31a40++;
                        }
                    } else if (edad > 40 && edad <= 50) {
                        if (infectado) {
                            inf41a50++;
                        }
                        if (fallecido) {
                            fall41a50++;
                        }
                    } else if (edad > 50 && edad <= 60) {
                        if (infectado) {
                            inf51a60++;
                        }
                        if (fallecido) {
                            fall51a60++;
                        }
                    } else if (edad > 60 && edad <= 70) {
                        if (infectado) {
                            inf61a70++;
                        }
                        if (fallecido) {
                            fall61a70++;
                        }
                    } else if (edad > 70 && edad <= 80) {
                        if (infectado) {
                            inf71a80++;
                        }
                        if (fallecido) {
                            fall71a80++;
                        }
                    } else if (edad > 80 && edad <= 90) {
                        if (infectado) {
                            inf81a90++;
                        }
                        if (fallecido) {
                            fall81a90++;
                        }
                    } else if (edad > 90 && edad <= 100) {
                        if (infectado) {
                            inf91a100++;
                        }
                        if (fallecido) {
                            fall91a100++;
                        }
                    } else if (edad > 100 && edad <= 150) {
                        if (infectado) {
                            inf101a150++;
                        }
                        if (fallecido) {
                            fall101a150++;
                        }
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

        float pctgInfMuestra = cantInfectados * 100 / cantMuestras;
        float pctgFallInf = cantFallecidos * 100 / cantInfectados;

        System.out.println("========================================");
        System.out.println("ESTADISTICAS GENERALES");
        System.out.println("----------------------------------------");
        System.out.println("Cantidad de Muestras: \t\t\t" + cantMuestras);
        System.out.println("Cantidad de Infectados: \t\t " + cantInfectados);
        System.out.println("Cantidad de Fallecidos: \t\t  " + cantFallecidos);
        System.out.println("Tasa Infectados/Muestras: \t\t" + pctgInfMuestra + " %");
        System.out.println("Tasa Fallecidos/Infectados: \t " + pctgFallInf + " %");
        System.out.println("========================================");
        System.out.println("ESTADISTICAS POR EDAD");
        System.out.println("----------------------------------------");
        System.out.println("Cantidad infectados\t  0-10\taños: " + inf0a10);
        System.out.println("Cantidad infectados\t 11-20\taños: " + inf11a20);
        System.out.println("Cantidad infectados\t 21-30\taños: " + inf21a30);
        System.out.println("Cantidad infectados\t 31-40\taños: " + inf31a40);
        System.out.println("Cantidad infectados\t 41-50\taños: " + inf41a50);
        System.out.println("Cantidad infectados\t 51-60\taños: " + inf51a60);
        System.out.println("Cantidad infectados\t 61-70\taños: " + inf61a70);
        System.out.println("Cantidad infectados\t 71-80\taños: " + inf71a80);
        System.out.println("Cantidad infectados\t 81-90\taños: " + inf81a90);
        System.out.println("Cantidad infectados\t 91-100\taños: " + inf91a100);
        System.out.println("Cantidad infectados\t101-150\taños: " + inf101a150);
        System.out.println("----------------------------------------");
        System.out.println("Cantidad fallecidos\t  0-10\taños: " + fall0a10);
        System.out.println("Cantidad fallecidos\t 11-20\taños: " + fall11a20);
        System.out.println("Cantidad fallecidos\t 21-30\taños: " + fall21a30);
        System.out.println("Cantidad fallecidos\t 31-40\taños: " + fall31a40);
        System.out.println("Cantidad fallecidos\t 41-50\taños: " + fall41a50);
        System.out.println("Cantidad fallecidos\t 51-60\taños: " + fall51a60);
        System.out.println("Cantidad fallecidos\t 61-70\taños: " + fall61a70);
        System.out.println("Cantidad fallecidos\t 71-80\taños: " + fall71a80);
        System.out.println("Cantidad fallecidos\t 81-90\taños: " + fall81a90);
        System.out.println("Cantidad fallecidos\t 91-100\taños: " + fall91a100);
        System.out.println("Cantidad fallecidos\t101-150\taños: " + fall101a150);
        System.out.println("========================================");


        return cantMuestras;
    }

}
