import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Double> input = new ArrayList<>();
        while (n-- > 0) {
            double num = Double.parseDouble(br.readLine());
            input.add(num);
        }

        double result = 0;
        for (int i = 1; i < input.size(); i++) {
            input.set(i, Math.max(input.get(i), input.get(i - 1) * input.get(i)));
            result = Math.max(result, input.get(i));
        }

        result = Math.round(result * 1000) / 1000.0;

        System.out.printf("%.3f\n", result);
    }

}
