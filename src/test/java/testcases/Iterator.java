import java.util.*;

public class Iterator {
    public static void main(String[] args) {
        System.out.println("--------------------------------------------------");
        ArrayList<String> element = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Element[0]:=>");
        String input = scanner.nextLine();
        int counter = 1;
        while (input != "") {
            element.add(input);
            System.out.printf("Element[%d]:=>", counter);
            input = scanner.nextLine();
            counter++;
        }
        System.out.println();
        for (int i = 0; i < element.size(); i++) {
            if (element.get(i).equals("###")) {
                for (int j = i+1; j < element.size(); j++) {
                    System.out.println(element.get(j));
                }
                break;
            }
        }
        System.out.println("--------------------------------------------------");
        scanner.close();
    }
}