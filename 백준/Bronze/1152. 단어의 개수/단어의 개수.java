import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] arsg) throws Exception {
        String[] tokens = new BufferedReader(new InputStreamReader(System.in)).readLine().split(" ");
        int result = 0;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].contains(" ") || tokens[i] == null || tokens[i].length() == 0)
                continue;

            result++;
        }

        System.out.println(result);
    }

}
