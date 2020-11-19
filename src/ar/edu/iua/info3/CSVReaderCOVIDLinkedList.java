package ar.edu.iua.info3;

import java.io.FileReader;
import java.io.File;
import java.net.URL;

import ar.edu.iua.info3.structures.HashTable;
import ar.edu.iua.info3.structures.LinkedList;

import com.opencsv.CSVReader;


public class CSVReaderCOVIDLinkedList {
    // Contadores
    private int cantMuestras;
    private int cantInfectados;
    private int cantFallecidos;

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
        // Contadores
        this.cantMuestras = 0;
        this.cantInfectados = 0;
        this.cantFallecidos = 0;

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
    }

    public HashTable<Integer, String> readCSV() {
        CSVReader reader = null;

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
                this.cantMuestras++;

                // Contador cantidad de infectados
                infectado = caso.get(20).toLowerCase().equals("confirmado");
                if (infectado) {
                    this.cantInfectados++;
                }

                // Contador cantidad de fallecidos
                fallecido = caso.get(14).toLowerCase().equals("si");
                if (fallecido) {
                    this.cantFallecidos++;
                }

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

        } catch (Exception e) {
            e.printStackTrace();
            HashTable<Integer, String> stat = new HashTable<>(1);
            return stat;
        }

        this.pctgInfMuestra = this.cantInfectados * 100 / this.cantMuestras;
        this.pctgFallInf = this.cantFallecidos * 100 / this.cantInfectados;

        HashTable<Integer, String> estadisticas = new HashTable<>(27);

        try {
            estadisticas.insert(1, Integer.toString(this.cantMuestras));
            estadisticas.insert(2, Integer.toString(this.cantFallecidos));
            estadisticas.insert(3, Integer.toString(this.cantInfectados));

            estadisticas.insert(4, Integer.toString(this.inf0a10));
            estadisticas.insert(5, Integer.toString(this.inf11a20));
            estadisticas.insert(6, Integer.toString(this.inf21a30));
            estadisticas.insert(7, Integer.toString(this.inf31a40));
            estadisticas.insert(8, Integer.toString(this.inf41a50));
            estadisticas.insert(9, Integer.toString(this.inf51a60));
            estadisticas.insert(10, Integer.toString(this.inf61a70));
            estadisticas.insert(11, Integer.toString(this.inf71a80));
            estadisticas.insert(12, Integer.toString(this.inf81a90));
            estadisticas.insert(13, Integer.toString(this.inf91a100));
            estadisticas.insert(14, Integer.toString(this.inf101a150));

            estadisticas.insert(15, Integer.toString(this.fall0a10));
            estadisticas.insert(16, Integer.toString(this.fall11a20));
            estadisticas.insert(17, Integer.toString(this.fall21a30));
            estadisticas.insert(18, Integer.toString(this.fall31a40));
            estadisticas.insert(19, Integer.toString(this.fall41a50));
            estadisticas.insert(20, Integer.toString(this.fall51a60));
            estadisticas.insert(21, Integer.toString(this.fall61a70));
            estadisticas.insert(22, Integer.toString(this.fall71a80));
            estadisticas.insert(23, Integer.toString(this.fall81a90));
            estadisticas.insert(24, Integer.toString(this.fall91a100));
            estadisticas.insert(25, Integer.toString(this.fall101a150));

            estadisticas.insert(26, Float.toString(this.pctgInfMuestra));
            estadisticas.insert(27, Float.toString(this.pctgFallInf));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return estadisticas;
    }

    public void printStats() {
        System.out.println("========================================");
        System.out.println("ESTADISTICAS GENERALES");
        System.out.println("----------------------------------------");
        System.out.println("Cantidad de Muestras: \t\t\t" + this.cantMuestras);
        System.out.println("Cantidad de Infectados: \t\t " + this.cantInfectados);
        System.out.println("Cantidad de Fallecidos: \t\t  " + this.cantFallecidos);
        System.out.println("Tasa Infectados/Muestras: \t\t" + this.pctgInfMuestra + " %");
        System.out.println("Tasa Fallecidos/Infectados: \t " + this.pctgFallInf + " %");
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

}
