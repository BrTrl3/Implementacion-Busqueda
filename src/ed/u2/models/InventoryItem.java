package ed.u2.models;

public class InventoryItem {

    private final String id;
    private final String insumo;
    private final int stock;

    public InventoryItem(String id, String insumo, int stock) {
        this.id = id;
        this.insumo = insumo;
        this.stock = stock;
    }

    public String getId() { return id; }
    public String getInsumo() { return insumo; }
    public int getStock() { return stock; }

    @Override
    public String toString() {
        return "%s (Stock: %d)".formatted(insumo, stock);
    }
}
