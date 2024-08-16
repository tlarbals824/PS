import java.util.*;
import java.io.*;

class Main {

    private static Map<Key, Set<Key>> tree = new HashMap<>();
    private static String ROOT = "root";
    private static Key ROOT_KEY = new Key(0, ROOT, null);

    static {
        tree.put(ROOT_KEY, new TreeSet<>());
    }

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");

            int depth = Integer.parseInt(input[0]);

            Key current = ROOT_KEY;
            for (int j = 0; j < depth; j++) {
                var targetKey = current;
                var set = tree.getOrDefault(targetKey, new TreeSet<>());
                var newKey = new Key(j + 1, input[j + 1], targetKey);
                set.add(newKey);
                tree.put(targetKey, set);
                current = newKey;
            }
        }

        var result = print(ROOT_KEY, new StringBuilder());
        System.out.print(result.toString());

        /**
         * 트리를 만들면됨
         * 2 B A
         * 루트 하위에 B가 있는지 확인, 있으면 타고타고가기
         * 
         * 
         * 4 A B C D
         */
    }

    private static StringBuilder print(Key key, StringBuilder sb) {
        if (tree.get(key) == null) {
            return sb;
        }
        for (var target : tree.get(key)) {
            sb.append(printLine(key.depth + 1) + target.name + "\n");
            print(target, sb);
        }
        return sb;
    }

    private static String printLine(int depth) {
        if (depth == 0)
            return "";
        return "--".repeat(depth - 1);
    }

    private static class Key implements Comparable<Key> {
        int depth;
        String name;
        Key parent;

        public Key(int depth, String name, Key parent) {
            this.depth = depth;
            this.name = name;
            this.parent = parent;
        }

        public int compareTo(Key other) {
            return this.name.compareTo(other.name);
        }

        public int hashCode() {
            return Objects.hash(depth, name, parent);
        }

        public boolean equals(Object other) {

            if (other instanceof Key) {
                var key = (Key) other;
                return (this.depth == key.depth) && (this.name.equals(key.name))
                        && (this.parent != null ? this.parent.equals(key.parent) : true);
            }
            return false;
        }
    }

}
