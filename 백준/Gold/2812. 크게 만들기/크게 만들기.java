import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int[] nk = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        char[] num = br.readLine().toCharArray();

        int count = nk[1];
        var dq = new ArrayDeque<Character>();
        dq.add(num[0]);
        for (int i = 1; i < nk[0]; i++) {
            if(!dq.isEmpty()){
                while(!dq.isEmpty() && count > 0 && num[i] > dq.getLast()){
                    dq.removeLast();
                    count--;
                }
            }

            dq.addLast(num[i]);
        }

        while(count-- > 0){
            dq.removeLast();
            
        }

        var sb = new StringBuilder();
        for (char top : dq) {
            sb.append(top);
        }
        System.out.println(sb.toString());
    }

}
