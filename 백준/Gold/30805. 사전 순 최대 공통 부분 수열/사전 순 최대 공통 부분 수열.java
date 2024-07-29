import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int m = Integer.parseInt(br.readLine());
        int[] b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> result = new ArrayList<>();
        int idxA = 0, idxB = 0;
        while (idxA < n && idxB < m) {

            int max = 0;
            int nextIdxA = idxA;
            int nextIdxB = idxB;
            for (int i = idxA; i < n; i++) {
                for (int j = idxB; j < m; j++) {
                    if (a[i] == b[j]) {
                        if (max < a[i]) {
                            max = a[i];
                            nextIdxA = i;
                            nextIdxB = j;
                        }
                    }
                }
            }

            if (max == 0)
                break;

            result.add(nextIdxB);
            idxA = nextIdxA + 1;
            idxB = nextIdxB + 1;
        }

        bw.write(result.size() + "\n");
        for (int i = 0; i < result.size(); i++) {
            bw.write(b[result.get(i)] + " ");
        }
        bw.write("\n");
        bw.flush();
    }

}
