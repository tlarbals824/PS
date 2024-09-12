import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args)throws Exception{
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nul = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n=nul[0], u=nul[1], l=nul[2];

        boolean solved = (n >= 1000);
        boolean maple = (u >= 8000 || l >= 260);
        if(solved && maple){
            System.out.println("Very Good");
        }else if(solved){
            System.out.println("Good");
        }else{
            System.out.println("Bad");
        }
    }
    
}
