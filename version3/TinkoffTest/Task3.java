import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // Количество моментов работы приложения
        int[] errors = new int[n]; // Массив с количеством ошибок
        for (int i = 0; i < n; i++) {
            errors[i] = scanner.nextInt();
        }

        int m = scanner.nextInt(); // Количество периодов
        for (int i = 0; i < m; i++) {
            int start = scanner.nextInt() - 1; // Начальный момент периода
            int end = scanner.nextInt() - 1; // Конечный момент периода

            // Проверка, является ли период сбоем
            boolean isFailure = isFailure(errors, start, end);

            // Вывод результата
            if (isFailure) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }

        scanner.close();
    }

    // Функция для проверки, является ли период сбоем
    private static boolean isFailure(int[] errors, int start, int end) {
        if(start == end)
            return true;
        boolean flag1 = false;
        boolean flag2 = false;
        int i = start;
            while (errors[i] >= errors[i+1] && i < end) {
                i++;
                flag1 = true;
            }
            if (start == i)
                return true;
            while (errors[i] <= errors[i+1] && i < end) {
                i++;
                flag2 = true;
            }
            if(i != end)
                return true;
            if (flag1 == false || flag2 == false)
                return true;
        return false;
    }

}
