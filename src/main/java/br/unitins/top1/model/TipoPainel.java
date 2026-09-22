package br.unitins.top1.model;

public enum TipoPainel {
    IPS(1, "IPS"),
    VA(2, "VA"),
    TN(3, "TN"),
    OLED(4, "OLED");

    private final int id;
    private final String name;

    TipoPainel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public static TipoPainel fromId(int id) {
        for (TipoPainel tipo : TipoPainel.values()) {
            if (tipo.getId() == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Invalid panel type: " + id);
    }
}