package ed.u2.datasets;

import ed.u2.models.Appointment;
import ed.u2.models.Patient;
import ed.u2.models.InventoryItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvDataLoader {

    private static final String DELIMITER = ";";

    private CsvDataLoader() {}

    // Método para cargar citas_100.csv y citas_1   00_casi_ordenadas.csv
    public static Appointment[] loadAppointments(String filename) throws IOException {
        List<Appointment> appointments = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Saltar el encabezado ("id;apellido;fechaHora")

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] values = line.split("\\s*;\\s*");
                if (values.length == 3) {
                    appointments.add(new Appointment(values[0], values[1], values[2]));
                }
            }
        }
        // Convertir la lista a un array para los algoritmos
        return appointments.toArray(new Appointment[0]);
    }

    // Método para cargar pacientes_500.csv
    public static Patient[] loadPatients(String filename) throws IOException {
        List<Patient> patients = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Saltar el encabezado

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] values = line.split("\\s*;\\s*");
                if (values.length == 3) {
                    int prioridad = Integer.parseInt(values[2]);
                    patients.add(new Patient(values[0], values[1], prioridad));
                }
            }
        }
        return patients.toArray(new Patient[0]);
    }

    // Método para cargar inventario_500_inverso.csv
    public static InventoryItem[] loadInventory(String filename) throws IOException {
        List<InventoryItem> items = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Saltar el encabezado

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] values = line.split("\\s*;\\s*");
                if (values.length == 3) {
                    int stock = Integer.parseInt(values[2]);
                    items.add(new InventoryItem(values[0], values[1], stock));
                }
            }
        }
        return items.toArray(new InventoryItem[0]);
    }
}