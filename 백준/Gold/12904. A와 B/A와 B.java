import java.util.*;
import java.io.*;

class Main {

    static Deque<Character> dq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception{
        var br = new BufferedReader(new InputStreamReader(System.in));

        char[] s = br.readLine().toCharArray();
        br.readLine().chars().forEach(it -> {
            dq.addLast((char)it);
        });

        int direction = 1; // 1 뒤 -> 앞, -1 앞 -> 뒤
        while(dq.size() > s.length){
            char target;
            if(direction == 1){
                target = dq.removeLast();
            }else{
                target = dq.removeFirst();
            }
            if(target=='B'){
                direction=-direction;
            }
        }

        for(int i=0;i<s.length;i++){
            char target;
            if(direction == -1){
                target = dq.removeLast();
            }else{
                target = dq.removeFirst();
            }

            if(target != s[i]){
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);

        
    }
    
}
