import java.util.*;

class Main{
    public static void main(String[] orgs){
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        while(t>0){
            t--;
            int n = scanner.nextInt();
            boolean isDividable = ((n+1)%((n/100)*100-n)==0);
            if(isDividable)
                System.out.println("Good");
            else
                System.out.println("Bye");
        }
    }
}