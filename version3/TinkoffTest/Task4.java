import java.util.*;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // Количество проверок
        int c = scanner.nextInt(); // Максимальное количество изменений
        int d = scanner.nextInt(); // Значение параметра d

        List<Integer> list = new ArrayList<>(n);

        // Считываем результаты проверок
        for (int i = 0; i < n; i++) {
            list.add(i, scanner.nextInt());
        }

        while (c > 0){
            int indexMin = list.indexOf(Collections.min(list));
            if(list.get(indexMin) <= 0){
                if(list.stream().anyMatch(a -> a < Math.abs(list.get(indexMin)))){
                    int index = list.indexOf(Collections.min(list.stream().filter(a-> a != list.get(indexMin)).toList()));
                    list.set(index, list.get(index)+d);
                    c--;
                } else {
                    list.set(indexMin, list.get(indexMin) - d);
                    c--;
                }
            } else {
                list.set(indexMin, list.get(indexMin)-d);
                c--;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(list.get(i) + " ");
        }

        scanner.close();
    }

}






