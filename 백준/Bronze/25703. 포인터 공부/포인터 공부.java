import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            System.out.println(generate(i));
        }
    }

    private static String generate(int idx) {
        var sb = new StringBuilder();
        sb.append("int ");

        if (idx >= 2) {
            sb.append(generatePoint("*".repeat(idx) + "ptr" + idx,
                    "ptr" + (idx - 1 == 1 ? "" : idx - 1)));
        } else if (idx == 1) {
            sb.append(generatePoint("*ptr", "a"));
        } else {
            sb.append("a");
        }

        sb.append(";");
        return sb.toString();
    }

    private static String generatePoint(String left, String right) {
        return left + " = &" + right;
    }

}
