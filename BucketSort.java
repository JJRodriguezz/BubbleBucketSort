import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BucketSort {

    public static void imprimirVector(String[] vector) {
        System.out.println("El vector resultante es: ");
        for (String s : vector) {
            System.out.print("|" + s);
        }
        System.out.print("|");
    }

    public static void bucketSort(String[] vector) {
        int n = 26; // Número de buckets para letras 'a' a 'z'
        ArrayList<ArrayList<String>> buckets = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            buckets.add(new ArrayList<>());
        }

        for (String s : vector) {
            char firstChar = s.toLowerCase().charAt(0);
            if (firstChar >= 'a' && firstChar <= 'z') {
                int bucketIndex = firstChar - 'a';
                buckets.get(bucketIndex).add(s);
            } else {
                buckets.get(0).add(s); // Bucket especial para caracteres que no sean del ABC
            }
        }

        int index = 0;
        for (ArrayList<String> bucket : buckets) {
            Collections.sort(bucket);
            for (String s : bucket) {
                vector[index++] = s;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> palabrasList = new ArrayList<>();

        // Definir el nombre del archivo de texto directamente en el código
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
        bucketSort(vector);
        long endTime = System.currentTimeMillis(); // Tiempo después de ordenar

        imprimirVector(vector);
        System.out.println("\nTiempo de ejecución: " + (endTime - startTime) + " ms");

        scanner.close();
    }
}