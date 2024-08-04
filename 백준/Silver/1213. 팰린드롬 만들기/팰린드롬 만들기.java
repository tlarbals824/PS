import java.util.*;
import java.io.*;

class Main {

    private static Map<Character, Integer> charMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        Character mid = ' ';
        String origin = br.readLine();
        for (char ch : origin.toCharArray()) {
            charMap.put(ch, charMap.getOrDefault(ch, 0) + 1);
        }

        for (var entry : charMap.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                if (mid == ' ') {
                    mid = entry.getKey();
                } else {
                    System.out.println("I'm Sorry Hansoo");
                    return;
                }
            }
        }
        Deque<Character> dq = new ArrayDeque<>();
        if (mid != ' ') {
            dq.addFirst(mid);
        }

        List<Character> keyList = new ArrayList<>(charMap.keySet());
        Collections.sort(keyList, Comparator.reverseOrder());

        for (var key : keyList) {
            int value = charMap.get(key);
            while (value > 1) {
                dq.addFirst(key);
                dq.addLast(key);
                value -= 2;
            }
        }
        var sb = new StringBuilder();
        for (char ch : dq) {
            sb.append(ch);
        }
        System.out.println(sb.toString());
    }

}
