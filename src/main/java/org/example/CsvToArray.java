package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvToArray {

    /**
     * Lee el archivo CSV y retorna una lista de arrays de cadenas que representan los datos del archivo.
     *
     * @return Una lista de arrays de cadenas, donde cada array representa una fila del archivo CSV.
     */
    private List<String[]> leerCsv(){
            final String path = "src/main/resources/film.csv";
            final String separator = ";";
            List<String[]> fields = new ArrayList<>();

                try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                    String line;

                    if ((line = br.readLine()) != null) {
                        String[] headers = line.split(separator);
                        fields.add(headers);
                    }

                    // Lee el archivo mientras sea distinto de nulo
                    while ((line = br.readLine()) != null) {
                        String[] movie = line.split(separator);
                        fields.add(movie);
                    }
                    // IOException es una excepcion que puede surgir al momento de manipular archivos
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

        return fields;
    }

    /**
     * Imprime los datos del archivo CSV en la consola.
     */
     protected void printCsv(){
        List<String[]> csvFields = leerCsv();
        for (String[] fields : csvFields) {
            for (String field : fields) {
                System.out.print(field + " ");
            }
            System.out.println();
        }
    }

    /**
     * Calcula y muestra la cantidad de películas premiadas en el archivo CSV.
     */
    protected void totalPeliculasPremiadas(){
        List<String[]> movies = leerCsv();

        // Las peliculas premiadas estan en la columna 8 del archivo csv
        long cantidadPeliculasPremiadas = movies.stream().filter(movie -> "Yes".equals(movie[8])).count();

        System.out.println("La cantidad de peliculas premiadas son: " + cantidadPeliculasPremiadas);
    }

    /**
     * Filtra las películas según un criterio de búsqueda y muestra los resultados.
     *
     * @param parametroBusqueda El parámetro por el cual se va a filtrar (por ejemplo, "Year", "Title", "Actor").
     * @param value             El valor que se utilizará para filtrar las películas.
     * @param imprimirDatos     Indica si se deben imprimir los datos filtrados en la consola.
     * @return Una lista de arrays de cadenas que representan las películas filtradas.
     */
    protected List<String[]> listaConFiltros(String parametroBusqueda, String value,boolean imprimirDatos){
        List<String[]> filteredMovies = new ArrayList<>();

        List<String[]> movies = leerCsv();

        switch (parametroBusqueda){
            case "Year":
                filteredMovies = movies.stream()
                        .filter(movie -> value.equals(movie[0]))
                        .collect(Collectors.toList()); // uso .collect(Collectors.toList()) porque me permite crear una
                // lista mutable. Necesito agregar el encabezado de la tabla.

                break;
            case "Length":
                filteredMovies = movies.stream()
                        .filter(movie -> value.equals(movie[1]))
                        .collect(Collectors.toList());;

                break;
            case "Title":
                filteredMovies = movies.stream()
                        .filter(movie -> movie[2].contains(value))
                        .collect(Collectors.toList());;

                break;
            case "Subject":
                filteredMovies = movies.stream()
                        .filter(movie -> movie[3].equals(value))
                        .collect(Collectors.toList());;

                break;
            case "Actor":
                filteredMovies = movies.stream()
                        .filter(movie -> movie[4].contains(value))
                        .collect(Collectors.toList());;

                break;
            case "Actress":
                filteredMovies = movies.stream()
                        .filter(movie -> movie[5].contains(value))
                        .collect(Collectors.toList());;

                break;
            case "Director":
                filteredMovies = movies.stream()
                        .filter(movie -> movie[6].contains(value))
                        .collect(Collectors.toList());;

                break;
            case "Populartity":
                filteredMovies = movies.stream()
                        .filter(movie -> value.equals(movie[7]))
                        .collect(Collectors.toList());

                break;
            case "Awards":
                filteredMovies = movies.stream()
                        .filter(movie -> value.equals(movie[8]))
                        .collect(Collectors.toList());;

                break;
            case "Image":
                filteredMovies = movies.stream()
                        .filter(movie -> movie[9].contains(value))
                        .collect(Collectors.toList());
                break;
            default:
                System.out.println("Parámetro de búsqueda no válido");
                break;
        }

        if (!movies.isEmpty() && !filteredMovies.isEmpty()) {
            filteredMovies.add(0, movies.get(0));
        }

        if (imprimirDatos) {
            filteredMovies.forEach(filteredMovie -> System.out.println(Arrays.toString(filteredMovie)));
        }

        return filteredMovies;
    }


}
