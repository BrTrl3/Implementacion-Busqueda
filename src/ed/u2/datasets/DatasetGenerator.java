package ed.u2.datasets;

import ed.u2.models.Appointment;
import ed.u2.models.Patient;
import ed.u2.models.InventoryItem;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Generador de datasets según la ficha.
 * Archivos generados en la carpeta "datasets/" de la raíz del proyecto.
 */
public final class DatasetGenerator {

    private static final Random RANDOM = new Random(42);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private static final Path DATASETS_DIR = Path.of("datasets");

    private DatasetGenerator() {}

    // -------------------------------------------------
    // Public API
    // -------------------------------------------------
    public static void generateAllDatasets() throws IOException {
        if (!Files.exists(DATASETS_DIR)) {
            Files.createDirectories(DATASETS_DIR);
        }

        Path citas100 = DATASETS_DIR.resolve("citas_100.csv");
        Path citas100Casi = DATASETS_DIR.resolve("citas_100_casi_ordenadas.csv");
        Path pacientes500 = DATASETS_DIR.resolve("pacientes_500.csv");
        Path inventario500 = DATASETS_DIR.resolve("inventario_500_inverso.csv");

        generateCitas100Csv(citas100);
        System.out.println("Dataset generado: " + citas100);

        generateCitas100CasiOrdenadasCsv(citas100Casi);
        System.out.println("Dataset generado: " + citas100Casi);

        generatePacientes500Csv(pacientes500);
        System.out.println("Dataset generado: " + pacientes500);

        generateInventario500InversoCsv(inventario500);
        System.out.println("Dataset generado: " + inventario500);
    }

    // -----------------------
    // 1) citas_100.csv
    // -----------------------
    public static void generateCitas100Csv(Path out) throws IOException {
        List<Appointment> list = new ArrayList<>(100);

        String[] surnames = new String[] {
                "González","Rodríguez","García","Martínez","López","Hernández","Pérez","Sánchez","Ramírez","Torres",
                "Flores","Rivera","Gómez","Díaz","Vargas","Castillo","Morales","Vásquez","Ramos","Ortiz",
                "Cruz","Guerrero","Naranjo","Cedeño","Benítez","Rojas","Acosta","Mendoza","Salazar","Pacheco"
        };

        for (int i = 1; i <= 100; i++) {
            String id = String.format("CITA-%03d", i);
            String apellido = surnames[RANDOM.nextInt(surnames.length)];

            // día 1..31, hora 8..17, minuto 0,10,...,50
            int day = 1 + RANDOM.nextInt(31);
            int hour = 8 + RANDOM.nextInt(10); // 8..17
            int minute = RANDOM.nextInt(6) * 10;

            LocalDateTime dt = LocalDateTime.of(2025, 3, day, hour, minute);
            String dateTimeStr = dt.format(DATE_FORMATTER);

            list.add(new Appointment(id, apellido, dateTimeStr));
        }

        writeAppointmentsCsv(out, list);
    }

    // -----------------------
    // 2) citas_100_casi_ordenadas.csv
    // -----------------------
    public static void generateCitas100CasiOrdenadasCsv(Path out) throws IOException {
        // reproducibilidad independiente: nuevo Random con semilla 42
        Random r = new Random(42);

        String[] surnames = new String[] {
                "González","Rodríguez","García","Martínez","López","Hernández","Pérez","Sánchez","Ramírez","Torres",
                "Flores","Rivera","Gómez","Díaz","Vargas","Castillo","Morales","Vásquez","Ramos","Ortiz",
                "Cruz","Guerrero","Naranjo","Cedeño","Benítez","Rojas","Acosta","Mendoza","Salazar","Pacheco"
        };

        List<Appointment> list = new ArrayList<>(100);
        for (int i = 1; i <= 100; i++) {
            String id = String.format("CITA-%03d", i);
            String apellido = surnames[r.nextInt(surnames.length)];
            int day = 1 + r.nextInt(31);
            int hour = 8 + r.nextInt(10); // 8..17
            int minute = r.nextInt(6) * 10;
            LocalDateTime dt = LocalDateTime.of(2025, 3, day, hour, minute);
            String dateTimeStr = dt.format(DATE_FORMATTER);
            list.add(new Appointment(id, apellido, dateTimeStr));
        }

        // ordenar por fecha/hora ascendente
        list.sort(Comparator.comparing(a -> LocalDateTime.parse(a.dateTimeStr(), DATE_FORMATTER)));

        // Hacer exactamente 5 swaps (5% de 100), sin repetir pareja
        int swapsNeeded = 5;
        Set<String> swappedPairs = new HashSet<>();
        int attempts = 0;
        while (swapsNeeded > 0 && attempts < 1000) {
            attempts++;
            int i = r.nextInt(list.size());
            int j = r.nextInt(list.size());
            if (i == j) continue;
            int a = Math.min(i, j);
            int b = Math.max(i, j);
            String key = a + "-" + b;
            if (swappedPairs.contains(key)) continue;
            Collections.swap(list, a, b);
            swappedPairs.add(key);
            swapsNeeded--;
        }

        writeAppointmentsCsv(out, list);
    }

