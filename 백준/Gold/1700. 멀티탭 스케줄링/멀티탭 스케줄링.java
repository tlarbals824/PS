import java.util.*;
import java.util.concurrent.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nk = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        Map<Integer, PriorityQueue<Integer>> commandMap = new HashMap<>();

        int[] command = Arrays.stream(br.readLine().split(" "))
                .mapToInt(it -> {
                    int num = Integer.parseInt(it);
                    return num;
                }).toArray();
        for (int i = 0; i < command.length; i++) {
            int num = command[i];
            var numList = commandMap.getOrDefault(num, new PriorityQueue<>());
            numList.add(i);
            commandMap.put(num, numList);
        }

        int result = 0;

        Set<Integer> currentConnected = new HashSet<>();

        for (int i = 0; i < nk[1]; i++) {
            if (currentConnected.size() < nk[0]) {
                currentConnected.add(command[i]);
                commandMap.get(command[i]).poll();
            } else {
                if (currentConnected.contains(command[i])) {
                    commandMap.get(command[i]).poll();
                    continue;
                }

                int minIdx = 0;
                for (int idx : currentConnected) {
                    if (minIdx == 0) {
                        minIdx = idx;
                        continue;
                    }

                    var minList = commandMap.get(minIdx);
                    var targetList = commandMap.get(idx);
                    if (minList.size() == 0) {
                        continue;
                    }
                    if (targetList.size() == 0) {
                        minIdx = idx;
                        continue;
                    }
                    int minFirst = minList.poll();
                    int targetFirst = targetList.poll();
                    if (minFirst < targetFirst) {
                        minIdx = idx;
                    }
                    targetList.add(targetFirst);
                    minList.add(minFirst);
                }
                currentConnected.remove(minIdx);
                currentConnected.add(command[i]);
                commandMap.get(command[i]).poll();
                result++;
            }
        }

        System.out.println(result);
    }

}
