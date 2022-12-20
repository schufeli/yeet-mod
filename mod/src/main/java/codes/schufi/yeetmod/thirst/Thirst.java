package codes.schufi.yeetmod.thirst;

public class Thirst {
    private int thirst;
    private final int MIN_THIRST = 0;
    private final int MAX_THIRST = 10;

    public int getThirst() {
        return thirst;
    }

    public void addThirst(int add) {
        this.thirst = Math.min(thirst + add, MAX_THIRST);
    }

    public void subThirst(int sub) {
        this.thirst = Math.max(thirst - sub, MIN_THIRST);
    }

    public void copyFrom(Thirst source) {
        this.thirst = source.thirst;
    }
}
