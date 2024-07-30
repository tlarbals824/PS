import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        System.out.println(Character.getNumericValue(input.charAt(input.length() - 1)) % 2 == 1 ? "SK" : "CY");
    }

}
