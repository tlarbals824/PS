import java.util.*;
import java.io.*;

class Main {

    static String[] input;
    static int[] count = new int[26];
    static Set<Character> charSet = new HashSet<>();

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        input = new String[n];
        for(int i=0;i<n;i++){
            input[i]=br.readLine();
            input[i].chars()
                .forEach(alpha -> charSet.add((char)alpha));
        }
    
        System.out.println(recursive(0,0,new int[charSet.size()]));
    }

    static int convert() {
        int result = 0;
        for (int i = 0; i < input.length; i++) {
            int tmpResult = 0;
            for(int j=0;j<input[i].length(); j++){
                tmpResult = tmpResult * 10 + count[input[i].charAt(j)-'A'];
            }
            result+=tmpResult;
        }
        return result;
    }

    static int recursive(int idx, int bit, int[] permu) {
        if (idx == charSet.size()) {
            Arrays.fill(count,0);
            int tmpIdx = 0;
            for(char alpha: charSet){
                count[alpha-'A']=permu[tmpIdx++];
            }
            
            int result = convert();
            return result;
        } else {
            int result = 0;
            for (int i = 9; i > (9 - charSet.size()); i--) {
                if(isMark(bit, i)) continue;
                permu[idx]=i;
                result = Math.max(result, recursive(idx+1, mark(bit, i),permu));
            }
            return result;
        }
    }

    static int mark(int bit, int idx){
        return bit | 1 << idx;
    }

    static boolean isMark(int bit, int idx){
        return (bit & 1 << idx) > 0;
    }



}
