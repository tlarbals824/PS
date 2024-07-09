import java.util.*;
import java.io.*;

class Main {

    static int result = 0;
    static Set<String> resultSet = new HashSet<>();
    

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        long[] input = Arrays.stream(br.readLine().split(" "))
               .mapToLong(Long::parseLong)
            .toArray();
        
        long result = (input[0]+input[1]) * (input[0]-input[1]);
        System.out.println(result);

        

        

    }
}
