import java.util.*;

public class Solution {


    public static void main(String[] args) {
        Map<String, Integer> dictionary = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        int max = 1;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (!line.equals("")) {
                String[] words = line.split(" ");
                for (String w : words) {
                    w = w.toLowerCase().trim();
                    if (!w.equals("")) {
                        if (dictionary.containsKey(w)) {
                            int newCounter = dictionary.get(w) + 1;
                            dictionary.put(w, newCounter);
                            max = max > newCounter ? max : newCounter;
                        } else {
                            dictionary.put(w, 1);
                        }
                    }
                }
            }

        }
        int finalMax = max;
        dictionary.forEach((key, value) -> {
            if (value == finalMax) {
                System.out.println(key);
            }
        });
    }
}
