import java.util.*;
import java.io.*;

class Main {

    private static Map<Character, Pair> tree = new HashMap<>();
    private static Character ROOT = 'A';

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().replace(" ", "").toCharArray();
            char parent = input[0];
            Character left = input[1] == '.' ? null : input[1];
            Character right = input[2] == '.' ? null : input[2];

            tree.put(parent, new Pair(left, right));
        }
        preorder(ROOT);
        System.out.println();
        inorder(ROOT);
        System.out.println();
        postorder(ROOT);
        System.out.println();

    }

    private static void preorder(Character current) {
        if (Objects.isNull(current))
            return;
        System.out.print(current);
        var pair = tree.get(current);
        preorder(pair.left);
        preorder(pair.right);
    }

    private static void inorder(Character current) {
        if (Objects.isNull(current))
            return;
        var pair = tree.get(current);
        inorder(pair.left);
        System.out.print(current);
        inorder(pair.right);
    }

    private static void postorder(Character current) {
        if (Objects.isNull(current))
            return;
        var pair = tree.get(current);
        postorder(pair.left);
        postorder(pair.right);
        System.out.print(current);
    }

    private static class Pair {
        Character left, right;

        public Pair(Character left, Character right) {
            this.left = left;
            this.right = right;
        }
    }

}
