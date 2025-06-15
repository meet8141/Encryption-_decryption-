import java.util.*;

// Main class for Encryption and Decryption
public class Encryption_Decryption {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Logic cipher = new Logic();// Creating an object of Logic class

        boolean exit = false;

        while (!exit) {
            displayMenu();// Displaying menu options
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.println("[Select type of Encryption you want]");

                    System.out.println("|---1.Simple Encryption");

                    System.out.println("|---2.Reverse Encryption");
                    System.out.print("[Enter your choice]");
                    String Encryption_choice = sc.nextLine();
                    switch (Encryption_choice) {
                        case "1" -> {
                            System.out.print("Enter message to encrypt: ");
                            String message = sc.nextLine();

                            System.out.print("Enter key (positive-integer): ");
                            String key = sc.nextLine();

                            if (Check_Key(key)) {
                                int numericKey = Integer.parseInt(key);
                                String encryptedMessage = cipher.encrypt(message, numericKey);
                                System.out.println("Encrypted Message: " + encryptedMessage);
                            } else {
                                System.out.println("Invalid input. The key must be a positive integer.");
                            }
                        }
                        case "2" -> {
                            System.out.print("Enter message to encrypt: ");
                            String message = sc.nextLine();
                            System.out.print("Enter key (positive-integer): ");
                            String key = sc.nextLine();

                            if (Check_Key(key)) {
                                int numericKey = Integer.parseInt(key);
                                String encryptedMessage = cipher.ReverseEncrypt(message, numericKey);
                                System.out.println("Encrypted Message: " + encryptedMessage);
                            } else {
                                System.out.println("Invalid input. The key must be a positive integer.");
                            }
                        }

                        default -> System.out.println("Invalid option. Please select a valid option.");

                    }
                }

                case "2" -> {
                    System.out.println("[Select type of Decryption you want]");

                    System.out.println("|---1.Simple Decryption");

                    System.out.println("|---2.Reverse Decryption");
                    System.out.print("[Enter your choice]");
                    String Decryption_choice = sc.nextLine();
                    switch (Decryption_choice) {
                        case "1" -> {
                            System.out.print("Enter message to decrypt: ");
                            String message = sc.nextLine();

                            System.out.print("Enter key (positive-integer): ");
                            String key = sc.nextLine();

                            if (Check_Key(key)) {
                                int numericKey = Integer.parseInt(key);
                                String decryptedMessage = cipher.decrypt(message, numericKey);
                                System.out.println("decrypted Message: " + decryptedMessage);
                            } else {
                                System.out.println("Invalid input. The key must be a positive integer.");
                            }
                        }
                        case "2" -> {
                            System.out.print("Enter message to decrypt: ");
                            String message = sc.nextLine();

                            System.out.print("Enter key (positive-integer): ");
                            String key = sc.nextLine();

                            if (Check_Key(key)) {
                                int numericKey = Integer.parseInt(key);
                                String decryptedMessage = cipher.ReverseDecrypt(message, numericKey);
                                System.out.println("decrypted Message: " + decryptedMessage);
                            } else {
                                System.out.println("Invalid input. The key must be a positive integer.");
                            }
                        }
                        default -> System.out.println("Invalid option. Please select a valid option.");
                    }
                }
                case "3" -> {
                    System.out.println("Exiting the program...");
                    exit = true;
                }
                default -> System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }

    /*--------------------Displaying_Menu--------------------*/
    static void displayMenu() {
        System.out.println("\nMenu");
        System.out.println("|--1.Encrypt a Message");
        System.out.println("|--2.Decrypt a Message");
        System.out.println("|--3.Exit");
        System.out.print("[Enter your choice]");
    }

    /*--------------------Checking_valid_key--------------------*/
    static boolean Check_Key(String key) {
        if (key == null || key.isEmpty()) {
            return false;
        }
        for (char c : key.toCharArray()) {
            if (!(c >= '1' && c <= '9')) {
                return false;
            }
        }
        return true;
    }
}
// Logic class that handles encryption and decryption
class Logic {
    /*--------------------Simple Encryption method using shift cipher--------------------*/

    String encrypt(String message, int key) {
        int shift = key % 26;
        int numShift = key % 10;
        StringBuffer result = new StringBuffer();

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) ((c - base + shift) % 26 + base);
            } else if (Character.isDigit(c)) {
                c = (char) ((c - '0' + numShift) % 10 + '0');
            }
            result.append(c);
        }
        return result.toString();
    }

    /*--------------------Simple Decryption method using shift cipher--------------------*/
    String decrypt(String message, int key) {
        int shift = key % 26;
        int numShift = key % 10;
        StringBuffer result = new StringBuffer();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) ((c - base - shift + 26) % 26 + base);
            } else if (Character.isDigit(c)) {
                c = (char) ((c - '0' - numShift + 10) % 10 + '0');
            }
            result.append(c);
        }
        return result.toString();
    }

    /*------------------- Reverse Encryption: Encrypts and then reverses the message--------------------*/
    String ReverseEncrypt(String message, int key) {
        StringBuffer result = new StringBuffer();
        int shift = key % 26;
        int numberShift = (key + 2) % 10;

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) ((c - base + shift) % 26 + base);
            } else if (Character.isDigit(c)) {
                c = (char) ((c - '0' + numberShift) % 10 + '0');
            }
            result.append(c);
        }

        return result.reverse().toString();
    }

    /*-------------------- Reverse Decryption: Reverses the message and then decrypts--------------------*/
    String ReverseDecrypt(String message, int key) {
        StringBuffer result = new StringBuffer();
        int shift = key % 26;
        int numberShift = (key + 2) % 10;

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) ((c - base - shift + 26) % 26 + base);
            } else if (Character.isDigit(c)) {
                c = (char) ((c - '0' - numberShift + 10) % 10 + '0');
            }
            result.append(c);
        }

        return result.reverse().toString();
    }

}
