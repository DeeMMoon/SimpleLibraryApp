import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();



        String password = scanner.next();

        if(k >= n) {
            System.out.println("0");
            return;
        }

        int[] charCount = new int[26];

        for (int i = 0; i < n; i++) {
            charCount[password.charAt(i) - 'a']++;
        }

        List<Integer> deletableCounts = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (charCount[i] > 0) {
                deletableCounts.add(charCount[i]);
            }
        }

        Collections.sort(deletableCounts);

        int i = 0;
        while (k > 0) {
            if(deletableCounts.get(i) == 0)
                i++;
           deletableCounts.set(i, deletableCounts.get(i)-1);
           k--;
        }

        int distinctChars = 0;
        for (int count : deletableCounts) {
            if (count > 0) {
                distinctChars++;
            }
        }

        System.out.println(distinctChars);
        scanner.close();
    }

}
