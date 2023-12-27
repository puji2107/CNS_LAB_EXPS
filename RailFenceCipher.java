import java.util.Scanner;

public class RailFenceCipher {

    // Encryption method for Rail Fence Cipher
    public static String encrypt(String text, int rails) {
        char[][] railMatrix = new char[rails][text.length()];
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                railMatrix[i][j] = '\n';
            }
        }

        boolean downward = false;
        int row = 0, col = 0;

        for (int i = 0; i < text.length(); i++) {
            if (row == 0 || row == rails - 1) {
                downward = !downward;
            }
            railMatrix[row][col++] = text.charAt(i);

            if (downward) {
                row++;
            } else {
                row--;
            }
        }

        StringBuilder result = new StringBuilder();
        StringBuilder process = new StringBuilder();
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (railMatrix[i][j] != '\n') {
                    result.append(railMatrix[i][j]);
                    process.append(railMatrix[i][j]);
                } else {
                    process.append(' ');
                }
            }
            process.append('\n');
        }
        System.out.println("Encryption Process:");
        System.out.println(process.toString());
        return result.toString();
    }

    // Decryption method for Rail Fence Cipher
    public static String decrypt(String text, int rails) {
        char[][] railMatrix = new char[rails][text.length()];
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                railMatrix[i][j] = '\n';
            }
        }

        boolean downward = false;
        int row = 0, col = 0;

        for (int i = 0; i < text.length(); i++) {
            if (row == 0) {
                downward = true;
            }
            if (row == rails - 1) {
                downward = false;
            }
            railMatrix[row][col++] = '*';

            if (downward) {
                row++;
            } else {
                row--;
            }
        }

        int index = 0;
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (railMatrix[i][j] == '*' && index < text.length()) {
                    railMatrix[i][j] = text.charAt(index++);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        StringBuilder process = new StringBuilder();
        row = 0;
        col = 0;
        for (int i = 0; i < text.length(); i++) {
            if (row == 0) {
                downward = true;
            }
            if (row == rails - 1) {
                downward = false;
            }
            if (railMatrix[row][col] != '*') {
                result.append(railMatrix[row][col++]);
                process.append(railMatrix[row][col - 1]);
            } else {
                process.append(' ');
            }
            if (downward) {
                row++;
            } else {
                row--;
            }
        }
        System.out.println("Decryption Process:");
        System.out.println(process.toString());
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the number of rails (depth): ");
        int rails = scanner.nextInt();

        // Encryption
        String encryptedText = encrypt(plaintext, rails);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decryption
        String decryptedText = decrypt(encryptedText, rails);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}
