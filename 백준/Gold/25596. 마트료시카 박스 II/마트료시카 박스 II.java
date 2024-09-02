import java.util.*;
import java.io.*;

class Main {

    private static boolean dividable = true;
    private static Map<Integer, LinkedList<Integer>> boxMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nmk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nmk[0], m = nmk[1], k = nmk[2];

        for (int i = 1; i <= n; i++) {
            boxMap.put(i, new LinkedList<>());
        }

        /**
         * 전체적인 포함 관계가 수정되면 안됨
         * 즉? 매 순간 새로운 박스를 추가해야함
         * 
         * 차례대로 검사?
         * m 이상 있는 경우 m 개만큼 제거하고 엔트리 추가하기
         * 만약에 있는거 다 가져와서 해도 괜찮지 그치
         */

        for (int i = 1; i <= n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            var list = boxMap.get(i);
            for (int j = 0; j < input[0]; j++) {
                list.add(input[j + 1]);
            }
        }

        int addBoxCount = 0;
        int current = n + addBoxCount;
        for (int i = 1; i <= n; i++) {
            Queue<Integer> list = boxMap.get(i);
            while (list.size() > m) {
                addBoxCount++;
                current = n + addBoxCount;
                if (addBoxCount > k) {
                    System.out.println(0);
                    return;
                }
                var newList = new LinkedList<Integer>();
                for (int j = 0; j < m; j++) {
                    newList.add(list.poll());
                }
                boxMap.put(current, newList);
                list.add(current);
            }
        }

        System.out.println(1);
        System.out.println(boxMap.size() - n);
        for (int i = 1; i <= boxMap.size(); i++) {
            var sb = new StringBuilder();

            var list = boxMap.get(i);
            sb.append(list.size() + " ");
            for (int j = 0; j < list.size(); j++) {
                sb.append(list.get(j) + " ");
            }
            System.out.println(sb.toString());
        }

    }

}
