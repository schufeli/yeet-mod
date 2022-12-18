package codes.schufi.yeetmod.client;

public class ClientThirstData {
    private static int playerThirst = 5;

    public static void set(int thirst) {
        ClientThirstData.playerThirst = thirst;
    }

    public static int getPlayerThirst() {
        return playerThirst;
    }
}
