package codes.schufi.yeetmod.api.thirst;

public enum WaterType {
    NORMAL(0.5f),
    DIRTY(1.0f),
    PURIFIED(0.0f);
    
    private float poisonChance;

    WaterType(float poisonChance) {
        this.poisonChance = poisonChance;
    }

    public float getPoisonChance() {
        return this.poisonChance;
    }
}