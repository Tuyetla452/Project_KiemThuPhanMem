import java.util.*;

public class PhoneBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("\n---------------------------------------------------------");
        System.out.println("Enter the number of entries:");
        int n = scanner.nextInt();

        HashMap<String, String> phoneBook = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String name = scanner2.nextLine(); // Name can include spaces
            String phone = scanner2.nextLine();
            phoneBook.put(name, phone);
        }
        scanner.nextLine();

        // get the query and print output
        ArrayList<String> query = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            query.add(scanner.nextLine());
        }
        System.out.println("");
        for (int i = 0; i < query.size(); i++) {
            if (phoneBook.containsKey(query.get(i))) {
                System.out.println(query.get(i) + "=" + phoneBook.get(query.get(i))); // Output name=phone
            } else {
                System.out.println("Not found"); // If the name does not exist
            }
        }

        scanner.close();
        scanner2.close();
        System.out.println("\n---------------------------------------------------------");
    }
}