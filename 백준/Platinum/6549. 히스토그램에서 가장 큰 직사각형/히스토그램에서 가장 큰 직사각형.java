import java.util.*;
import java.io.*;

class Main {
    private static int[] input;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (input[0] == 0)
                break;

            long result = 0;
            Stack<Block> stack = new Stack<>();
            for (long i = 1; i <= input[0]; i++) {

                long start = i;
                while (!stack.isEmpty()) {
                    var top = stack.pop();
                    if (input[(int) top.idx] < input[(int) i]) {
                        stack.add(top);
                        break;
                    } else {
                        start = top.start;
                        result = Math.max(result, (i - top.start) * input[(int) top.idx]);
                    }
                }
                stack.add(new Block(start, i));
            }

            while (!stack.isEmpty()) {
                var top = stack.pop();
                result = Math.max(result, (input[0] - top.start + 1) * input[(int) top.idx]);
            }

            bw.write(result + "\n");
        }
        bw.flush();
    }

    private static class Block {
        long start;
        long idx;

        public Block(long start, long idx) {
            this.start = start;
            this.idx = idx;
        }
    }
}
