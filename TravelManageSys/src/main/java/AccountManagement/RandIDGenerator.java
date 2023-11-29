package AccountManagement;

import java.util.Random;

public class RandIDGenerator {
    private StringBuilder str;
    private int itemCount;
    private String alphaNumeric;

    public RandIDGenerator() {
        str = new StringBuilder();
        itemCount = 0;
        alphaNumeric = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    }

    public void generateRandID() {
        Random rand = new Random();
        for (int i = 0; i < itemCount; i++) {
            str.append(alphaNumeric.charAt(rand.nextInt(alphaNumeric.length())));
        }
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getRandID() {
        return str.toString();
    }
}
