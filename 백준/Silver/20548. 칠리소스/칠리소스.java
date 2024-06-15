import java.util.*;

class Main {
    public static void main(String[] orgs){
        int count[] = new int[14];
        long sevenPow[] = new long[14];
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<14;i++){
            sevenPow[i]=(long)Math.pow(7,i);
        }

        long c = scanner.nextLong();
        for(int i=13;i>=0;i--){
            if(c>=sevenPow[i]*2){
                count[i]=2;
                c-=(sevenPow[i]*2);
            }else if(c>=sevenPow[i]){
                count[i]=1;
                c-=sevenPow[i];
            }
        }

        long result = 0;
        for(int i=0;i<14;i++){
            result+=(Math.pow(3,Math.max(i,0))*count[i]);
        }
        System.out.println(result);
    }

    
}
