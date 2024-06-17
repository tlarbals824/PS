import java.util.*;
import java.util.stream.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String nk[] = reader.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        var numberList = Arrays.stream(reader.readLine().split(" "))
            .map(number -> Integer.parseInt(number))
            .sorted()
            .collect(Collectors.toList());

        long result = 0;
        int activeCount = 0;

        for(int num : numberList){
            result+=(long)activeCount*num;
            if(activeCount<k){
                activeCount++;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(result+"\n");
        bw.flush();
    }
}
