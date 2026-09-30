package br.unitins.top1.model;

public enum PanelType {
    IPS(1, "IPS"),
    VA(2, "VA"),
    TN(3, "TN"),
    OLED(4, "OLED");

    private final int id;
    private final String name;

    PanelType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public static PanelType fromId(int id) {
        for (PanelType tipo : PanelType.values()) {
            if (tipo.getId() == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Invalid panel type: " + id);
    }
}