import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nhw = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nhw[0], h = nhw[1], w = nhw[2];
        /**
         * 가로 단위로 검색 글자가 하나라도 있으면 됨
         * 세로 단위로 검색 글자가 하나라도 있으면 됨
         */
        char[][] map = new char[h][n * w];
        for (int i = 0; i < h; i++) {
            map[i] = br.readLine().toCharArray();
        }
        char[] result = new char[n];
        Arrays.fill(result, '?');
        for (int i = 0; i < n * w; i += w) {
            for (int x = i; x < i + w; x++) {
                for (int y = 0; y < h; y++) {
                    if (map[y][x] != '?') {
                        result[i / w] = map[y][x];
                    }
                }
            }
        }
        System.out.println(String.valueOf(result));
    }

}
