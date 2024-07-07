import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nk = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        char[] num = br.readLine().toCharArray();

        int count = nk[1];
        var dq = new ArrayDeque<Character>();
        dq.add(num[0]);
        for (int i = 1; i < nk[0]; i++) {
            if (count == 0) {
                dq.addLast(num[i]);
                continue;
            }

            char prevNum = 0;

            var top = dq.removeLast();
            if (top < num[i]) {
                count--;
                while (!dq.isEmpty() && count > 0) {
                    var tmpTop = dq.removeLast();
                    if (tmpTop >= num[i]) {
                        dq.addLast(tmpTop);
                        break;
                    } else {
                        count--;
                    }
                }
                dq.addLast(num[i]);
                prevNum = 0;
            } else {
                dq.addLast(top);
                if (i == nk[0] - count) {
                    count--;
                    continue;
                }
                if (top == num[i]) {

                    dq.addLast(num[i]);

                } else if (prevNum < num[i]) {
                    dq.addLast(num[i]);
                } else {
                    count--;
                }
                prevNum = num[i];
            }
        }
        var sb = new StringBuilder();
        for (char top : dq) {
            sb.append(top);
        }
        System.out.println(sb.toString());
    }

    static void toString(Deque<Character> dq) {
        for (char input : dq) {
            System.out.print(input);
        }
        System.out.println();
    }

}
