import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] nk = Arrays.stream(br.readLine().split(" "))
                .mapToInt(str -> Integer.parseInt(str))
                .toArray();

        double[] area = new double[nk[0] + 1];

        for (int i = 0; i < nk[0]; i++) {
            int p = Integer.parseInt(br.readLine());
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(str -> Integer.parseInt(str))
                    .toArray();
            
            for(int j=0;j<p;j++){
                int x=input[j*2],y=input[j*2+1];
                area[i]=Math.max(area[i], Math.pow(x,2)+Math.pow(y,2));
            }
        }

        Arrays.sort(area);

        System.out.printf("%.2f\n",area[nk[1]]);
    }

}
