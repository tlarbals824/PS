import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> result = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        result.add(Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            if (result.get(result.size() - 1) < input[i]) {
                result.add(input[i]);
            } else {
                int idx = lowerBound(result, input[i]);
                result.set(idx, input[i]);
            }
        }

        System.out.println(result.size());
    }

    private static int lowerBound(List<Integer> targetList, int target) {
        int left = 0;
        int right = targetList.size() - 1;
        while (left < right) {
            int mid = (left + right) / 2;

            if (targetList.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

}
