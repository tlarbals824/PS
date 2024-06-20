import java.util.*;
import java.util.concurrent.*;
import java.io.*;

class Main {

    static char[][] map = new char[101][101];
    static int[][] check = new int[101][101];

    static int[] dirY = { -1, 0, 1, 0 };
    static int[] dirX = { 0, -1, 0, 1 };

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            run(br, bw);
        }
        bw.flush();
    }

    static void run(BufferedReader br, BufferedWriter bw) throws Exception {
        int[] hw = Arrays.stream(br.readLine().split(" "))
                .mapToInt(str -> Integer.parseInt(str))
                .toArray();
        for (int i = 0; i < hw[0]; i++) {
            map[i] = br.readLine().toCharArray();
        }

        Set<Character> keySet = new HashSet<>();
        br.readLine().chars()
                .forEach(ch -> {
                    keySet.add((char) ch);
                });

        Set<Pos> doorSet = ConcurrentHashMap.newKeySet();

        Integer result = 0;
        Queue<Pos> q = new LinkedList<>();

        while (true) {

            for (int i = 0; i < hw[0]; i++) {
                result=isValid(map, i, 0, keySet, q, result);
                result=isValid(map, i, hw[1] - 1, keySet, q, result);
            }
            for (int i = 0; i < hw[1]; i++) {
                result=isValid(map, 0, i, keySet, q, result);
                result=isValid(map, hw[0] - 1, i, keySet, q, result);
            }

            for (int i = 0; i < hw[0]; i++) {
                isValid(map, i, 0, keySet, doorSet, q);
                isValid(map, i, hw[1] - 1, keySet, doorSet, q);
            }
            for (int i = 0; i < hw[1]; i++) {
                isValid(map, 0, i, keySet, doorSet, q);
                isValid(map, hw[0] - 1, i, keySet, doorSet, q);
            }

            for (int i = 0; i < hw[0]; i++) {
                Arrays.fill(check[i], 0);
            }

            int prevDoorSize = doorSet.size();
            int prevKeySize = keySet.size();

            while (!q.isEmpty()) {
                var top = q.poll();

                check[top.y][top.x] = 1;

                for (int i = 0; i < 4; i++) {
                    int nextY = dirY[i] + top.y;
                    int nextX = dirX[i] + top.x;
                    if (nextY < 0 || nextY >= hw[0])
                        continue;
                    if (nextX < 0 || nextX >= hw[1])
                        continue;
                    if (check[nextY][nextX] == 1)
                        continue;
                    if (map[nextY][nextX] == '*')
                        continue;

                    if (map[nextY][nextX] == '$') {
                        result++;
                    } else if (Character.isLowerCase(map[nextY][nextX])) {
                        keySet.add(map[nextY][nextX]);
                    } else if (Character.isUpperCase(map[nextY][nextX])) {
                        if (keySet.contains(Character.toLowerCase(map[nextY][nextX]))) {
                            doorSet.remove(new Pos(nextY, nextX));
                        } else {
                            doorSet.add(new Pos(nextY, nextX));
                            continue;
                        }
                    }
                    check[nextY][nextX] = 1;
                    map[nextY][nextX] = '.';
                    q.add(new Pos(nextY, nextX));
                }
            }

            if (prevDoorSize == doorSet.size() && prevKeySize == keySet.size())
                break;
        }

        bw.write(result + "\n");
    }

    static Integer isValid(char[][] map, int y, int x, Set<Character> keySet, Queue<Pos> q, Integer result) {
        char target = map[y][x];
        if (target == '.') {
        } else if (Character.isLowerCase(target)) {
            keySet.add(target);
        } else if (target == '$') {
            result++;
        } else {
            return result;
        }
        q.add(new Pos(y, x));
        map[y][x] = '.';
        return result;
    }

    static void isValid(char[][] map, int y, int x, Set<Character> keySet, Set<Pos> door, Queue<Pos> q) {
        char target = map[y][x];
        if (Character.isUpperCase(target)) {
            if (keySet.contains(Character.toLowerCase(target))) {
                q.add(new Pos(y, x));
                door.remove(new Pos(y, x));
                map[y][x] = '.';
            }
        }
    }

    static void print(char[][] map, int[] hw) {
        for (int i = 0; i < hw[0]; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }

    static class Pos {
        int y;
        int x;

        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public boolean equals(Object other) {
            if (other instanceof Pos) {
                Pos target = (Pos) other;
                return target.x == this.x && target.y == this.y;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }
    }

}
