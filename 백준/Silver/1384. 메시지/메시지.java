import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = 1;
        while (true) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0)
                break;

            String[] name = new String[n];
            Queue<Pair> q = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                String[] input = br.readLine().split(" ");
                name[i] = input[0];
                for (int j = 0; j < n - 1; j++) {
                    if (input[j + 1].equals("N")) {
                        int target = (n + i - j - 1) % n;
                        q.add(new Pair(target, i));
                    }
                }
                /**
                 * 1: 5->4 ->3 ->2
                 * 2: 1 -> 5 -> 4-
                 */
            }

            if (count > 1) {
                bw.write("\n");
            }
            bw.write("Group " + count + "\n");
            if (q.size() == 0) {
                bw.write("Nobody was nasty\n");
            }
            while (!q.isEmpty()) {
                var front = q.poll();
                bw.write(name[front.x] + " was nasty about " + name[front.y] + "\n");
            }
            count++;
        }
        bw.flush();
    }

    private static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
