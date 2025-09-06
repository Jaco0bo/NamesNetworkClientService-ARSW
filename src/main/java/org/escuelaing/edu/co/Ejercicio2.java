package org.escuelaing.edu.co;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) throws Exception {
        System.out.println("Ingrese la URL de la cual se obtendran datos: ");
        Scanner sc = new Scanner(System.in);
        String url = sc.nextLine();
        URL google = new URL(url);
        File resultado = new File("resultado.html");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(google.openStream()));
        FileWriter writer = new FileWriter(resultado))  {
            String inputline;
            while ((inputline = reader.readLine()) != null) {
                writer.write(inputline);
                writer.write("\n");
            }
            System.out.println("Contenido guardado en: " + resultado.getAbsolutePath());
        } catch (IOException x) {
            System.err.println(x);
        }
    }
}
