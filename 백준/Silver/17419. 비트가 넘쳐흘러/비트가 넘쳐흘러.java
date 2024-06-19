import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception{
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String k = br.readLine();

        int result = 0;
        for(int i=0;i<k.length();i++){
            if(k.charAt(i)=='1'){
                result++;
            }
        }

        System.out.println(result);
    }
    
}
