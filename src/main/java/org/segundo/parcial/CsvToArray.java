package org.segundo.parcial;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

public class CsvToArray {
    private List<String[]> fields = new ArrayList<>();

    private List<String[]> datos;

    /**
     * Lee el archivo CSV y retorna una lista de arrays de cadenas que representan los datos del archivo.
     *
     * @return Una lista de arrays de cadenas, donde cada array representa una fila del archivo CSV.
     */
    public void leerCsv() {
        datos = new ArrayList<>();

        final String PATH = "film.csv";
        final String SEPARATOR = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;

            if ((line = br.readLine()) != null) {
                String[] headers = line.split(SEPARATOR);
                datos.add(headers);
            }

            // Lee el archivo mientras sea distinto de nulo
            while ((line = br.readLine()) != null) {
                String[] movie = line.split(SEPARATOR);
                datos.add(movie);
            }
            // IOException es una excepcion que puede surgir al momento de manipular archivos
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Imprime los datos del archivo CSV en la consola.
     */
    public void printCsv() {
        leerCsv();
        for (String[] fields : datos) {
            for (String field : fields) {
                System.out.print(field + " ");
            }
            System.out.println();
        }
    }

    /**
     * Calcula y muestra la cantidad de películas premiadas en el archivo CSV.
     */
    public long totalPeliculasPremiadas() {


        // Las peliculas premiadas estan en la columna 8 del archivo csv
        return datos.stream().filter(movie -> "Yes".equals(movie[8])).count();
    }

    /**
     * Filtra y devuelve una lista de películas basada en el año y el genero.
     *
     * Este método lee un archivo CSV de películas, filtra las películas según el año y el tema dados,
     * y devuelve una lista de arrays de strings que representan las películas filtradas.
     * El encabezado del archivo CSV se conserva en la lista resultante.
     *
     * @param subject El tema de las películas a filtrar.
     * @param year    El año de las películas a filtrar.
     * @return Una lista de arrays de strings que representan las películas filtradas.
     */
    public List<String[]> listaConFiltros(String subject, String year) {

        List<String[]> filteredMovies;


        // Uso .collect(Collectors.toList()) porque me permite crear una lista mutable.
        // Necesito agregar el encabezado de la tabla. De esta forma filteredMovies puedo pasarlo
        // como argumento a la funcion generarXls y generar un excel con estos datos.
        filteredMovies = datos.stream().filter(movie -> (year.equals(movie[0]) && subject.equals(movie[3])))
                .collect(Collectors.toList());

        if (!datos.isEmpty() && !filteredMovies.isEmpty()) {
            filteredMovies.add(0, datos.get(0));
        }

        for (String[] fields : filteredMovies) {
            for (String field : fields) {
                System.out.print(field + " ");
            }
            System.out.println();
        }

        return filteredMovies;
    }


    public List<String[]> getFields() {
        return fields;
    }

    /**
     * Filtra las películas según un criterio de búsqueda y muestra los resultados.
     *
     * @param parametroBusqueda El parámetro por el cual se va a filtrar (por ejemplo, "Year", "Title", "Actor").
     * @param value             El valor que se utilizará para filtrar las películas.
     * @param imprimirDatos     Indica si se deben imprimir los datos filtrados en la consola.
     * @return Una lista de arrays de cadenas que representan las películas filtradas.
     */
    /*
    protected List<String[]> listaConFiltros(String parametroBusqueda, String value, boolean imprimirDatos) {
        List<String[]> filteredMovies = new ArrayList<>();

        List<String[]> movies = leerCsv();

        switch (parametroBusqueda) {
            case "Year":
                filteredMovies = movies.stream()
                        .filter(movie -> value.equals(movie[0]))
                        .collect(Collectors.toList()); // uso .collect(Collectors.toList()) porque me permite crear una
                // lista mutable. Necesito agregar el encabezado de la tabla.

                break;
            case "Length":
                filteredMovies = movies.stream()
                        .filter(movie -> value.equals(movie[1]))
                        .collect(Collectors.toList());
                ;

                break;
            case "Title":
                filteredMovies = movies.stream()
                        .filter(movie -> movie[2].contains(value))
                        .collect(Collectors.toList());
                ;

                break;
            case "Subject":
                filteredMovies = movies.stream()
                        .filter(movie -> movie[3].equals(value))
                        .collect(Collectors.toList());
                ;

                break;
            case "Actor":
                filteredMovies = movies.stream()
                        .filter(movie -> movie[4].contains(value))
                        .collect(Collectors.toList());
                ;

                break;
            case "Actress":
                filteredMovies = movies.stream()
                        .filter(movie -> movie[5].contains(value))
                        .collect(Collectors.toList());
                ;

                break;
            case "Director":
                filteredMovies = movies.stream()
                        .filter(movie -> movie[6].contains(value))
                        .collect(Collectors.toList());
                ;

                break;
            case "Populartity":
                filteredMovies = movies.stream()
                        .filter(movie -> value.equals(movie[7]))
                        .collect(Collectors.toList());

                break;
            case "Awards":
                filteredMovies = movies.stream()
                        .filter(movie -> value.equals(movie[8]))
                        .collect(Collectors.toList());
                ;

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
    */



}