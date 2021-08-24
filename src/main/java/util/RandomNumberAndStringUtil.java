package util;

import java.util.Random;

public class RandomNumberAndStringUtil {

    private static StringBuilder sb = new StringBuilder();
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String generateRandomString(int stringLength) {
        for (int i = 0; i < stringLength; i++) {
            int index = (int)(alphabet.length() * Math.random());
            sb.append(alphabet.charAt(index));
        }
        return sb.toString();
    }

    public static int generateRandomNumber(int numberLength) {
        Random random = new Random();
        int generatedNumber = random.nextInt(numberLength);
        System.out.println("Random number generated: "+ generatedNumber);
        return generatedNumber;
    }
}