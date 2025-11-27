package ed.u2.models;

public class Patient {
    private final String id;
    private final String apellido;
    private final int prioridad;

    public Patient(String id, String apellido, int prioridad) {
        this.id = id;
        this.apellido = apellido;
        this.prioridad = prioridad;
    }

    public String getId() { return id; }
    public String getApellido() { return apellido; }
    public int getPrioridad() { return prioridad; }

    @Override
    public String toString() {
        return String.format("%s (%d)", apellido, prioridad);
    }
}
