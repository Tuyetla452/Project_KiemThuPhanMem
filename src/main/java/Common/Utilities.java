package Common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.regex.Pattern;

public class Utilities {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    public static void scrollDown(WebDriver driver) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,250)", "");
    }

}
