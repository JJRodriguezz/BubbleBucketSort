import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BubbleSort {

    public static void imprimirVector(String[] vector) {
        System.out.println("El vector resultante es: ");
        for (String s : vector) {
            System.out.print("|" + s);
        }
        System.out.print("|");
    }

    public static void bubbleSort(String[] vector) {
        int n = vector.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (vector[j].compareTo(vector[j + 1]) > 0) {
                    String temp = vector[j];
                    vector[j] = vector[j + 1];
                    vector[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> palabrasList = new ArrayList<>();

        // Definir el nombre del archivo de texto directamente en el código.
        String nombreArchivo = "/workspaces/BubbleBucketSort/palabras.es";

        // Pedir al usuario cuántas palabras desea leer
        System.out.println("Ingrese el número de palabras que desea leer del archivo:");
        int numPalabras = scanner.nextInt();

        // Leer palabras desde el archivo
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int contador = 0;
            while ((linea = br.readLine()) != null && contador < numPalabras) {
                palabrasList.add(linea);
                contador++;
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
            scanner.close();
            return; // Salir del programa si hay un error al leer el archivo
        }

        // Convertir ArrayList a un array
        String[] vector = palabrasList.toArray(new String[0]);

        long startTime = System.currentTimeMillis(); // Tiempo antes de ordenar
        bubbleSort(vector);
        long endTime = System.currentTimeMillis(); // Tiempo después de ordenar

        imprimirVector(vector);
        System.out.println("\nTiempo de ejecución: " + (endTime - startTime) + " ms");

        scanner.close();
    }
}