package org.example;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
public class ListToXls {

    final String SHEET_NAME = "lista";
    final String PATH = "src/main/ouput/filtered_movie.xls";

    /**
     * Esta función genera un archivo Excel (.xls) a partir de una lista de datos.
     * La primera fila del archivo Excel tendrá un fondo de color amarillo.
     *
     * @param data Una lista de arrays de cadenas que representan los datos para el archivo Excel.
     *             Cada elemento de la lista corresponde a una fila en la hoja de cálculo.
     */
    protected void generarXls(List<String[]> data) {
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            // Crear una hoja en el libro de trabajo
            HSSFSheet sheet = workbook.createSheet(SHEET_NAME);

            // Crear un estilo de celda con fondo de color amarillo para la primera fila
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Iterar sobre los datos y crear filas y celdas en la hoja
            int rowNum = 0;
            for (String[] rowData : data) {
                HSSFRow row = sheet.createRow(rowNum);

                int colNum = 0;
                for (String cellData : rowData) {
                    // Crear celda y establecer el valor de la celda
                    HSSFCell cell = row.createCell(colNum++);
                    cell.setCellValue(cellData);
                }

                // Aplicar estilo de celda para la primera fila (encabezado)
                if (rowNum == 0) {
                    for (int i = 0; i < row.getLastCellNum(); i++) {
                        HSSFCell cell = row.getCell(i);
                        cell.setCellStyle(headerStyle);
                    }
                }

                rowNum++;
            }

            // Guardar el libro de trabajo en un archivo
            try (FileOutputStream outputStream = new FileOutputStream(PATH)) {
                workbook.write(outputStream);
                System.out.println("Archivo Excel generado con éxito.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
