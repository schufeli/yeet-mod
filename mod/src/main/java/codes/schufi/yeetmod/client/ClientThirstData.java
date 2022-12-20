package codes.schufi.yeetmod.client;

public class ClientThirstData {
    private static int playerThirst;

    public static void set(int thirst) {
        ClientThirstData.playerThirst = thirst;
    }

    public static void add(int thirst) {
        playerThirst += thirst;
    }

    public static int getPlayerThirst() {
        return playerThirst;
    }
}
