import java.util.*;
import java.io.*;


class Main{

    public static void main(String[] args) throws Exception{
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nab = Arrays.stream(br.readLine().split(" "))
            .mapToInt(str -> Integer.parseInt(str))
            .toArray();

        int busTime = nab[1];
        int trainTime = nab[2]-nab[0];

        if(trainTime < 0){
            System.out.println("Bus");
        }else{
            if(busTime == nab[2]){
                System.out.println("Anything");
            }else if(busTime < nab[2]){
                System.out.println("Bus");
            }else{
                System.out.println("Subway");
            }
        }
    }   
}