package org.segundo.parcial;

import java.sql.SQLOutput;
import java.util.List;

public class Main {

    static CsvToArray csv;
    static ListToXls xls;

    public static void main(String[] args) {

        System.out.println( "ALUMNO: Arcidiacono Juan Ignacio" );
        System.out.println("===================================================================");

        csv = new CsvToArray();
        xls = new ListToXls();

        csv.printCsv();
        System.out.println("Total de peliculas premiadas: "+ csv.totalPeliculasPremiadas());
        System.out.println("===================================================================");

        System.out.println("== Lista de peliculas ==");
        List<String[]> data = csv.listaConFiltros("Comedy", "1990");
        xls.generarXls(data);
    }
}
