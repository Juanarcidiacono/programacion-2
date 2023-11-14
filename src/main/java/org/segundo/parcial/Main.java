package org.segundo.parcial;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        CsvToArray movie = new CsvToArray();
        ListToXls sheet = new ListToXls();

        movie.printCsv();
        movie.totalPeliculasPremiadas();

        movie.listaConFiltros("Year","1986",true);
        List<String[]> data = movie.listaConFiltros("Title", "Magic", false);
        sheet.generarXls(data);
    }
}