    // -----------------------
    // 3) pacientes_500.csv
    // -----------------------
    public static void generatePacientes500Csv(Path out) throws IOException {
        List<Patient> list = new ArrayList<>(500);

        // pool ≈ 50 apellidos (subgrupos para sesgo)
        String[] pool = new String[] {
                "Ramírez","Ramírez","Ramírez","Ramírez","González","González","González","González","Pérez","Pérez",
                "López","Hernández","Sánchez","Rodríguez","García","Martínez","Flores","Rivera","Gómez","Díaz",
                "Vargas","Castillo","Morales","Vásquez","Ramos","Ortiz","Cruz","Guerrero","Naranjo","Cedeño",
                "Benítez","Rojas","Acosta","Mendoza","Salazar","Pacheco","Soto","Valencia","Navarro","Suárez",
                "Lozada","Camacho","Arias","Bravo","Montero","Silva","León","Ibarra","Pérez-G","Montaño"
        };

        Random r = new Random(42);
        for (int i = 1; i <= 500; i++) {
            String id = String.format("PAC-%04d", i);
            int pick = r.nextInt(100);
            String apellido;
            if (pick < 60) {
                apellido = pool[r.nextInt(10)];
            } else if (pick < 90) {
                apellido = pool[10 + r.nextInt(20)];
            } else {
                apellido = pool[30 + r.nextInt(20)];
            }
            int prioridad = 1 + r.nextInt(3);
            list.add(new Patient(id, apellido, prioridad));
        }

        writePatientsCsv(out, list);
    }

    // -----------------------
    // 4) inventario_500_inverso.csv
    // -----------------------
    public static void generateInventario500InversoCsv(Path out) throws IOException {
        List<InventoryItem> list = new ArrayList<>(500);

        String[] insumos = new String[] {
                "Guante Nitrilo Talla M","Alcohol 70% 1L","Gasas 10x10","Mascarilla Quirúrgica",
                "Jeringa 5ml","Catéter 14G","Venda Elástica","Termómetro Digital","Bata Desechable",
                "Papel Gasas","Alcohol 70% 500ml","Tiritas","Compresas Estériles","Guantes Látex Talla L",
                "Gasa Hidrocoloide","Silla Ruedas Plegable","Bolsa Suero","Tubos Ensayo","Aguja 21G","Solución Salina 0.9%"
        };

        for (int i = 0; i < 500; i++) {
            String id = String.format("ITEM-%04d", i + 1);
            String insumo = insumos[i % insumos.length];
            int stock = 500 - i; // 500,499,...,1
            list.add(new InventoryItem(id, insumo, stock));
        }

        writeInventoryCsv(out, list);
    }

    // -----------------------
    // CSV writers
    // -----------------------
    private static void writeAppointmentsCsv(Path out, List<Appointment> list) throws IOException {
        try (BufferedWriter w = Files.newBufferedWriter(out, StandardCharsets.UTF_8)) {
            w.write("id;apellido;fechaHora");
            w.newLine();
            for (Appointment a : list) {
                w.write(String.format("%s;%s;%s", a.id(), a.lastName(), a.dateTimeStr()));
                w.newLine();
            }
        }
    }

    private static void writePatientsCsv(Path out, List<Patient> list) throws IOException {
        try (BufferedWriter w = Files.newBufferedWriter(out, StandardCharsets.UTF_8)) {
            w.write("id;apellido;prioridad");
            w.newLine();
            for (Patient p : list) {
                w.write(String.format("%s;%s;%d", p.getId(), p.getApellido(), p.getPrioridad()));
                w.newLine();
            }
        }
    }

    private static void writeInventoryCsv(Path out, List<InventoryItem> list) throws IOException {
        try (BufferedWriter w = Files.newBufferedWriter(out, StandardCharsets.UTF_8)) {
            w.write("id;insumo;stock");
            w.newLine();
            for (InventoryItem it : list) {
                w.write(String.format("%s;%s;%d", it.getId(), it.getInsumo(), it.getStock()));
                w.newLine();
            }
        }
    }

    // -------------------------------------------------
    // Quick runner (optional)
    // -------------------------------------------------
    public static void main(String[] args) throws IOException {
        generateAllDatasets();
    }
}
